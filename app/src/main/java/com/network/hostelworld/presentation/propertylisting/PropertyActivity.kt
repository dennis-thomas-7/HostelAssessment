package com.network.hostelworld.presentation.propertylisting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.network.hostelworld.R
import com.network.hostelworld.data.Location
import com.network.hostelworld.data.PropertyRepository
import com.network.hostelworld.domain.Property
import com.network.hostelworld.domain.PropertyApiService
import com.network.hostelworld.presentation.PropertyAdapter
import com.network.hostelworld.presentation.PropertyContract
import com.network.hostelworld.presentation.base.BaseActivity
import com.network.hostelworld.presentation.propertydetail.PropertyDetailActivity
import kotlinx.android.synthetic.main.activity_property.*
import java.io.Serializable

class PropertyActivity : BaseActivity(), PropertyContract.View,
    PropertyAdapter.OnClickedlistener {

    private lateinit var presenter: PropertyContract.Presenter
    private lateinit var adapter: PropertyAdapter
    private lateinit var locationData: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property)

        presenter = PropertyPresenter(PropertyRepository(PropertyApiService.create(this)),this)
        presenter.attachView(this)

        adapter = PropertyAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter!!.setClickListener(this)
        presenter.getPropertyList()
    }


    override fun showProperties(properties: List<Property>) {
        adapter.submitList(properties)
    }

    override fun showLocation(location: Location) {
        locationData = location
    }

    override fun showError(errorMessage: String) {
        println("Error message: "+errorMessage)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
        presenter.onDestroy()
    }

    override fun onClicked(property: Property) {

        if (::locationData.isInitialized) {
            Intent(this, PropertyDetailActivity::class.java).apply {
                putExtra("prop_data", property as Serializable)
                putExtra("loc_data", locationData as Serializable)
                startActivity(this)
            }
        } else {
            showError("Location data is not initialized.")
        }
    }

}