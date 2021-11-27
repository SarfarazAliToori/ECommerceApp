package com.example.ecommerceapp.verticalRecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.MyOnClickedListener
import com.example.ecommerceapp.R

class VrAdapter(private val myListener : MyOnClickedListener) : RecyclerView.Adapter<VrAdapter.VrHolder>() {

    val arrayList : ArrayList<VrData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VrHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_view_vertical_recycler,parent,false)
        val vrHolder = VrHolder(view)

        view.setOnClickListener {
            myListener.vrOnClickedListener(arrayList[vrHolder.absoluteAdapterPosition])
        }
        return vrHolder
    }

    override fun onBindViewHolder(holder: VrHolder, position: Int) {
        val aa = arrayList[position]
        holder.title.text = aa.title
        //holder.description.text = aa.description
        holder.price.text = "$${aa.price}"
        Glide.with(holder.image.context).load(aa.image).into(holder.image)

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun updateData(arrayListOfVrData: ArrayList<VrData>) {
        arrayList.clear()
        arrayList.addAll(arrayListOfVrData)
        notifyDataSetChanged()
    }


    inner class VrHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image = itemView.findViewById<ImageView>(R.id.img_v)
        var title = itemView.findViewById<TextView>(R.id.tv_title_v)
        var price = itemView.findViewById<TextView>(R.id.tv_price_v)
        //var description = itemView.findViewById<TextView>(R.id.tv_detail_v)
    }
}