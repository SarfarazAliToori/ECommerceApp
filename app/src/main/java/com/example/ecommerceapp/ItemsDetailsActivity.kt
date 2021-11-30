package com.example.ecommerceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewDebug
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_items_details.*
import java.lang.NumberFormatException

class ItemsDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items_details)

        getDetailsOfItems()
    }

    private fun getDetailsOfItems() {
        var hh : Float? = null
        val ItemsId = intent.getStringExtra("id")  // we don't need id here
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("detail")
        val category = intent.getStringExtra("category")
        val price  = intent.getStringExtra("price")
        val image = intent.getStringExtra("image")


        //tv_items_id.text = ItemsId
        tv_item_title.text = "Title: $title"
        tv_items_detail.text = "Description: $description"
        tv_item_category.text = "Category: $category"
        tv_item_price.text = "Price: $$price"
        Glide.with(this).load(image).into(imageView)

        try {
            val rating = intent.getStringExtra("rating")
            val rate = rating?.subSequence(8, 11).toString().toFloat()
            ratingBar.rating = rate
        } catch (n: NumberFormatException) { n.printStackTrace() }

//        Toast.makeText(this, "Rating: $rating", Toast.LENGTH_SHORT).show()
//        Log.d("TAG", "Rating: $rating")
//        Log.d("TAG", "Final Rating : ${rate}")
    }
}