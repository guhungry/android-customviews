package com.guhungry.views.recyclerview.decoration

import android.graphics.Rect
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

private const val ITEMS = 4
private const val SPACING = 12
private const val ORIENTATION = LinearLayoutManager.HORIZONTAL

object LinearSpacingItemDecorationTest : Spek({
    val outRect by memoized { Rect(0, 0, 0, 0) }
    val view by memoized { mock(View::class.java) }
    val recycler by memoized { mock(RecyclerView::class.java) }
    val state by memoized { mock(RecyclerView.State::class.java) }

    beforeEachTest {
        `when`(recycler.getAdapter()).thenReturn(null)
    }

    given("LinearSpacingItemDecoration with no items, spacing = $SPACING, includeEdge") {
        val sut = LinearSpacingItemDecoration(ORIENTATION, SPACING, true)

        on("position 1 of $ITEMS") {
            getItemOffsetForPosition(0, recycler, view, sut, outRect, state)

            it("should not throw error") {
                assertThat(true, equalTo(true))
            }
        }
    }
})

private fun getItemOffsetForPosition(position: Int, recycler: RecyclerView, view: View, sut: RecyclerView.ItemDecoration, outRect: Rect, state: RecyclerView.State) {
    `when`(recycler.getChildAdapterPosition(view)).thenReturn(position)
    sut.getItemOffsets(outRect, view, recycler, state)
}