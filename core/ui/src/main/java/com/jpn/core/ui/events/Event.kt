package com.jpn.core.ui.events

class Event<out T>(private val content: T) {
    private var handled = false

    fun getContentIfNotHandled(): T? {
        return if (handled) null else {
            handled = true
            content
        }
    }

    fun peekContent(): T = content
}