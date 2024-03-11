package com.network.hostelworld.presentation.base

import android.app.Dialog
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.network.hostelworld.R

open class BaseActivity : AppCompatActivity(),IBase  {

    var basePresenter : BasePresenter? = null
    var progressDialog: ProgressDialog? = null

    private val dialog: Dialog by lazy {
        Dialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        basePresenter = BasePresenter(this)

        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage("Loading...")
    }

    override fun showLoading() {
        dialog.setContentView(R.layout.progress_dialog)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    override fun hideLoading() {
        dialog.cancel()
    }


}