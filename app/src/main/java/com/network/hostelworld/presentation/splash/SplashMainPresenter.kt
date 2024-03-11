package com.network.hostelworld.presentation.splash

import com.network.hostelworld.presentation.ISplashMainView
import com.network.hostelworld.presentation.ISplashMainPresenter


class SplashMainPresenter (private val view: ISplashMainView): ISplashMainPresenter {

    override fun startTimer(time: Long) {
        android.os.Handler().postDelayed({
            view.ChangeScreen()
        }, time)
    }
}