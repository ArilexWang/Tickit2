package com.example.ricardo.tickit2.view.fragment.show

import com.example.ricardo.tickit2.data.model.Show

/**
 * Created by yuhanyin on 1/8/18.
 */
interface ShowFragmentView {
    var refresh: Boolean
    fun show(items: List<Show>)
    fun showError(error: Throwable)
}