package com.maghraby.hyperonenews.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maghraby.hyperonenews.data.database.dao.dao
import com.maghraby.hyperonenews.data.database.entity.NewsEntity
import com.maghraby.hyperonenews.data.database.entity.UserEntity

@Database(entities = [NewsEntity::class, UserEntity::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase(){
    abstract val dao: dao

}