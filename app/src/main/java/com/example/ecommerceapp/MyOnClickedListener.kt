package com.example.ecommerceapp

import com.example.ecommerceapp.horizontalRecyclerView.HrData
import com.example.ecommerceapp.verticalRecyclerView.VrData

interface MyOnClickedListener {
    fun hrOnClickedListener(hrItems : HrData)
    fun vrOnClickedListener(vrItems : VrData)
}