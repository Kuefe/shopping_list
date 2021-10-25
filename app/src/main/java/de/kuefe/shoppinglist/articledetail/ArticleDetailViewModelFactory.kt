package de.kuefe.shoppinglist.articledetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.kuefe.shoppinglist.model.Article

class ArticleDetailViewModelFactory(
    private val article: Article,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleDetailViewModel::class.java)) {
            return ArticleDetailViewModel(article, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}