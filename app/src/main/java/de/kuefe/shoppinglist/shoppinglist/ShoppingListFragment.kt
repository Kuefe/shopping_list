package de.kuefe.shoppinglist.shoppinglist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import de.kuefe.shoppinglist.adapter.ShoppingListAdapter
import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 * Use the [ShoppingListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoppingListFragment : Fragment() {
    /**
     * Lazily initialize our [ShoppingListViewModel].
     */
    private lateinit var viewModel: ShoppingListViewModel
    private lateinit var viewModelFactory: ShoppingListViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application

        // Inflate the layout for this fragment
        val binding =
            de.kuefe.shoppinglist.databinding.FragmentShoppingListBinding.inflate(inflater)

        viewModelFactory = ShoppingListViewModelFactory(application)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ShoppingListViewModel::class.java)

        // Giving the binding access to the ElectionViewModel
        binding.viewModel = viewModel

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Sets the adapter of the ShoppingList RecyclerView with clickHandler lambda that
        // tells the viewModel when a election is clicked
        binding.shoppinglistRecycler.adapter =
            ShoppingListAdapter(ShoppingListAdapter.OnClickListener {
                viewModel.displayArticle(it)
                Timber.i("Timber: shoppinglistRecycler")
            })

        // Observe the navigateToSelectedProperty LiveData and Navigate when it isn't null
        // After navigating, call displayPropertyDetailsComplete() so that the ViewModel is ready
        // for another navigation event.
        viewModel.navigateToSelectedArticle.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                // Must find the NavController from the Fragment
                this.findNavController().navigate(
                    ShoppingListFragmentDirections.actionShoppingListFragmentToArticleDetailFragment()
                )
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                viewModel.displayArticleDetailsComplete()

            }
            Timber.i("Tiamber: navigateToSelectedArticle")
        })

        return binding.root
    }
}