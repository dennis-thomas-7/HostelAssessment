package com.network.hostelworld.presentation

import com.network.hostelworld.data.Rates
import com.network.hostelworld.presentation.base.IBase

interface PropertyDetailContract {
    interface View : IBase {
        fun showCurrencies(currencies: Rates)
        fun showDetailError(errorMessage: String)
    }

    interface Presenter {
        fun attachDetailView(view: View)
        fun detachDetailView()
        fun getCurrencyData()
        fun onDestroy()
    }
}