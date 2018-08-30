package com.guhungry.views.recyclerview

import android.support.annotation.LayoutRes
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.OnScrollListener
import android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guhungry.views.recyclerview.listener.OnPageChangeListener

fun RecyclerView.addOnPageChangeListener(listener: OnPageChangeListener): OnScrollListener {
    val scrollListener = object : OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (newState == SCROLL_STATE_IDLE) {
                val position = (layoutManager as? LinearLayoutManager)?.findFirstVisibleItemPosition() ?: 0
                listener.onPageSelected(position)
            }
        }
    }

    addOnScrollListener(scrollListener)
    return scrollListener
}

fun <T> RecyclerView.Adapter<T>.inflate(parent: ViewGroup, @LayoutRes resId: Int): View? where T : RecyclerView.ViewHolder {
    return LayoutInflater.from(parent.context).inflate(resId, parent, false)
}