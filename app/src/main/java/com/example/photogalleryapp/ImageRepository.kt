package com.example.photogalleryapp

import kotlinx.coroutines.flow.Flow
class ImageRepository(private val dao : ImageDao) {
    fun getAll(): Flow<List<ImageEntity>> = dao.getAll()
    suspend fun insert(entity: ImageEntity) = dao.insert(entity)
    suspend fun deleteById(id: Long) = dao.deleteById(id)
}