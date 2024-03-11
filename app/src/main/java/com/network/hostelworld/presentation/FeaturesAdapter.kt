package com.network.hostelworld.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.network.hostelworld.R
import com.network.hostelworld.data.FacilitiesItem
import com.network.hostelworld.domain.Property
import kotlinx.android.synthetic.main.item_features.view.*

class FeaturesAdapter(val data: List<FacilitiesItem?>?, val context: Context?) : RecyclerView.Adapter<FeaturesAdapter.FeaturesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_features, parent, false)
        return FeaturesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data?.size!!
    }


    override fun onBindViewHolder(holder: FeaturesViewHolder, position: Int) {
        holder.itemView.tvFeatureName.text = data?.get(position)?.facilities!![0]!!.name
    }

    inner class FeaturesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(property: Property) {

        }
    }



}