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

object HorizontalLinearSpacingItemDecorationTest : Spek({
    val outRect by memoized { Rect(0, 0, 0, 0) }
    val view by memoized { mock(View::class.java) }
    val adapter by memoized { mock(RecyclerView.Adapter::class.java) }
    val recycler by memoized { mock(RecyclerView::class.java) }
    val state by memoized { mock(RecyclerView.State::class.java) }

    beforeEachTest {
        `when`(recycler.getAdapter()).thenReturn(adapter)
        `when`(adapter.getItemCount()).thenReturn(ITEMS)
    }

    given("Horizontal LinearSpacingItemDecoration with $ITEMS items, spacing = $SPACING, includeEdge") {
        val sut = LinearSpacingItemDecoration(ORIENTATION, SPACING, true)

        on("position 1 of $ITEMS") {
            getItemOffsetForPosition(0, recycler, view, sut, outRect, state)

            it("should have correct ourRect") {
                assertThat(outRect.top, equalTo(0))
                assertThat(outRect.left, equalTo(SPACING))
                assertThat(outRect.right, equalTo(3))
                assertThat(outRect.bottom, equalTo(0))
            }
        }

        on("position 2 of $ITEMS") {
            getItemOffsetForPosition(1, recycler, view, sut, outRect, state)

            it("should have correct ourRect") {
                assertThat(outRect.top, equalTo(0))
                assertThat(outRect.left, equalTo(9))
                assertThat(outRect.right, equalTo(6))
                assertThat(outRect.bottom, equalTo(0))
            }
        }

        on("position 3 of $ITEMS") {
            getItemOffsetForPosition(2, recycler, view, sut, outRect, state)

            it("should have correct ourRect") {
                assertThat(outRect.top, equalTo(0))
                assertThat(outRect.left, equalTo(6))
                assertThat(outRect.right, equalTo(9))
                assertThat(outRect.bottom, equalTo(0))
            }
        }

        on("position 4 of $ITEMS") {
            getItemOffsetForPosition(3, recycler, view, sut, outRect, state)

            it("should have correct ourRect") {
                assertThat(outRect.top, equalTo(0))
                assertThat(outRect.left, equalTo(3))
                assertThat(outRect.right, equalTo(SPACING))
                assertThat(outRect.bottom, equalTo(0))
            }
        }
    }

    given("Horizontal LinearSpacingItemDecoration with $ITEMS items, spacing = $SPACING, noEdge") {
        val sut = LinearSpacingItemDecoration(ORIENTATION, SPACING, false)

        on("position 1 of $ITEMS") {
            getItemOffsetForPosition(0, recycler, view, sut, outRect, state)

            it("should have correct ourRect") {
                assertThat(outRect.top, equalTo(0))
                assertThat(outRect.left, equalTo(0))
                assertThat(outRect.right, equalTo(9))
                assertThat(outRect.bottom, equalTo(0))
            }
        }

        on("position 2 of $ITEMS") {
            getItemOffsetForPosition(1, recycler, view, sut, outRect, state)

            it("should have correct ourRect") {
                assertThat(outRect.top, equalTo(0))
                assertThat(outRect.left, equalTo(3))
                assertThat(outRect.right, equalTo(6))
                assertThat(outRect.bottom, equalTo(0))
            }
        }

        on("position 3 of $ITEMS") {
            getItemOffsetForPosition(2, recycler, view, sut, outRect, state)

            it("should have correct ourRect") {
                assertThat(outRect.top, equalTo(0))
                assertThat(outRect.left, equalTo(6))
                assertThat(outRect.right, equalTo(3))
                assertThat(outRect.bottom, equalTo(0))
            }
        }

        on("position 4 of $ITEMS") {
            getItemOffsetForPosition(3, recycler, view, sut, outRect, state)

            it("should have correct ourRect") {
                assertThat(outRect.top, equalTo(0))
                assertThat(outRect.left, equalTo(9))
                assertThat(outRect.right, equalTo(0))
                assertThat(outRect.bottom, equalTo(0))
            }
        }
    }
})

private fun getItemOffsetForPosition(position: Int, recycler: RecyclerView, view: View, sut: RecyclerView.ItemDecoration, outRect: Rect, state: RecyclerView.State) {
    `when`(recycler.getChildAdapterPosition(view)).thenReturn(position)
    sut.getItemOffsets(outRect, view, recycler, state)
}