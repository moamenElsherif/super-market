package com.app.supermarket.base

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter(value = ["app:imageUrl"])
fun setImageUrl(image: ImageView, completeUrl: String?) {
    val requestOptions = RequestOptions()
    completeUrl?.let {
        Glide.with(image.context)
            .load(it)
            .apply(requestOptions)
            .into(image)
    }
}

@BindingAdapter(value = ["app:getStringFromFloat"])
fun getStringFromFloat(textView: TextView , float: Float?): String{
    return float.toString()
}