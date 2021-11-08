package de.kuefe.shoppinglist.articledetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import de.kuefe.shoppinglist.databinding.FragmentArticleDetailBinding


/**
 * A simple [Fragment] subclass.
 * Use the [ArticleDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArticleDetailFragment : Fragment() {
    /**
     * Lazily initialize our [OverviewViewModel].
     */
    private lateinit var viewModel: ArticleDetailViewModel
    private lateinit var viewModelFactory: ArticleDetailViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application

        val binding = FragmentArticleDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val article = ArticleDetailFragmentArgs.fromBundle(requireArguments()).selectedArticle

        viewModelFactory = ArticleDetailViewModelFactory(application)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ArticleDetailViewModel::class.java)

        // Giving the binding access to the ElectionViewModel
        binding.viewModel = viewModel
        viewModel.article = article

        binding.saveButton.setOnClickListener { view: View ->
            viewModel.saveArticlel()
            view.findNavController()
                .navigate(ArticleDetailFragmentDirections.actionArticleDetailFragmentToShoppingListFragment())

        }
        return binding.root
    }

}