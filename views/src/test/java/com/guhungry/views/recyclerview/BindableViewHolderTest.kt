package com.guhungry.views.recyclerview

import android.view.View
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.mockito.Mockito.mock

class BindableViewHolderTest : Spek({
    given("a default BaseLoadMoreAdapter") {
        val view by memoized { mock(View::class.java) }
        val sut by memoized { MockViewHolder(view) }

        on("bindData") {
            val expected = 555
            sut.bindData(expected)

            it("should recieve correct data") {
                assertThat(sut.data, equalTo(expected))
            }
        }

        on("onRecycled") {
            sut.onRecycled()

            it("should recieve correct data") {
                assertThat(sut.countRecycledCalled, equalTo(1))
            }
        }
    }
}) {
    private class MockViewHolder(view: View) : BindableViewHolder<Int>(view) {
        var data = 0
        var countRecycledCalled = 0

        override fun bindData(data: Int) {
            this.data = data
        }

        override fun onRecycled() {
            countRecycledCalled++
        }

    }
}