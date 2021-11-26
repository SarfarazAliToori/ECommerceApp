package com.example.ecommerceapp

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.example.ecommerceapp.horizontalRecyclerView.HrAdapter
import com.example.ecommerceapp.horizontalRecyclerView.HrData
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    lateinit var hrAdapter : HrAdapter
    lateinit var vrAdapter: HrAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        functionCalls()
    }

    private fun functionCalls() {
        horizontalRecyclerView()
    }

   private fun horizontalRecyclerView() {

       val url = "https://fakestoreapi.com/products/categories"
       var jsonArray = JsonArrayRequest(Request.Method.GET, url, null, {
           var arrayListOfHrData = ArrayList<HrData>()
           // all category in not available in api so that's why we are going to add manually
           arrayListOfHrData.add(HrData("All Category"))
           for (i in 0 until it.length()) {
               //var myJsonObj = it.getJSONObject(i)
                   val myArray = it.get(i).toString()
               val myHrData = HrData(myArray)
               arrayListOfHrData.add(myHrData)
           }
           hrAdapter.updateData(arrayListOfHrData)

           Log.i("TAG", "MyArray : $arrayListOfHrData")
       }, {
           Log.d("error", it.localizedMessage)
       })
       // call to singleton instance
       MySingletone.getInstance(this).addToRequestQueue(jsonArray)

       recycler_view_horizontal.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
       hrAdapter = HrAdapter()
       recycler_view_horizontal.adapter = hrAdapter
    }

   private fun mainRecyclerView() {

    }
}