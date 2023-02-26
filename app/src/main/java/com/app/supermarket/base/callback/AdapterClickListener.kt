package com.app.supermarket.base.callback


class AdapterClickListener<T>(val clickListener: (data: T) -> Unit) {
    fun onClick(data: T) = clickListener(data)
}