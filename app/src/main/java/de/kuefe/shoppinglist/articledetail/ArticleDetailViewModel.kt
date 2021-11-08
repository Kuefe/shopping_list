package de.kuefe.shoppinglist.articledetail

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.kuefe.shoppinglist.BR
import de.kuefe.shoppinglist.database.ShopplingListDatabase.Companion.getInstance
import de.kuefe.shoppinglist.model.Article
import de.kuefe.shoppinglist.repository.ArticleRepository
import kotlinx.coroutines.launch
import timber.log.Timber


class ArticleDetailViewModel(application: Application) : ViewModel(), Observable {
    private val database = getInstance(application)
    private val articleRepository = ArticleRepository(database)

    private val propertyChangeRegistry = PropertyChangeRegistry()

    private val _selectedArticle = MutableLiveData<Article>()

    // The external LiveData for the SelectedProperty
    val selectedArticle: LiveData<Article>
        get() = _selectedArticle

    //Get address from individual fields
    @Bindable
    var article = Article(0, 1.0, "", "")
        set(value) {
            if (value != field) {
                field = value
                propertyChangeRegistry.notifyChange(this, BR.article)
            }
        }

    fun saveArticlel() {
        viewModelScope.launch {
            try {
                articleRepository.insertArticle(article)
            } catch (e: Exception) {
                Timber.e(e.message)
            }
        }
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        propertyChangeRegistry.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        propertyChangeRegistry.remove(callback)
    }
}