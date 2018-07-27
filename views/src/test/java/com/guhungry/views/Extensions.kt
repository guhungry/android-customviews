package com.guhungry.views

import android.text.Editable

fun String.toEditable() = Editable.Factory.getInstance().newEditable(this)