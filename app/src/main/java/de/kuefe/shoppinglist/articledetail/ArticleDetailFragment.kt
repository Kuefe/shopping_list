package de.kuefe.shoppinglist.articledetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import de.kuefe.shoppinglist.databinding.FragmentArticleDetailBinding
import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 * Use the [ArticleDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArticleDetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentArticleDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val article = ArticleDetailFragmentArgs.fromBundle(requireArguments()).selectedArticle
        Timber.i("Timber: " + article)
        val viewModelFactory = ArticleDetailViewModelFactory(article, application)
        binding.viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(ArticleDetailViewModel::class.java)
        return binding.root
    }

}