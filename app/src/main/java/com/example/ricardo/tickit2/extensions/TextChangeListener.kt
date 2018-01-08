package com.example.ricardo.tickit2.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView

/**
 * Created by Ricardo on 2018/1/9.
 */

fun TextView.addOnTextChangedListener(config: TextwatcherConfiguration.() -> Unit) {
    addTextChangedListener(TextwatcherConfiguration().apply { config() })
}

class TextwatcherConfiguration: TextWatcher {
    private var berforeTextChangedCallback: (BeforeTextChangedFunction)? = null
    private var onTextChangedCallback: (OnTextChangeFunction)? = null
    private var afterTextChangedCallback: (AfterTextChangedFunction)? = null

    fun beforeTextChanged(callback: BeforeTextChangedFunction) { berforeTextChangedCallback = callback}
    fun onTextChanged(callback: OnTextChangeFunction) { onTextChangedCallback = callback}
    fun afterTextChanged(callback: AfterTextChangedFunction) { afterTextChangedCallback = callback}

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        berforeTextChangedCallback?.invoke(s.toString(),start, count, after)
    }


    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onTextChangedCallback?.invoke(s.toString(), start, before, count)
    }


    override fun afterTextChanged(s: Editable) {
        afterTextChangedCallback?.invoke(s)
    }


}

private typealias BeforeTextChangedFunction = (text: String, start: Int, Count: Int, after: Int) -> Unit
private typealias OnTextChangeFunction = (text: String, start: Int, before:Int, Count: Int) -> Unit
private typealias AfterTextChangedFunction = (s: Editable) -> Unit