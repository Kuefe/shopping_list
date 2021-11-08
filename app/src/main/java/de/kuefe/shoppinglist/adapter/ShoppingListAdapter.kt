package de.kuefe.shoppinglist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import de.kuefe.shoppinglist.databinding.ShoppingListItemBinding
import de.kuefe.shoppinglist.model.Article


/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 */

class ShoppingListAdapter(val onClickListener: OnClickListener) :
    ListAdapter<Article, ShoppingListAdapter.ArticleViewHolder>(DiffCallback) {

    /**
     * The ArticleViewHolder constructor takes the binding variable from the associated
     * ListViewItem, which nicely gives it access to the full [Article] information.
     */
    class ArticleViewHolder(private var binding: ShoppingListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Article) {
            binding.databaseArticle = item
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleViewHolder {
        return ArticleViewHolder(
            ShoppingListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    object DiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return newItem == newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return newItem.id == oldItem.id
        }

    }

    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [Article]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [Article]
     */
    class OnClickListener(val clickListener: (article: Article) -> Unit) {
        fun onClick(article: Article) = clickListener(article)
    }
}