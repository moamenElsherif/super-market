package com.app.supermarket.base

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.app.supermarket.R
import com.app.supermarket.domain.models.Product
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.util.Locale

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

@BindingAdapter(value = ["app:setTotalCost"])
fun setTotalCost(textView: TextView, totalCost: Float) {
    textView.text = textView.context.getString(R.string.price_formate, totalCost)
}

@BindingAdapter(value = ["app:setProductName"])
fun setProductName(textView: TextView, product: Product) {
    textView.text = if (Locale.getDefault().language == Constants.arabic) product.localizedName
    else product.productName
}