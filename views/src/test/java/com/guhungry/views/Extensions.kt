package com.guhungry.views

import android.text.Editable
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

fun String.toEditable() = mock(Editable::class.java).apply {
    `when`(toString()).thenReturn(this@toEditable)
}