package com.maghraby.hyperonenews.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maghraby.hyperonenews.data.database.entity.NewsEntity
import com.maghraby.hyperonenews.data.database.entity.UserEntity

@Dao
interface dao {
    @Query(" SELECT * FROM User WHERE username=:username AND password=:password")
    fun getUsers( username: String, password: String): LiveData<UserEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun registerUser(userEntity: UserEntity)

    @Query("SELECT * FROM News")
    fun getNews(): LiveData<List<NewsEntity>>

    @Insert
    fun insertNews(vararg news: NewsEntity)
}