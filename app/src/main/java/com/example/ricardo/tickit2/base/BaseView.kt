package com.example.ricardo.tickit2.base

/**
 * Created by Ricardo on 2017/11/10.
 */

interface BaseView<T> {
    fun setPresenter(presenter: T)
}