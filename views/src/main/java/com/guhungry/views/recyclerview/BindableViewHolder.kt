package com.guhungry.views.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BindableViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bindData(data: T)
    abstract fun onRecycled()
}