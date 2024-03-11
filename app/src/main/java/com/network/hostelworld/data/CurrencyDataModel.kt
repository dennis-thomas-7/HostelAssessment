package com.network.hostelworld.data

class CurrencyDataModel(var access_key: String = "" )

data class CurrencyDataResponse(val date: String? = null,
                                val success: Boolean? = null,
                                val rates: Rates? = null,
                                val timestamp: Int? = null,
                                val base: String? = null)

data class Rates(val EUR : Double?= null, val GBP: Double?= null, val USD: Double?= null)