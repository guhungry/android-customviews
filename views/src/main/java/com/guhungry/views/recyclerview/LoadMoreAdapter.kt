package com.guhungry.views.recyclerview

import android.support.v7.widget.RecyclerView

abstract class LoadMoreAdapter<T> : RecyclerView.Adapter<T>() where T : RecyclerView.ViewHolder {
    var isLoadMoreEnabled = true
    var isLoading = false
    private var listener: OnLoadMoreListener? = null

    //////////////////////
    // Lifecycle Functions
    //////////////////////
    override fun onBindViewHolder(holder: T, position: Int) {
        doBindViewHolder(holder, position)

        if (isLastPosition(position) && shouldLoadMore()) listener?.onLoadMore()
    }

    private fun isLastPosition(position: Int) = position == itemCount - 1
    private fun shouldLoadMore() = isLoadMoreEnabled && !isLoading

    fun setOnLoadMoreListener(listener: OnLoadMoreListener) {
        this.listener = listener
    }

    ///////////////////
    // Abstract Methods
    ///////////////////
    abstract fun doBindViewHolder(holder: T, position: Int)
}
