package com.example.attractortest.ui.images

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.attractortest.R
import com.example.attractortest.data.model.ImageModel
import com.example.attractortest.databinding.ItemImageRecyclerviewBinding

class ImageListAdapter : ListAdapter<ImageModel, ImageViewHolder>(
    ImageModel.DIFF_UTIL_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ImageViewHolder(private val binding: ItemImageRecyclerviewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ImageModel) {
        Glide.with(itemView)
            .load(item.image)
            .placeholder(R.color.item_color)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .into(binding.ivPhoto)
    }

    companion object {
        fun create(parent: ViewGroup) = ImageViewHolder(
            ItemImageRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }
}