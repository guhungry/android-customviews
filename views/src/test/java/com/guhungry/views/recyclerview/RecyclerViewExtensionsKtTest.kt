package com.guhungry.views.recyclerview

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.OnScrollListener
import com.guhungry.views.recyclerview.listener.OnPageChangeListener
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

object RecyclerViewExtensionsKtTest : Spek({
    val listener by memoized { MockPageChangeListener() }
    val recycler by memoized { mock(RecyclerView::class.java) }
    var sut: OnScrollListener? = null

    beforeEachTest {
        sut = recycler.addOnPageChangeListener(listener)
    }
    afterEachTest { sut = null }

    given("RecyclerView.addOnPageChangeListener() with layoutManager") {
        val layoutManager by memoized { mock(LinearLayoutManager::class.java) }

        beforeGroup {
            `when`(layoutManager.findFirstVisibleItemPosition()).thenReturn(5)
            `when`(recycler.getLayoutManager()).thenReturn(layoutManager)
        }

        on("onScrollStateChanged()") {
            it("should not call listener when newState = SCROLL_STATE_DRAGGING") {
                sut?.onScrollStateChanged(recycler, RecyclerView.SCROLL_STATE_DRAGGING)

                assertThat(listener.position, equalTo(-1))
            }

            it("should not call listener when newState = SCROLL_STATE_SETTLING") {
                sut?.onScrollStateChanged(recycler, RecyclerView.SCROLL_STATE_SETTLING)

                assertThat(listener.position, equalTo(-1))
            }

            it("should call listener when newState = SCROLL_STATE_IDLE") {
                sut?.onScrollStateChanged(recycler, RecyclerView.SCROLL_STATE_IDLE)

                assertThat(listener.position, equalTo(5))
            }
        }
    }

    given("RecyclerView.addOnPageChangeListener() without layoutManager") {
        beforeGroup {
            `when`(recycler.getLayoutManager()).thenReturn(null)
        }

        on("onScrollStateChanged()") {
            it("should not call listener when newState = SCROLL_STATE_DRAGGING") {
                sut?.onScrollStateChanged(recycler, RecyclerView.SCROLL_STATE_DRAGGING)

                assertThat(listener.position, equalTo(-1))
            }

            it("should not call listener when newState = SCROLL_STATE_SETTLING") {
                sut?.onScrollStateChanged(recycler, RecyclerView.SCROLL_STATE_SETTLING)

                assertThat(listener.position, equalTo(-1))
            }

            it("should call listener when newState = SCROLL_STATE_IDLE") {
                sut?.onScrollStateChanged(recycler, RecyclerView.SCROLL_STATE_IDLE)

                assertThat(listener.position, equalTo(0))
            }
        }
    }
}) {
    class MockPageChangeListener : OnPageChangeListener {
        var position = -1

        override fun onPageSelected(position: Int) {
            this.position = position
        }
    }
}