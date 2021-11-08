package de.kuefe.shoppinglist.shoppinglist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.kuefe.shoppinglist.database.ShoppingListDao

/**
 * Simple ViewModel factory that provides the context to the ViewModel.
 */

class ShoppingListViewModelFactory(
    private val application: Application
) :
    ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoppingListViewModel::class.java)) {
            return ShoppingListViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}