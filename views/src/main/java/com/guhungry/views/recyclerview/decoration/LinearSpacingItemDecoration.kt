package com.guhungry.views.recyclerview.decoration

import android.graphics.Rect
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

class LinearSpacingItemDecoration(private val orientation: Int, private val spacing: Int, private val includeEdge: Boolean) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val count = parent.adapter.itemCount

        if (includeEdge) {
            when (orientation) {
                LinearLayoutManager.VERTICAL -> {
                    if (isFirstItem(position)) outRect.top = spacing
                    outRect.bottom = spacing
                }
                else -> {
                    if (isFirstItem(position)) outRect.left = spacing
                    outRect.right = spacing
                }
            }
        } else {
            when (orientation) {
                LinearLayoutManager.VERTICAL -> {
                    if (notFirstItem(position)) outRect.top = spacing
                    if (notLastItem(position, count)) outRect.bottom = spacing
                }
                else -> {
                    if (notFirstItem(position)) outRect.left = spacing
                    if (notLastItem(position, count)) outRect.right = spacing
                }
            }
        }
    }

    private fun isFirstItem(position: Int) = position == 0
    private fun notFirstItem(position: Int) = !isFirstItem(position)
    private fun notLastItem(position: Int, count: Int) = position < count - 1
}