package com.example.photogalleryapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface ImageDao {
    @Insert
    suspend fun insert(image: ImageEntity): Long


    @Query("SELECT * FROM images ORDER BY id DESC")
    fun getAll(): Flow<List<ImageEntity>>


    @Query("DELETE FROM images WHERE id = :id")
    suspend fun deleteById(id: Long)
}