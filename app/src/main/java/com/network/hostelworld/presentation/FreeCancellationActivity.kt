package com.network.hostelworld.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.network.hostelworld.R
import kotlinx.android.synthetic.main.activity_free_cancellation.*

class FreeCancellationActivity : AppCompatActivity() {

    var freeCancel:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_free_cancellation)
        freeCancel = intent.getStringExtra("cancellation_data").toString()
        tvFreeCancellationDesc.text = freeCancel

        imgFreeCancelBackArrow.setOnClickListener {
            super.onBackPressed()
        }
    }

}