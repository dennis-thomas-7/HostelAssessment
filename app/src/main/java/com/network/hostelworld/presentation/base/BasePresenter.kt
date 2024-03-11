package com.network.hostelworld.presentation.base

import android.content.Context

class BasePresenter(var context: Context?) {

    var callback : IBase? = null
    init {
        callback = context as IBase
    }

}