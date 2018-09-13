package com.guhungry.views.recyclerview

import android.view.View
import android.view.ViewGroup
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.mockito.Mockito.mock
import com.guhungry.views.recyclerview.BaseSectionedDataAdapterTest.MockAdapterBase.Header
import com.guhungry.views.recyclerview.BaseSectionedDataAdapterTest.MockAdapterBase.Item

object BaseSectionedDataAdapterTest : Spek({
    given("a default BaseSectionedDataAdapter") {
        val adapter by memoized { MockAdapterBase() }

        it("itemCount should be 0") {
            assertThat(adapter.itemCount, equalTo(0))
        }
    }

    given("a BaseSectionedDataAdapter with 5 items and 2 sections") {
        val adapter by memoized { MockAdapterBase().apply { setMockItems() } }
        val parent = mock(ViewGroup::class.java)

        it("itemCount should be 7") {
            assertThat(adapter.itemCount, equalTo(7))
        }

        on("getItemViewType") {
            it("should return correct item type") {
                assertThat(adapter.getItemViewType(0), equalTo(BaseSectionedDataAdapter.TYPE_SECTION_HEADER))
                assertThat(adapter.getItemViewType(1), equalTo(BaseSectionedDataAdapter.TYPE_ITEM))
                assertThat(adapter.getItemViewType(2), equalTo(BaseSectionedDataAdapter.TYPE_ITEM))
                assertThat(adapter.getItemViewType(3), equalTo(BaseSectionedDataAdapter.TYPE_ITEM))
                assertThat(adapter.getItemViewType(4), equalTo(BaseSectionedDataAdapter.TYPE_ITEM))
                assertThat(adapter.getItemViewType(5), equalTo(BaseSectionedDataAdapter.TYPE_SECTION_HEADER))
                assertThat(adapter.getItemViewType(6), equalTo(BaseSectionedDataAdapter.TYPE_ITEM))
            }
        }

        on("onCreateViewHolder") {
            it("should create currect view holder") {
                assertThat(adapter.onCreateViewHolder(parent, BaseSectionedDataAdapter.TYPE_SECTION_HEADER), instanceOf(Header::class.java))
                assertThat(adapter.onCreateViewHolder(parent, BaseSectionedDataAdapter.TYPE_ITEM), instanceOf(Item::class.java))
            }
        }

        on("onBindViewHolder with header") {
            it("should return bind with correct data") {
                val header = adapter.onCreateViewHolder(parent, BaseSectionedDataAdapter.TYPE_SECTION_HEADER) as Header

                adapter.onBindViewHolder(header, 0)
                assertThat(header.data, equalTo("0"))
                adapter.onBindViewHolder(header, 5)
                assertThat(header.data, equalTo("1"))
            }
        }

        on("onBindViewHolder with item") {
            it("should return bind with correct data") {
                val header = adapter.onCreateViewHolder(parent, BaseSectionedDataAdapter.TYPE_ITEM) as Item

                adapter.onBindViewHolder(header, 1)
                assertThat(header.data, equalTo(0))
                adapter.onBindViewHolder(header, 2)
                assertThat(header.data, equalTo(1))
                adapter.onBindViewHolder(header, 3)
                assertThat(header.data, equalTo(2))
                adapter.onBindViewHolder(header, 4)
                assertThat(header.data, equalTo(3))
                adapter.onBindViewHolder(header, 6)
                assertThat(header.data, equalTo(4))
            }
        }
    }
}) {
    internal class MockAdapterBase : BaseSectionedDataAdapter<Int>() {
        fun setMockItems() = generateSectionData(0 until 5)

        override fun privateNotifyDataSetChanged() = Unit

        override fun toSectionHeader(item: Int) = (item / 4).toString()

        override fun onCreateHeaderViewHolder(parent: ViewGroup): BindableViewHolder<String> {
            return Header(mock(View::class.java))
        }

        override fun onCreateItemViewHolder(parent: ViewGroup): BindableViewHolder<Int> {
            return Item(mock(View::class.java))
        }

        class Header(itemView: View) : BindableViewHolder<String>(itemView) {
            lateinit var data: String

            override fun bindData(data: String) {
                this.data = data
            }

            override fun onRecycled() = Unit
        }

        class Item(itemView: View) : BindableViewHolder<Int>(itemView) {
            var data: Int = -1

            override fun bindData(data: Int) {
                this.data = data
            }

            override fun onRecycled() = Unit
        }
    }
}