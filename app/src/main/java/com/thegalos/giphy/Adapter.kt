package com.thegalos.giphy

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thegalos.giphy.data.Gif
import com.thegalos.giphy.databinding.ItemGifBinding
import timber.log.Timber

/**
 * Created by Gal Reshef on 2/22/2022.
 */
class Adapter(private val gifClickListener: GifClickListener) :
    ListAdapter<Gif, Adapter.ViewHolder>(GifDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, gifClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class GifClickListener(val gifClickListener: (gif: Gif) -> Unit) {
        fun onClick(gif: Gif) = gifClickListener(gif)
    }

    class ViewHolder private constructor(val binding: ItemGifBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Gif, clickListener: GifClickListener) {
            itemView.context.resources
            Timber.i("bind view holder, gif title is: ${item.title}")
            binding.clickListener = clickListener
            binding.item = item
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemGifBinding
                    .inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class GifDiffCallback : DiffUtil.ItemCallback<Gif>() {
        override fun areItemsTheSame(oldItem: Gif, newItem: Gif): Boolean {
            return oldItem.title == newItem.title
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Gif, newItem: Gif): Boolean {
            return oldItem == newItem
        }
    }
}
