package de.kuefe.shoppinglist.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "article_table")
data class Article(
    @PrimaryKey val id: Int,
    val quantity: Double,
    val unit: String,
    val description: String
)
