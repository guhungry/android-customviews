package com.guhungry.views.textview

import com.guhungry.views.textview.SimpleTextWatcherTest.STRING
import com.guhungry.views.toEditable
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object SimpleTextWatcherTest : Spek({
    val sut = object : SimpleTextWatcher {}
    given("a simple text watcher") {
        on("beforeTextChanged") {
            it("should do nothing when call ") {
                sut.beforeTextChanged(STRING, 1, 2, 3)
            }
        }

        on("onTextChanged") {
            it("should do nothing when call ") {
                sut.onTextChanged(STRING, 1, 2, 3)
            }
        }

        on("afterTextChanged") {
            it("should do nothing when call ") {
                sut.afterTextChanged(STRING.toEditable())
            }
        }
    }
}) {
    private const val STRING = "BEE"
}