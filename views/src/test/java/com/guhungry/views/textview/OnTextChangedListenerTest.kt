package com.guhungry.views.textview

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object OnTextChangedListenerTest : Spek({
    given("OnTextChangedListener") {
        var cValue: CharSequence? = null
        var cStart = 0
        var cBefore = 0
        var cCount = 0
        var callCount = 0
        val lambda: (s: CharSequence?, start: Int, before: Int, count: Int) -> Unit = { s, start, before, count ->
            callCount++
            cValue = s
            cStart = start
            cBefore = before
            cCount = count
        }
        val sut by memoized { OnTextChangedListener(lambda) }

        on("onTextChanged") {
            callCount = 0

            it("should pass correct values when called 1st time") {
                val expected = "BEDA"
                sut.onTextChanged(expected, 1, 2, 3)

                assertThat(callCount, equalTo(1))
                assertThat(cValue, equalTo<CharSequence>(expected))
                assertThat(cStart, equalTo(1))
                assertThat(cBefore, equalTo(2))
                assertThat(cCount, equalTo(3))
            }

            it("should pass correct values when called 2nd time") {
                val expected = "aeda"
                sut.onTextChanged(expected, 3, 1, 2)

                assertThat(callCount, equalTo(2))
                assertThat(cValue, equalTo<CharSequence>(expected))
                assertThat(cStart, equalTo(3))
                assertThat(cBefore, equalTo(1))
                assertThat(cCount, equalTo(2))
            }
        }
    }
})