package de.kuefe.shoppinglist.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import de.kuefe.shoppinglist.model.Article

/**
 * When there is no Article data (data is null), hide the [RecyclerView], otherwise show it.
 */
@BindingAdapter("listdata")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Article>?) {
    val adapter = recyclerView.adapter as ShoppingListAdapter
    adapter.submitList(data) {
        recyclerView.scrollToPosition(0)
    }
}

