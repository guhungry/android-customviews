package com.guhungry.views.recyclerview

import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.ViewGroup
import android.widget.LinearLayout
import com.guhungry.views.recyclerview.BaseLoadMoreAdapterTest.POSITION_LAST
import com.guhungry.views.recyclerview.BaseLoadMoreAdapterTest.POSITION_OTHER
import com.guhungry.views.recyclerview.listener.OnLoadMoreListener
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.mockito.Mockito.mock

object BaseLoadMoreAdapterTest : Spek({
    given("a default BaseLoadMoreAdapter") {
        val listener by memoized { MockListener() }
        val adapter by memoized { MockAdapterBase() }
        val holder by memoized { adapter.onCreateViewHolder(mock(LinearLayout::class.java), 0) }

        on("default value") {
            it("isLoading should be false") {
                assertThat(adapter.isLoading, equalTo(false))
            }

            it("isLoadMoreEnabled should be true") {
                assertThat(adapter.isLoadMoreEnabled, equalTo(true))
            }
        }

        on("setOnLoadMoreListener") {
            adapter.setOnLoadMoreListener(listener)

            it("should not call listener when bind at before last position") {
                adapter.onBindViewHolder(holder, POSITION_OTHER)

                assertThat(listener.callCount, equalTo(0))
            }

            it("should call listener when bind at last position") {
                adapter.onBindViewHolder(holder, POSITION_LAST)

                assertThat(listener.callCount, equalTo(1))
            }

            it("should call listener again when bind at last position") {
                adapter.onBindViewHolder(holder, POSITION_LAST)

                assertThat(listener.callCount, equalTo(2))
            }

            it("should not call listener when bind at last position and isLoading = true") {
                adapter.isLoading = true
                adapter.isLoadMoreEnabled = true
                adapter.onBindViewHolder(holder, POSITION_LAST)

                assertThat(listener.callCount, equalTo(2))
            }

            it("should not call listener when bind at last position and isLoadMoreEnabled = false") {
                adapter.isLoading = false
                adapter.isLoadMoreEnabled = false
                adapter.onBindViewHolder(holder, POSITION_LAST)

                assertThat(listener.callCount, equalTo(2))
            }

            it("should call listener again when bind at last position and isLoadMoreEnabled = true and isLoading = false") {
                adapter.isLoading = false
                adapter.isLoadMoreEnabled = true
                adapter.onBindViewHolder(holder, POSITION_LAST)

                assertThat(listener.callCount, equalTo(3))
            }
        }

        on("with no listener") {
            it("should not call listener when bind at before last position") {
                adapter.onBindViewHolder(holder, POSITION_OTHER)

                assertThat(listener.callCount, equalTo(0))
            }

            it("should not call listener when bind at last position") {
                adapter.onBindViewHolder(holder, POSITION_LAST)

                assertThat(listener.callCount, equalTo(0))
            }
        }
    }
}) {
    private const val POSITION_OTHER = 0
    private const val POSITION_LAST = 1

    private class MockAdapterBase : BaseLoadMoreAdapter<ViewHolder>() {
        val value = 2
        override fun doBindViewHolder(holder: ViewHolder, position: Int) = Unit

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = mock(ViewHolder::class.java)

        override fun getItemCount(): Int = value
    }

    private class MockListener : OnLoadMoreListener {
        var callCount = 0

        override fun onLoadMore() {
            callCount++
        }
    }
}