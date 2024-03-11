package com.network.hostelworld.data

import com.network.hostelworld.domain.PropertyApiService
import io.reactivex.rxjava3.core.Single

class PropertyRepository(private val apiService: PropertyApiService) {

    fun getProperties(): Single<PropertyResponse> {
        return apiService.getProperties()
    }

}

