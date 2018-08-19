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
            // Start spacing, end spacing
            applyItemOffsets(outRect, spacing - currentOffset, nextOffset)
        } else {
            // Start 0, end 0
            applyItemOffsets(outRect, currentOffset, spacing - nextOffset)
        }
    }

    private fun applyItemOffsets(outRect: Rect, start: Int, end: Int) {
        if (orientation == LinearLayoutManager.VERTICAL) {
            outRect.top = start
            outRect.bottom = end
        } else {
            outRect.left = start
            outRect.right = end
        }
    }
}