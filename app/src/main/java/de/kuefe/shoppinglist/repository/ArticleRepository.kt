package de.kuefe.shoppinglist.repository

import de.kuefe.shoppinglist.database.DatabaseArticle
import de.kuefe.shoppinglist.database.ShopplingListDatabase
import de.kuefe.shoppinglist.database.asDatabaseModel
import de.kuefe.shoppinglist.database.asDomainModel
import de.kuefe.shoppinglist.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArticleRepository(private val database: ShopplingListDatabase) {


    /**
     * get a List of articles from the database an transform then to a domain object
     */
    suspend fun getAllArticles(): List<Article> {
        return withContext(Dispatchers.IO) {
            val electionList: List<DatabaseArticle> =
                database.shoppingListDao.getAll()
            electionList.asDomainModel()
        }
    }

    /**
     * insert an article in the database
     */

    suspend fun insertArticle(article: Article){
        return withContext(Dispatchers.IO) {
            database.shoppingListDao.insert(asDatabaseModel(article))
        }
    }
}