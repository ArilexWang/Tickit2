package com.example.ricardo.tickit2.view.admin.detail

import com.example.ricardo.tickit2.data.model.Show

/**
 * Created by Ricardo on 2018/1/11.
 */
interface ShowDetailView{
    fun setShowSuccess(items: List<Show>)
    fun setShowError(error: Throwable)
}