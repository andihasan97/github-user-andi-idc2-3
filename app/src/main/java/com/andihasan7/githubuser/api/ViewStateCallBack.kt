package com.andihasan7.githubuser.api

import android.view.View

interface ViewStateCallBack<T> {
    fun onSuccess(data: T)
    fun onLoading()
    fun onFailed(message: String?)

    val invisible: Int
        get() = View.INVISIBLE

    val visible: Int
        get() = View.VISIBLE
}