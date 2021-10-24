package de.kuefe.shoppinglist.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ShoppingListDao {
    @Query("SELECT * FROM article_table")
    fun getAll(): List<Article>
}