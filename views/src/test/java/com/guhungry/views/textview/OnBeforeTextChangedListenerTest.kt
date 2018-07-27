package com.guhungry.views.textview

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object OnBeforeTextChangedListenerTest : Spek({
    given("OnBeforeTextChangedListener") {
        var cValue: CharSequence? = null
        var cStart = 0
        var cCount = 0
        var cAfter = 0
        var callCount = 0
        val lambda: (s: CharSequence?, start: Int, count: Int, after: Int) -> Unit = { s, start, count, after ->
            callCount++
            cValue = s
            cStart = start
            cCount = count
            cAfter = after
        }
        val sut by memoized { OnBeforeTextChangedListener(lambda) }

        on("beforeTextChanged") {
            callCount = 0

            it("should pass correct values when called 1st time") {
                val expected = "BEDA"
                sut.beforeTextChanged(expected, 1, 2, 3)

                MatcherAssert.assertThat(callCount, CoreMatchers.equalTo(1))
                MatcherAssert.assertThat(cValue, CoreMatchers.equalTo<CharSequence>(expected))
                MatcherAssert.assertThat(cStart, CoreMatchers.equalTo(1))
                MatcherAssert.assertThat(cCount, CoreMatchers.equalTo(2))
                MatcherAssert.assertThat(cAfter, CoreMatchers.equalTo(3))
            }

            it("should pass correct values when called 2nd time") {
                val expected = "aeda"
                sut.beforeTextChanged(expected, 3, 1, 2)

                MatcherAssert.assertThat(callCount, CoreMatchers.equalTo(2))
                MatcherAssert.assertThat(cValue, CoreMatchers.equalTo<CharSequence>(expected))
                MatcherAssert.assertThat(cStart, CoreMatchers.equalTo(3))
                MatcherAssert.assertThat(cCount, CoreMatchers.equalTo(1))
                MatcherAssert.assertThat(cAfter, CoreMatchers.equalTo(2))
            }
        }
    }
})