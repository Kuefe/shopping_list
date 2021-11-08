package de.kuefe.shoppinglist.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import de.kuefe.shoppinglist.model.Article

@Entity(tableName = "article_table")
data class DatabaseArticle(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(defaultValue = "1")
    val quantity: Double,
    val unit: String,
    val description: String
)

fun List<DatabaseArticle>.asDomainModel(): List<Article> {
    return map {
        Article(
            id = it.id,
            quantity = it.quantity,
            unit = it.unit,
            description = it.description

        )
    }
}

fun asDatabaseModel(article: Article): DatabaseArticle {
    return DatabaseArticle(
        id = article.id,
        quantity = article.quantity,
        unit = article.unit,
        description = article.description
    )

}
