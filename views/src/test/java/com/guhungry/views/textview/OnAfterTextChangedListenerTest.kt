package com.guhungry.views.textview

import android.text.Editable
import com.guhungry.views.toEditable
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object OnAfterTextChangedListenerTest : Spek({
    given("OnAfterTextChangedListener") {
        var cValue: CharSequence? = null
        var callCount = 0
        val lambda: (s: Editable?) -> Unit = {
            callCount++
            cValue = it
        }
        val sut by memoized { OnAfterTextChangedListener(lambda) }

        on("afterTextChanged") {
            callCount = 0

            it("should pass correct values when called 1st time") {
                val expected = "BEDA".toEditable()
                sut.afterTextChanged(expected)

                MatcherAssert.assertThat(callCount, CoreMatchers.equalTo(1))
                MatcherAssert.assertThat(cValue, CoreMatchers.equalTo<CharSequence>(expected))
            }

            it("should pass correct values when called 2nd time") {
                val expected = "aeda".toEditable()
                sut.afterTextChanged(expected)

                MatcherAssert.assertThat(callCount, CoreMatchers.equalTo(2))
                MatcherAssert.assertThat(cValue, CoreMatchers.equalTo<CharSequence>(expected))
            }
        }
    }
})