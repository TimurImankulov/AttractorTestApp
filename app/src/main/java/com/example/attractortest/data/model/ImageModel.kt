package com.example.attractortest.data.model

import androidx.recyclerview.widget.DiffUtil


data class ImageModel(
    var image: String
) {
    companion object {
        val DIFF_UTIL_CALLBACK = object : DiffUtil.ItemCallback<ImageModel>() {
            override fun areContentsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean =
                oldItem == newItem
        }
    }
}