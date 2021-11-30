package com.example.ecommerceapp

import com.example.ecommerceapp.horizontalRecyclerView.HrAdapter
import com.example.ecommerceapp.horizontalRecyclerView.HrData
import com.example.ecommerceapp.verticalRecyclerView.VrData

interface MyOnClickedListener {
    fun hrOnClickedListener(hrItems : HrData, categoryTitle : HrAdapter.HrHolder)
    fun vrOnClickedListener(vrItems : VrData)
}