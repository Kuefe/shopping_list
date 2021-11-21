package de.kuefe.shoppinglist.shoppinglist

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.kuefe.shoppinglist.database.ShopplingListDatabase
import de.kuefe.shoppinglist.model.Article
import de.kuefe.shoppinglist.repository.ArticleRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class ShoppingListViewModel(application: Application) : ViewModel() {
    private val database = ShopplingListDatabase.getInstance(application)
    private val articleRepository = ArticleRepository(database)

    private var countOfArticles: Int = 0

    private val _articleList = MutableLiveData<List<Article>>()

    val articleList: LiveData<List<Article>>
        get() = _articleList

    private val _article = MutableLiveData<Article>()

    val article: LiveData<Article>
        get() = _article

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

    fun getAllArticles() {
        Timber.i("Timber: getAllArticles")
        viewModelScope.launch {
            try {
                _articleList.value = articleRepository.getAllArticles()
            } catch (e: Exception) {
                Timber.e(e.message)
            }
        }
    }

    fun deleteArticle(article: Article): Int {
        viewModelScope.launch {
            try {
                articleRepository.deleteArticle(article)
                countOfArticles = articleRepository.countOfArticles()
            } catch (e: Exception) {
                Timber.e(e.message)
            }
        }
        return countOfArticles
    }
}