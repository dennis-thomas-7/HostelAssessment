package com.network.hostelworld.domain

import com.network.hostelworld.data.FacilitiesItem
import com.network.hostelworld.data.FreeCancellation
import com.network.hostelworld.data.ImagesGalleryItem


data class Property(
    val name: String,
    val isFeatured: Boolean,
    val rating: Int,
    val numberOfRatings: String?,
    val lowestPrice: String,
    val lowestPriceCurrency: String,
    val images: List<ImagesGalleryItem?>?,
    val overview: String?,
    val freeCancellation: FreeCancellation?,
    val facilities: List<FacilitiesItem?>?
):java.io.Serializable

