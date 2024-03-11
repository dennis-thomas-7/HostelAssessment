package com.network.hostelworld.data

import java.io.IOException

class NoConnectivityException : IOException() {
    companion object {
        val errorMessage = "No network connection available. Please check your internet connection."
    }
    override val message: String
        get() = errorMessage
}