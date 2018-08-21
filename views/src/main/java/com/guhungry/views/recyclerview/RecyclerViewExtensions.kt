package com.guhungry.views.recyclerview

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.OnScrollListener
import android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE
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