package com.network.hostelworld.presentation

import com.network.hostelworld.data.Location
import com.network.hostelworld.domain.Property
import com.network.hostelworld.presentation.base.IBase

interface PropertyContract  {
    interface View:IBase {
        fun showProperties(properties: List<Property>)
        fun showLocation(location: Location)
        fun showError(errorMessage: String)
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun getPropertyList()
        fun onDestroy()
    }
}

