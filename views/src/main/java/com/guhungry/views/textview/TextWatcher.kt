package com.guhungry.views.textview

import android.text.Editable
import android.text.TextWatcher

interface SimpleTextWatcher : TextWatcher {
    override fun afterTextChanged(s: Editable?) = Unit
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
}

class OnTextChangedListener(private val listener: (s: CharSequence?, start: Int, before: Int, count: Int) -> Unit) : SimpleTextWatcher {
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) =
            listener.invoke(s, start, before, count)
}

class OnBeforeTextChangedListener(private val listener: (s: CharSequence?, start: Int, count: Int, after: Int) -> Unit) : SimpleTextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
            listener.invoke(s, start, count, after)
}

class OnAfterTextChangedListener(private val listener: (s: Editable?) -> Unit) : SimpleTextWatcher {
    override fun afterTextChanged(s: Editable?) = listener.invoke(s)
}