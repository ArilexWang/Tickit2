package com.example.ricardo.tickit2.view.common

/**
 * Created by Ricardo on 2018/1/4.
 */

class ZeroChildException : Exception {

    constructor() {

    }

    constructor(errorMessage: String) : super(errorMessage) {}

    companion object {

        private val serialVersionUID = 1L
    }

}