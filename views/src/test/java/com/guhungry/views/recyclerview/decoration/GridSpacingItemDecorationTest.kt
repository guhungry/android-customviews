package com.guhungry.views.recyclerview.decoration

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.guhungry.views.recyclerview.decoration.GridSpacingItemDecorationTest.COLUMNS
import com.guhungry.views.recyclerview.decoration.GridSpacingItemDecorationTest.SPACING
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

object GridSpacingItemDecorationTest : Spek({
    val outRect by memoized { Rect(0, 0, 0, 0) }
    val view by memoized { mock(View::class.java) }
    val recycler by memoized { mock(RecyclerView::class.java) }
    val state by memoized { mock(RecyclerView.State::class.java) }

    given("GridSpacingItemDecoration with $COLUMNS columns, spacing = $SPACING, includeEdge") {
        val sut = GridSpacingItemDecoration(COLUMNS, SPACING, true)

        on("position 1 of $COLUMNS") {
            `when`(recycler.getChildAdapterPosition(view)).thenReturn(0)
            sut.getItemOffsets(outRect, view, recycler, state)

            it("should have correct ourRect") {
                assertThat(outRect.top, equalTo(SPACING))
                assertThat(outRect.left, equalTo(SPACING))
                assertThat(outRect.right, equalTo(3))
                assertThat(outRect.bottom, equalTo(SPACING))
            }
        }

        on("position 2 of $COLUMNS") {
            `when`(recycler.getChildAdapterPosition(view)).thenReturn(1)
            sut.getItemOffsets(outRect, view, recycler, state)

            it("should have correct ourRect") {
                assertThat(outRect.top, equalTo(SPACING))
                assertThat(outRect.left, equalTo(9))
                assertThat(outRect.right, equalTo(6))
                assertThat(outRect.bottom, equalTo(SPACING))
            }
        }

        on("position 3 of $COLUMNS") {
            `when`(recycler.getChildAdapterPosition(view)).thenReturn(2)
            sut.getItemOffsets(outRect, view, recycler, state)

            it("should have correct ourRect") {
                assertThat(outRect.top, equalTo(SPACING))
                assertThat(outRect.left, equalTo(6))
                assertThat(outRect.right, equalTo(9))
                assertThat(outRect.bottom, equalTo(SPACING))
            }
        }

        on("position 4 of $COLUMNS") {
            `when`(recycler.getChildAdapterPosition(view)).thenReturn(3)
            sut.getItemOffsets(outRect, view, recycler, state)

            it("should have correct ourRect") {
                assertThat(outRect.top, equalTo(SPACING))
                assertThat(outRect.left, equalTo(3))
                assertThat(outRect.right, equalTo(SPACING))
                assertThat(outRect.bottom, equalTo(SPACING))
            }
        }

        on("next row") {
            `when`(recycler.getChildAdapterPosition(view)).thenReturn(4)
            sut.getItemOffsets(outRect, view, recycler, state)

            it("must not have top spacing") {
                assertThat(outRect.top, equalTo(0))
            }
        }
    }

    given("GridSpacingItemDecoration with $COLUMNS columns, spacing = $SPACING, noEdge") {
        val sut = GridSpacingItemDecoration(COLUMNS, SPACING, false)

        on("position 1 of $COLUMNS") {
            `when`(recycler.getChildAdapterPosition(view)).thenReturn(0)
            sut.getItemOffsets(outRect, view, recycler, state)

            it("should have correct ourRect") {
                assertThat(outRect.top, equalTo(0))
                assertThat(outRect.left, equalTo(0))
                assertThat(outRect.right, equalTo(9))
                assertThat(outRect.bottom, equalTo(0))
            }
        }

        on("position 2 of $COLUMNS") {
            `when`(recycler.getChildAdapterPosition(view)).thenReturn(1)
            sut.getItemOffsets(outRect, view, recycler, state)

            it("should have correct ourRect") {
                assertThat(outRect.top, equalTo(0))
                assertThat(outRect.left, equalTo(3))
                assertThat(outRect.right, equalTo(6))
                assertThat(outRect.bottom, equalTo(0))
            }
        }

        on("position 3 of $COLUMNS") {
            `when`(recycler.getChildAdapterPosition(view)).thenReturn(2)
            sut.getItemOffsets(outRect, view, recycler, state)

            it("should have correct ourRect") {
                assertThat(outRect.top, equalTo(0))
                assertThat(outRect.left, equalTo(6))
                assertThat(outRect.right, equalTo(3))
                assertThat(outRect.bottom, equalTo(0))
            }
        }

        on("position 4 of $COLUMNS") {
            `when`(recycler.getChildAdapterPosition(view)).thenReturn(3)
            sut.getItemOffsets(outRect, view, recycler, state)

            it("should have correct ourRect") {
                assertThat(outRect.top, equalTo(0))
                assertThat(outRect.left, equalTo(9))
                assertThat(outRect.right, equalTo(0))
                assertThat(outRect.bottom, equalTo(0))
            }
        }

        on("next row") {
            `when`(recycler.getChildAdapterPosition(view)).thenReturn(4)
            sut.getItemOffsets(outRect, view, recycler, state)

            it("must have top spacing") {
                assertThat(outRect.top, equalTo(SPACING))
            }
        }
    }
}) {
    private const val COLUMNS = 4
    private const val SPACING = 12
}