package com.network.hostelworld.presentation.propertydetail

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.network.hostelworld.R
import com.network.hostelworld.data.*
import com.network.hostelworld.domain.Property
import com.network.hostelworld.domain.PropertyDetailApiService
import com.network.hostelworld.presentation.*
import com.network.hostelworld.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_property_detail.*
import java.math.RoundingMode
import java.text.DecimalFormat

class PropertyDetailActivity : BaseActivity(),View.OnClickListener,
    PropertyDetailContract.View {

    lateinit var detailpresenter: PropertyDetailContract.Presenter
    private lateinit var propData:Property
    private lateinit var locData:Location
    var sliderDotspanel:LinearLayout? = null
    var decimal : Float = 0.0F
    var currency:String= ""
    var cancellationData:String=""
    private lateinit var adapter: FeaturesAdapter


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_detail)
        detailpresenter = PropertyDetailPresenter(PropertyDetailRepository(PropertyDetailApiService.create(this)),this)
        detailpresenter.attachDetailView(this)

        showCustomUI()
        initEventClicks()
        if (intent.getSerializableExtra("prop_data")!=null){
            propData=intent.getSerializableExtra("prop_data") as Property
            locData=intent.getSerializableExtra("loc_data") as Location
            decimal = (propData.rating).toFloat()
            tvHostelName.text = propData.name
            tvHostelLocation.text = locData.city?.name
            cancellationData = propData.freeCancellation?.description?.replace("\\(.*?\\)".toRegex(),"")?.replace("#","").toString()
            tvHostelFreeCancellation.text = cancellationData
            tvPropertyDetailRatingNumber.text = "("+propData.numberOfRatings+")"
            tvPropertyDetailRating.text = ((decimal)/10).toString()
            tvHostelOverview.text = HtmlCompat.fromHtml(propData.overview.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
            if (propData.lowestPriceCurrency=="EUR")currency = resources.getText(R.string.euro).toString()

            tvPropertyLowestPriceCurrency.text = currency
            tvPropertyLowestPriceCurrency.paintFlags = tvPropertyLowestPriceCurrency.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG
            tvPropertyLowestPrice.text = propData.lowestPrice+"/night"

            adapter = FeaturesAdapter(propData.facilities,this)
            recyclerFacilitiesView.layoutManager = GridLayoutManager(this,2)
            recyclerFacilitiesView.adapter = adapter

        }

        val adapter = ViewPagerAdapter(propData.images)
        viewPager.adapter = adapter
        val dotscount = adapter.count
        val dots = arrayOfNulls<ImageView>(dotscount)
        sliderDotspanel = findViewById(R.id.SliderDots)
        for (i in 0 until dotscount) {

            dots[i] = ImageView(applicationContext)
            dots[i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.non_active_dot))

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            params.setMargins(8, 0, 8, 0)

            sliderDotspanel?.addView(dots[i], params)

        }

        dots[0]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.active_dot))

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {

                for (i in 0 until dotscount) {
                    dots[i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.non_active_dot))
                }

                dots[position]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.active_dot))

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    private fun showCustomUI() {
        val decorView: View = window.decorView
        decorView.setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        )
    }

    private fun initEventClicks() {

        imgBackArrow.setOnClickListener(this)
        tvPropertyLowestPriceCurrency.setOnClickListener(this)
        imgHostelFreeCancellation.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.imgBackArrow ->{
                super.onBackPressed()
            }
            R.id.tvPropertyLowestPriceCurrency ->{
                showPopupMenu(v)
            }
            R.id.imgHostelFreeCancellation ->{

                Intent(this, FreeCancellationActivity::class.java).apply {
                    putExtra("cancellation_data", cancellationData)
                    startActivity(this)
                }
            }
        }
    }

    fun showPopupMenu(view: View?) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.currency_menu, popupMenu.menu)

        // Set item click listener
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_eur -> {
                    // Handle EUR selection
                    fetchCurrencyData()
                    currency = resources.getText(R.string.euro).toString()
                    true
                }
                R.id.menu_usd -> {
                    // Handle USD selection
                    fetchCurrencyData()
                    currency = resources.getText(R.string.dollar).toString()
                    true
                }
                R.id.menu_gbp -> {
                    // Handle GBP selection
                    fetchCurrencyData()
                    currency = resources.getText(R.string.pound).toString()
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }

    private fun fetchCurrencyData() {
        detailpresenter.getCurrencyData()
    }


    override fun showCurrencies(currencies: Rates) {

        var floatVal: Double? = null

        if(currency == resources.getText(R.string.euro).toString()){
            tvPropertyLowestPriceCurrency.text = resources.getText(R.string.euro).toString()
            tvPropertyLowestPriceCurrency.paintFlags = tvPropertyLowestPriceCurrency.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG
            floatVal = currencies.EUR

        }
        if(currency == resources.getText(R.string.pound).toString()){
            tvPropertyLowestPriceCurrency.text = resources.getText(R.string.pound).toString()
            tvPropertyLowestPriceCurrency.paintFlags = tvPropertyLowestPriceCurrency.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG
            floatVal = currencies.GBP

        }
        if(currency == resources.getText(R.string.dollar).toString()){
            tvPropertyLowestPriceCurrency.text = resources.getText(R.string.dollar).toString()
            tvPropertyLowestPriceCurrency.paintFlags = tvPropertyLowestPriceCurrency.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG
            floatVal = currencies.USD

        }

        var floatVal2 = propData.lowestPrice.toDoubleOrNull() ?: 0.0

        val result = floatVal?.let { it * floatVal2 }


        val formattedResult = result?.let {
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING
            df.format(it)
        } ?: "N/A"

        tvPropertyLowestPrice.text = "$formattedResult/night"
    }

    override fun showDetailError(errorMessage: String) {
        println("error message: "+errorMessage)
    }

    public override fun onDestroy() {
        super.onDestroy()
        detailpresenter.detachDetailView()
        detailpresenter.onDestroy()
    }


}