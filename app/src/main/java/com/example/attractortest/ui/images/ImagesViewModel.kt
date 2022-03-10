package com.example.attractortest.ui.images

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.attractortest.data.model.ImageModel
import com.example.attractortest.utils.ImagesGallery
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ImagesViewModel : ViewModel(), KoinComponent {

    private val androidContext: Context by inject()

    private val _galleryImagesLiveData = MutableLiveData<ArrayList<ImageModel>>()
    val galleryImagesLiveData: LiveData<ArrayList<ImageModel>> get() = _galleryImagesLiveData

    fun getGalleryImages() {
        val imageArray = ImagesGallery.listOfImages(androidContext)
        _galleryImagesLiveData.value = imageArray
    }
}