package com.thegalos.giphy

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

/**
 * Created by Gal Reshef on 2/22/2022.
 */

@BindingAdapter("imageUrl")
fun setImageUrl(imgView: ImageView, imgUrl: String?){

    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .asGif()
            .load(imgUri)
            .placeholder(R.drawable.ic_baseline_add_24)
            .error(R.drawable.ic_baseline_add_24)
            .fallback(R.drawable.ic_baseline_add_24)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imgView)
    }
}