package de.kuefe.shoppinglist.database

import androidx.room.*

@Dao
interface ShoppingListDao {
    @Query("SELECT * FROM article_table")
    fun getAll(): List<DatabaseArticle>

    @Query("SELECT * from article_table WHERE id = :key")
    suspend fun get(key: Long): DatabaseArticle?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(databaseArticle: DatabaseArticle)

    @Update
    suspend fun update(databaseArticle: DatabaseArticle)

    @Query("DELETE FROM article_table")
    suspend fun clear()
}