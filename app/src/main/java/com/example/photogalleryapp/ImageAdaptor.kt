package com.example.photogalleryapp

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.photogalleryapp.databinding.ItemImageBinding
class ImageAdapter(
    private var items: List<ImageEntity>,
    private val onLongClick: (ImageEntity) -> Unit
): RecyclerView.Adapter<ImageAdapter.Holder>() {
    inner class Holder(private val binding: ItemImageBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(entity: ImageEntity) {
            val uri = Uri.parse(entity.uriString)
            Glide.with(binding.imageView.context)
                .load(uri)
                .centerCrop()
                .into(binding.imageView)


            binding.root.setOnLongClickListener {
                onLongClick(entity)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return Holder(binding)
    }


    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position])
    }


    override fun getItemCount(): Int = items.size


    fun update(newItems: List<ImageEntity>) {
        items = newItems
        notifyDataSetChanged()
    }
}