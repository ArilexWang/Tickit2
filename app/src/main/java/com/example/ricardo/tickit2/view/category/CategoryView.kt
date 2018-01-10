package com.example.ricardo.tickit2.view.category

import com.example.ricardo.tickit2.data.model.Show

/**
 * Created by Ricardo on 2018/1/10.
 */
interface CategoryView{
    fun onShowSuccess(items: List<Show>)
    fun onShowError(error: Throwable)

}