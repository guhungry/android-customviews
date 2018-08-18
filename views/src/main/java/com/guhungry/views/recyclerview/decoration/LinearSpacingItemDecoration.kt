package com.guhungry.views.recyclerview.decoration

import android.graphics.Rect
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Orientation
import android.view.View

class LinearSpacingItemDecoration(@Orientation private val orientation: Int, private val spacing: Int, var includeEdge: Boolean) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val count = parent.adapter.itemCount
        val currentOffset = position * spacing / count
        val nextOffset = (position + 1) * spacing / count

        if (includeEdge) {
            when (orientation) {
                LinearLayoutManager.VERTICAL -> {
                    outRect.top = spacing - currentOffset
                    outRect.bottom = nextOffset
                }
                else -> {
                    outRect.left = spacing - currentOffset
                    outRect.right = nextOffset
                }
            }
        } else {
            when (orientation) {
                LinearLayoutManager.VERTICAL -> {
                    outRect.top = currentOffset
                    outRect.bottom = spacing - nextOffset
                }
                else -> {
                    outRect.left = currentOffset
                    outRect.right = spacing - nextOffset
                }
            }
        }
    }
}