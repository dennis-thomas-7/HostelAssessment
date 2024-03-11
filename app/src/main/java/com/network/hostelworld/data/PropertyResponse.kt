package com.network.hostelworld.data

data class PropertyResponse(
	val pagination: Pagination? = null,
	val filterData: FilterData? = null,
	val sortOrder: Any? = null,
	val locationEn: LocationEn? = null,
	val location: Location? = null,
	val properties: List<PropertiesItem?>? = null
)

data class LocationEn(
	val city: City? = null,
	val region: Any? = null
)

data class StayRuleViolationsItem(
	val description: String? = null
)

data class City(
	val country: String? = null,
	val name: String? = null,
	val id: Int? = null,
	val idCountry: Int? = null
):java.io.Serializable

data class OriginalLowestAveragePrivatePricePerNight(
	val currency: String? = null,
	val value: String? = null
)

data class LowestPrivatePricePerNight(
	val currency: String? = null,
	val value: String? = null
)

data class FilterData(
	val highestPricePerNight: HighestPricePerNight? = null,
	val lowestPricePerNight: LowestPricePerNight? = null
)

data class LowestDormPricePerNight(
	val currency: String? = null,
	val value: String? = null
)

data class ImagesItem(
	val prefix: String? = null,
	val suffix: String? = null
)

data class PropertiesItem(
	val lowestAverageDormPricePerNight: LowestAverageDormPricePerNight? = null,
	val distance: Distance? = null,
	val latitude: Any? = null,
	val districts: List<Any?>? = null,
	val veryPopular: Boolean? = null,
	val fabSort: FabSort? = null,
	val fenceDiscount: Int? = null,
	val type: String? = null,
	val freeCancellation: FreeCancellation? = null,
	val lowestDormPricePerNight: LowestDormPricePerNight? = null,
	val hostelworldRecommends: Boolean? = null,
	val freeCancellationAvailableUntil: String? = null,
	val id: Int? = null,
	val starRating: Int? = null,
	val isFeatured: Boolean? = null,
	val hwExtra: Any? = null,
	val lowestAveragePrivatePricePerNight: LowestAveragePrivatePricePerNight? = null,
	val longitude: Any? = null,
	val originalLowestAveragePricePerNight: OriginalLowestAveragePricePerNight? = null,
	val ratingBreakdown: RatingBreakdown? = null,
	val originalLowestAverageDormPricePerNight: OriginalLowestAverageDormPricePerNight? = null,
	val overview: String? = null,
	val images: List<ImagesItem?>? = null,
	val overallRating: OverallRating? = null,
	val hbid: Int? = null,
	val address2: String? = null,
	val address1: String? = null,
	val isElevate: Boolean? = null,
	val isNew: Boolean? = null,
	val stayRuleViolations: List<StayRuleViolationsItem?>? = null,
	val isPromoted: Boolean? = null,
	val lowestAveragePricePerNight: LowestAveragePricePerNight? = null,
	val promotions: List<PromotionsItem?>? = null,
	val freeCancellationAvailable: Boolean? = null,
	val district: Any? = null,
	val originalLowestAveragePrivatePricePerNight: OriginalLowestAveragePrivatePricePerNight? = null,
	val name: String? = null,
	val position: Int? = null,
	val rateRuleViolations: List<Any?>? = null,
	val imagesGallery: List<ImagesGalleryItem?>? = null,
	val lowestPricePerNight: LowestPricePerNight? = null,
	val facilities: List<FacilitiesItem?>? = null,
	val lowestPrivatePricePerNight: LowestPrivatePricePerNight? = null
)

data class PromotionsItem(
	val stack: Boolean? = null,
	val name: String? = null,
	val discount: Int? = null,
	val id: Int? = null,
	val type: String? = null
)

data class ImagesGalleryItem(
	val prefix: String? = null,
	val suffix: String? = null
):java.io.Serializable

data class FacilitiesItem(
	val name: String? = null,
	val id: String? = null,
	val facilities: List<FacilitiesItem?>? = null
):java.io.Serializable

data class LowestAveragePrivatePricePerNight(
	val promotions: Promotions? = null,
	val original: String? = null,
	val currency: String? = null,
	val value: String? = null
)

data class OriginalLowestAveragePricePerNight(
	val currency: String? = null,
	val value: String? = null
)

data class District(
	val name: String? = null,
	val id: String? = null
)

data class RateRuleViolationsItem(
	val nightsStay: NightsStay? = null
)

data class RatingBreakdown(
	val average: Int? = null,
	val security: Int? = null,
	val ratingsCount: Int? = null,
	val location: Int? = null,
	val staff: Int? = null,
	val clean: Int? = null,
	val facilities: Int? = null,
	val value: Int? = null
)

data class HighestPricePerNight(
	val currency: String? = null,
	val value: String? = null
)

data class Promotions(
	val promotionsIds: List<Int?>? = null,
	val totalDiscount: String? = null
)

data class Pagination(
	val next: String? = null,
	val numberOfPages: Int? = null,
	val prev: String? = null,
	val totalNumberOfItems: Int? = null
)

data class NightsStay(
	val min: Int? = null
)

data class FabSort(
	val rank1: Int? = null,
	val rank2: Int? = null,
	val rank3: Int? = null,
	val rank8: Int? = null,
	val rank9: Int? = null,
	val rank4: Int? = null,
	val rank5: Int? = null,
	val rank6: Int? = null,
	val rank7: Int? = null
)

data class DistrictsItem(
	val name: String? = null,
	val id: Int? = null
)

data class LowestAverageDormPricePerNight(
	val promotions: Promotions? = null,
	val original: String? = null,
	val currency: String? = null,
	val value: String? = null
)

data class Distance(
	val units: String? = null,
	val value: Any? = null
)

data class LowestPricePerNight(
	val currency: String? = null,
	val value: String? = null
)

data class FreeCancellation(
	val description: String? = null,
	val label: String? = null
):java.io.Serializable

data class OverallRating(
	val numberOfRatings: String? = null,
	val overall: Int? = null
)

data class LowestAveragePricePerNight(
	val promotions: Promotions? = null,
	val original: String? = null,
	val currency: String? = null,
	val value: String? = null
)

data class Location(
	val city: City? = null,
	val region: Any? = null
):java.io.Serializable

data class OriginalLowestAverageDormPricePerNight(
	val currency: String? = null,
	val value: String? = null
)

