package com.network.hostelworld.presentation.propertylisting

import android.content.Context
import com.network.hostelworld.data.Location
import com.network.hostelworld.data.PropertyRepository
import com.network.hostelworld.domain.Property
import com.network.hostelworld.presentation.PropertyContract
import com.network.hostelworld.presentation.base.IBase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class PropertyPresenter(private val repository: PropertyRepository, val context: Context) : PropertyContract.Presenter {

    private val disposables = CompositeDisposable()

    var callback: PropertyContract.View? = null

    init {
        callback = context as PropertyContract.View
    }


    override fun attachView(view: PropertyContract.View) {
        this.callback = view
    }

    override fun detachView() {
        this.callback = null
    }

    override fun getPropertyList() {
        callback!!.showLoading()
        val disposable = repository.getProperties()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ response ->

                response.properties?.let { propertiesList ->
                    val properties = propertiesList.mapNotNull { propertyDto ->
                        propertyDto?.let {
                            Property(
                                name = it.name ?: "",
                                isFeatured = it.isFeatured ?: false,
                                rating = it.overallRating?.overall ?: 0,
                                numberOfRatings = it.overallRating?.numberOfRatings,
                                lowestPrice = it.lowestPricePerNight?.value?: "",
                                lowestPriceCurrency = it.lowestPricePerNight?.currency?: "",
                                images = it.imagesGallery,
                                overview = it.overview,
                                freeCancellation = it.freeCancellation,
                                facilities = it.facilities
                            )
                        }
                    }
                    callback!!.hideLoading()
                    callback?.showProperties(properties)
                } ?:callback!!.hideLoading()
                    callback?.showError("No properties found")

                response.location?.let {
                   val location = Location(
                        city = it.city,
                        region = it.region
                    )
                    callback!!.hideLoading()
                    callback?.showLocation(location)
                }?: callback!!.hideLoading()
                    callback?.showError("No location found")
            }, { error ->
                callback!!.hideLoading()
                error.printStackTrace()
                callback?.showError("Error fetching properties: ${error.message}")
            })

        disposables.add(disposable)
    }

    override fun onDestroy(){
        disposables.clear()
    }
}

