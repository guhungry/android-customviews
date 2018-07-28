package com.guhungry.views.recyclerview.decoration

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int, private val includeEdge: Boolean) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view) // item position
        val column = position % spanCount // item column
        val currentOffset = column * spacing / spanCount
        val nextOffset = (column + 1) * spacing / spanCount

        if (includeEdge) {
            outRect.left = spacing - currentOffset // start spacing
            outRect.right = nextOffset // end spacing

            if (isFirstRow(position)) outRect.top = spacing
            outRect.bottom = spacing
        } else {
            outRect.left = currentOffset // start 0
            outRect.right = spacing - nextOffset // end 0

            if (notFirstRow(position)) outRect.top = spacing
        }
    }

    private fun isFirstRow(position: Int) = position < spanCount
    private fun notFirstRow(position: Int) = !isFirstRow(position)
}