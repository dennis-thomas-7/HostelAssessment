package com.network.hostelworld.presentation.propertydetail

import android.content.Context
import com.network.hostelworld.data.*
import com.network.hostelworld.presentation.PropertyDetailContract
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class PropertyDetailPresenter(private val repository: PropertyDetailRepository, val context :Context) :
    PropertyDetailContract.Presenter {

    private val disposables = CompositeDisposable()

    var callback: PropertyDetailContract.View? = null

    init {
        callback = context as PropertyDetailContract.View
    }

    override fun attachDetailView(view: PropertyDetailContract.View) {
        this.callback = view
    }

    override fun detachDetailView() {
        this.callback = null
    }

    override fun getCurrencyData(){
        callback!!.showLoading()
        val disposable = repository.getCurrencyData()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ response ->
                response.rates?.let {
                    val ratesItem = Rates(
                        EUR = it.EUR,
                        USD = it.USD,
                        GBP = it.GBP
                    )
                    callback!!.hideLoading()
                    callback?.showCurrencies(ratesItem)
                }?: callback!!.hideLoading()
                    callback?.showDetailError("No currencies found")

            }, { error ->
                callback!!.hideLoading()
                error.printStackTrace()
                callback?.showDetailError("Error fetching currencies: ${error.message}")
            })

        disposables.add(disposable)
    }

    override fun onDestroy(){
        disposables.clear()
    }

}