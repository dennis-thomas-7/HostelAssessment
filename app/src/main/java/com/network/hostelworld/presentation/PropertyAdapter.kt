package com.network.hostelworld.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.network.hostelworld.R
import com.network.hostelworld.domain.Property
import kotlinx.android.synthetic.main.item_property.view.*

class PropertyAdapter : ListAdapter<Property, PropertyAdapter.PropertyViewHolder>(DiffCallback) {

    private var onClickedListener: OnClickedlistener? = null
    var decimal : Float = 0.0F
    var combinedImage :String = ""
    var currency:String= ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_property, parent, false)
        return PropertyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val property = getItem(position)
        holder.bind(property)

    }

    inner class PropertyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(property: Property) {


            itemView.tvPropertyName.text = property.name

            decimal = (property.rating).toFloat()
            itemView.tvPropertyRating.text = ((decimal)/10).toString()
            itemView.tvPropertyRatingNumber.text = "("+property.numberOfRatings+")"

            if (property.isFeatured==true){
                itemView.imgPropertyIsFeatured.visibility=View.VISIBLE
            }else{
                itemView.imgPropertyIsFeatured.visibility=View.INVISIBLE
            }

            if (property.lowestPriceCurrency=="EUR")currency="€"

            itemView.tvPropertyLowestPrice.text = currency+property.lowestPrice+"/night"

            combinedImage = (property.images!![0]!!.prefix.toString()+property.images!![0]!!.suffix.toString())

            Glide.with(itemView)
                .load("https://"+combinedImage)
                .apply(
                    RequestOptions()
                    .placeholder(R.drawable.placeholder))
                .into(itemView.imgHome)

            itemView.cardLinearLayout.setOnClickListener(View.OnClickListener {
                onClickedListener?.onClicked(property)
            })
        }
    }

    private object DiffCallback : DiffUtil.ItemCallback<Property>() {
        override fun areItemsTheSame(oldItem: Property, newItem: Property): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Property, newItem: Property): Boolean {
            return oldItem == newItem
        }
    }

    fun setClickListener(clickListener: OnClickedlistener) {
        this.onClickedListener = clickListener
    }

    interface OnClickedlistener {
        fun onClicked(property: Property)
    }

}
