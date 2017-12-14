package com.example.ricardo.tickit2.data.network.provider

/**
 * Created by Ricardo on 2017/11/23.
 */

abstract class Provider<T> {
    abstract fun creator(): T

    private val instance: T by lazy { creator() }
    var testingInstance: T? = null

    fun get(): T = testingInstance ?: instance
}