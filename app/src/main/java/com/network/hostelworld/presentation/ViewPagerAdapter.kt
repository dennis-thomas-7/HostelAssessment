package com.network.hostelworld.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.network.hostelworld.R
import com.network.hostelworld.data.ImagesGalleryItem
import kotlinx.android.synthetic.main.page_layout.view.*

class ViewPagerAdapter(val images: List<ImagesGalleryItem?>?) : PagerAdapter() {

    var combinedImage :String = ""

    override fun isViewFromObject(v: View, `object`: Any): Boolean {
        return v === `object` as View
    }

    override fun getCount(): Int {
        return images!!.size
    }

    override fun instantiateItem(parent: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(parent?.context)
            .inflate(R.layout.page_layout,parent,false)

        combinedImage = images?.get(position)?.prefix.toString()+images?.get(position)?.suffix.toString()
        Glide.with(view.context)
            .load("https://"+combinedImage)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.placeholder))
            .into(view.imgLauncher)
        parent?.addView(view)
        return view
    }


    override fun destroyItem(parent: ViewGroup, position: Int, `object`: Any) {
        parent.removeView(`object` as View)
    }

}