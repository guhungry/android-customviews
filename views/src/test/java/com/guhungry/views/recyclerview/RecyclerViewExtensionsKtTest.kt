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
    given("RecyclerView.addOnPageChangeListener()") {
        val listener by memoized { MockPageChangeListener() }
        val layoutManager by memoized { mock(LinearLayoutManager::class.java) }
        val recycler by memoized { mock(RecyclerView::class.java) }
        var sut: OnScrollListener? = null

        beforeEachTest {
            `when`(recycler.getLayoutManager()).thenReturn(layoutManager)
            `when`(layoutManager.findFirstVisibleItemPosition()).thenReturn(5)

            sut = recycler.addOnPageChangeListener(listener)
        }
        on("onScrollStateChanged()") {
            it("should not call listener when newState = SCROLL_STATE_DRAGGING") {
                sut?.onScrollStateChanged(recycler, RecyclerView.SCROLL_STATE_DRAGGING)

                assertThat(listener.position, equalTo(0))
            }

            it("should not call listener when newState = SCROLL_STATE_SETTLING") {
                sut?.onScrollStateChanged(recycler, RecyclerView.SCROLL_STATE_SETTLING)

                assertThat(listener.position, equalTo(0))
            }

            it("should call listener when newState = SCROLL_STATE_IDLE") {
                sut?.onScrollStateChanged(recycler, RecyclerView.SCROLL_STATE_IDLE)

                assertThat(listener.position, equalTo(5))
            }
        }
    }
}) {
    class MockPageChangeListener : OnPageChangeListener {
        var position = 0

        override fun onPageSelected(position: Int) {
            this.position = position
        }
    }
}