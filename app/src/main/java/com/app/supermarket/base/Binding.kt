package com.app.supermarket.base

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.app.supermarket.R
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

@BindingAdapter(value = ["app:setProductPrice"])
fun setProductPrice(textView: TextView, price: Float) {
    textView.text = textView.context.getString(R.string.price_formate, price)
}