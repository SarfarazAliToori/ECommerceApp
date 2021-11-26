package com.example.ecommerceapp.verticalRecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R

class VrAdapter() : RecyclerView.Adapter<VrAdapter.VrHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VrHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_view_vertical_recycler,parent,false)
        return VrHolder(view)
    }

    override fun onBindViewHolder(holder: VrHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


    inner class VrHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image = itemView.findViewById<ImageView>(R.id.img_v)
        var title = itemView.findViewById<TextView>(R.id.tv_title_v)
        var price = itemView.findViewById<TextView>(R.id.tv_price_v)
        var Detail = itemView.findViewById<TextView>(R.id.tv_detail_v)
    }
}