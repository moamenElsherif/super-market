package com.app.supermarket.base

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter(value = ["app:imageUrl"])
fun setImageUrl(image: ImageView, completeUrl: String?) {
    Log.e("setImageUrl", "setImageUrl: " + completeUrl)
    val requestOptions = RequestOptions()
    completeUrl?.let {
        Glide.with(image.context)
            .load(it)
            .apply(requestOptions)
            .into(image)
    }
}