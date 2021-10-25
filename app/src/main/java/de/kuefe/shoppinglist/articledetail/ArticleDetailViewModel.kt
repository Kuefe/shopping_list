package de.kuefe.shoppinglist.articledetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.kuefe.shoppinglist.model.Article

class ArticleDetailViewModel(article: Article, app: Application) : AndroidViewModel(app) {
    private val _selectedArticle = MutableLiveData<Article>()

    // The external LiveData for the SelectedProperty
    val selectedArticle: LiveData<Article>
        get() = _selectedArticle

    init {
        _selectedArticle.value = article
    }
}