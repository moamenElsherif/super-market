package com.app.supermarket.presentation.main.home

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.supermarket.R
import com.app.supermarket.data.models.response.CategoryResponse
import com.app.supermarket.data.models.response.HomeCategoryResponse
import com.app.supermarket.data.models.response.Item


class HomeCategoryAdapter(
    private var items: List<Item>,
) : RecyclerView.Adapter<HomeCategoryAdapter.CourseViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.home_category_item,
            parent, false
        )
        return CourseViewHolder(itemView)
    }

    @SuppressLint("RtlHardcoded")
    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        if(position%2==0){
            val params = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
            params.gravity = Gravity.START
            holder.linear.layoutParams = params
        }
        else{
            val params = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
            params.gravity = Gravity.END
            holder.linear.layoutParams = params
        }
        holder.categoryName.text = items[position].title
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // on below line we are initializing our course name text view and our image view.
        val categoryName: TextView = itemView.findViewById(R.id.tv_category)
        val categoryImage: ImageView = itemView.findViewById(R.id.iv_category)
        val linear: LinearLayout = itemView.findViewById(R.id.linear)
    }
}