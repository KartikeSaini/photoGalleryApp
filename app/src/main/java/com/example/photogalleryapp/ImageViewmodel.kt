package com.example.photogalleryapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
class ImageViewmodel (application: Application): AndroidViewModel(application){
    private val repo: ImageRepository
    val images: StateFlow<List<ImageEntity>>


    init {
        val db = ImageDatabase.getDatabase(application)
        repo = ImageRepository(db.imageDao())
        images = repo.getAll().map { it }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    }


    fun addUri(uriString: String) {
        viewModelScope.launch {
            repo.insert(ImageEntity(uriString = uriString))
        }
    }


    fun delete(id: Long) {
        viewModelScope.launch {
            repo.deleteById(id)
        }
    }
}