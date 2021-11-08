package de.kuefe.shoppinglist.shoppinglist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import de.kuefe.shoppinglist.R
import de.kuefe.shoppinglist.adapter.ShoppingListAdapter
import de.kuefe.shoppinglist.database.ShopplingListDatabase
import de.kuefe.shoppinglist.databinding.FragmentShoppingListBinding
import de.kuefe.shoppinglist.model.Article
import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 * Use the [ShoppingListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoppingListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentShoppingListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shopping_list, container, false)

        val application = requireNotNull(activity).application

        val dataSource = ShopplingListDatabase.getInstance(application).shoppingListDao

        val viewModelFactory = ShoppingListViewModelFactory(application)

        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ShoppingListViewModel::class.java)

        // Giving the binding access to the ElectionViewModel
        binding.viewModel = viewModel

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        val adapter = ShoppingListAdapter(ShoppingListAdapter.OnClickListener { viewModel.displayArticle(it) })
        binding.shoppinglistRecycler.adapter = adapter


        // Observe the navigateToSelectedProperty LiveData and Navigate when it isn't null
        // After navigating, call displayPropertyDetailsComplete() so that the ViewModel is ready
        // for another navigation event.
        viewModel.navigateToSelectedArticle.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                // Must find the NavController from the Fragment
                this.findNavController().navigate(
                    ShoppingListFragmentDirections.actionShoppingListFragmentToArticleDetailFragment(
                        it
                    )
                )
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                viewModel.displayArticleDetailsComplete()

            }
        })

        binding.fab.setOnClickListener {
            val emptyArticle = Article(0, 1.0, "", "")
            this.findNavController().navigate(
                ShoppingListFragmentDirections.actionShoppingListFragmentToArticleDetailFragment(
                    emptyArticle
                )
            )
            // Tell the ViewModel we've made the navigate call to prevent multiple navigation
            viewModel.displayArticleDetailsComplete()
        }

        viewModel.articleList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.getAllArticles()

        return binding.root
    }
}