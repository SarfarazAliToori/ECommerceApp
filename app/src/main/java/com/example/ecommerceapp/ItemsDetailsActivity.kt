package com.example.ecommerceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ItemsDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items_details)

        getDetailsOfItems()
    }

    private fun getDetailsOfItems() {
        val title = intent.getStringArrayExtra("title")
        val description = intent.getStringExtra("detail")
        val category = intent.getStringExtra("category")
        val price  = intent.getStringExtra("price")
        val image = intent.getStringExtra("image")
        val rating = intent.getStringExtra("rating")
        //val profileName=intent.getStringExtra("Username")
    }
}