package com.network.hostelworld.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.network.hostelworld.R
import com.network.hostelworld.presentation.ISplashMainView
import com.network.hostelworld.presentation.propertylisting.PropertyActivity

class SplashMainActivity : AppCompatActivity(), ISplashMainView {

    private val timeOut: Long = 2000
    private val mSplashPresenter by lazy { SplashMainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mSplashPresenter.startTimer(timeOut)
    }

    override fun ChangeScreen(){
        startActivity(Intent (this, PropertyActivity::class.java))
        finish()
    }

}