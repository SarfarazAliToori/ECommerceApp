package com.example.ecommerceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.example.ecommerceapp.horizontalRecyclerView.HrAdapter
import com.example.ecommerceapp.horizontalRecyclerView.HrData
import com.example.ecommerceapp.verticalRecyclerView.VrAdapter
import com.example.ecommerceapp.verticalRecyclerView.VrData
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity(), MyOnClickedListener {
    lateinit var hrAdapter : HrAdapter
    lateinit var vrAdapter: VrAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        functionCalls()
    }

    private fun functionCalls() {
        horizontalRecyclerView()
        mainRecyclerView()
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
           try {
               Toast.makeText(this, "Network Problem", Toast.LENGTH_SHORT).show()
           }catch (e: Exception) { e.printStackTrace()}
       })
       // call to singleton instance
       MySingletone.getInstance(this).addToRequestQueue(jsonArray)

       recycler_view_horizontal.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
       hrAdapter = HrAdapter(this)
       recycler_view_horizontal.adapter = hrAdapter
    }

   private fun mainRecyclerView() {

       val url = "https://fakestoreapi.com/products"
       var jsonArray = JsonArrayRequest(Request.Method.GET, url, null, {
           var arrayListOfVrData = ArrayList<VrData>()
           for (i in 0 until it.length()) {
               var myJsonObj = it.getJSONObject(i)
               //val myArray = it.get(i).toString()
               val myVrData = VrData(myJsonObj.getString("title"),
               myJsonObj.getString("price"),
                   myJsonObj.getString("description"),
                   myJsonObj.getString("category"),
                   myJsonObj.getString("image"),
                   myJsonObj.getString("rating")
               )
               arrayListOfVrData.add(myVrData)
           }
           vrAdapter.updateData(arrayListOfVrData)

           Log.i("TAG", "MyArray : $arrayListOfVrData")
       }, {
           Log.d("error", it.localizedMessage)
           try {
               Toast.makeText(this, "Network Problem", Toast.LENGTH_SHORT).show()
           } catch (e: Exception) { e.printStackTrace()}
       })
       // call to singleton instance
       MySingletone.getInstance(this).addToRequestQueue(jsonArray)

       recycler_view_vertical.layoutManager = GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false)
       vrAdapter = VrAdapter(this)
       recycler_view_vertical.adapter = vrAdapter
    }

    override fun hrOnClickedListener(hrItems: HrData) {
        Toast.makeText(this, "Hello World : ${hrItems.toString()}", Toast.LENGTH_SHORT).show()
    }

    override fun vrOnClickedListener(vrItems: VrData) {
        val title = vrItems.title
        val detail = vrItems.description
        val category = vrItems.category
        val price = vrItems.price
        val image = vrItems.image
        val rating = vrItems.rating

        val intent = Intent(this@MainActivity,ItemsDetailsActivity::class.java)
        intent.putExtra("title",title)
        intent.putExtra("detail", detail)
        intent.putExtra("category", category)
        intent.putExtra("price", price)
        intent.putExtra("image", image)
        intent.putExtra("rating", rating)
        startActivity(intent)

    }
}