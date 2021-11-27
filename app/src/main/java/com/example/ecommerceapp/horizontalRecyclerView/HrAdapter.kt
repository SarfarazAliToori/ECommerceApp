package com.example.ecommerceapp.horizontalRecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.MyOnClickedListener
import com.example.ecommerceapp.R
import kotlinx.android.synthetic.main.items_view_horizontal_recycler.view.*
import java.util.ArrayList

class HrAdapter(private val myListener: MyOnClickedListener) : RecyclerView.Adapter<HrAdapter.HrHolder>(){

    private val categoryArray: ArrayList<HrData> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HrHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.items_view_horizontal_recycler,parent, false)
        var hrHolder = HrHolder(view)

        view.setOnClickListener {
            myListener.hrOnClickedListener(categoryArray[hrHolder.absoluteAdapterPosition])
        }
        return hrHolder
    }

    override fun onBindViewHolder(holder: HrHolder, position: Int) {
        val dd = categoryArray[position]
        holder.category.text = dd.categoryTitle
        //holder.hardCodedAllCategory.text = "All Category"
    }

    override fun getItemCount(): Int {
       return categoryArray.size
    }

    fun updateData(arrayListOfHrData: ArrayList<HrData>) {
        categoryArray.clear()
        categoryArray.addAll(arrayListOfHrData)
       // categoryArray.add(HrData("All Category"))
        notifyDataSetChanged()
    }

    inner class HrHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var category = itemView.findViewById<TextView>(R.id.tv_category_h)
//        var hardCodedAllCategory = itemView.findViewById<TextView>(R.id.tv_category_h1)
    }
}

//interface HrOnClickedListener {
//    fun mOnClickListener(item : HrData)
//}