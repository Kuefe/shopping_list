package de.kuefe.shoppinglist.shoppinglist

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.kuefe.shoppinglist.model.Article

class ShoppingListViewModel(application: Application) : ViewModel() {

    private val _articleList = MutableLiveData<List<Article>>()

    val articleList: LiveData<List<Article>>
        get() = _articleList

    /**
     * Variable that tells the Fragment to navigate to a specific [ArticleDetailFragment]
     *
     * This is private because we don't want to expose setting this value to the Fragment.
     */

    private val _navigateToSelectedArticle = MutableLiveData<Article>()

    /**
     * If this is non-null, immediately navigate to [VoterInfoFragment] and call [displayPropertyDetailsComplete]
     */
    val navigateToSelectedArticle: LiveData<Article>
        get() = _navigateToSelectedArticle

    /**
     * Call this immediately after navigating to [ArticleDetailFragment]
     *
     * It will clear the navigation request, so if the user rotates their phone it won't navigate
     * twice.
     */
    fun displayArticleDetailsComplete() {
        _navigateToSelectedArticle.value = null
    }

    /**
     * When the property is clicked, set the [_navigateToSelectedArticle] [MutableLiveData]
     * @param article The [Article] that was clicked on.
     */
    fun displayArticle(article: Article) {
        _navigateToSelectedArticle.value = article
    }

    init {
        defaultList()
    }

    private fun defaultList() {
        _articleList.value = mutableListOf(
            Article(2, 1.0, "Packung", "Salz"),
            Article(1, 2.0, "kg", "Bananen"),
            Article(3, 3.0, "Stück", "Butter")
        )
    }
}