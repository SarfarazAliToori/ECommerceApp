package com.example.ecommerceapp

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.example.ecommerceapp.horizontalRecyclerView.HrAdapter
import com.example.ecommerceapp.horizontalRecyclerView.HrData
import com.example.ecommerceapp.verticalRecyclerView.VrAdapter
import com.example.ecommerceapp.verticalRecyclerView.VrData
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.android.synthetic.main.activity_main.*

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
        productsFiltering("/")
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
           //Log.d("error", it.localizedMessage)
           Toast.makeText(this, "Network Problem", Toast.LENGTH_SHORT).show()
           myAlertDialog { horizontalRecyclerView() }
       })
       // call to singleton instance
       MySingletone.getInstance(this).addToRequestQueue(jsonArray)

       recycler_view_horizontal.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
       hrAdapter = HrAdapter(this)
       recycler_view_horizontal.adapter = hrAdapter
    }

    private fun productsFiltering(str: String) {
        val progress = KProgressHUD.create(this)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setLabel("Please wait")
            .setCancellable(true)
            .show()

        val url = "https://fakestoreapi.com/products$str"
        var jsonArray = JsonArrayRequest(Request.Method.GET, url, null, {
            var arrayListOfVrData = ArrayList<VrData>()
            for (i in 0 until it.length()) {
                var myJsonObj = it.getJSONObject(i)
                //val myArray = it.get(i).toString()
                val myVrData = VrData(myJsonObj.getString("id"),
                    myJsonObj.getString("title"),
                    myJsonObj.getString("price"),
                    myJsonObj.getString("description"),
                    myJsonObj.getString("category"),
                    myJsonObj.getString("image"),
                    myJsonObj.getString("rating")
                )
                arrayListOfVrData.add(myVrData)
            }
            vrAdapter.updateData(arrayListOfVrData)

            progress.dismiss()

            Log.i("TAG", "MyArray : $arrayListOfVrData")
        }, {
            progress.dismiss()
            myAlertDialog { functionCalls() }
        })
        // call to singleton instance
        MySingletone.getInstance(this).addToRequestQueue(jsonArray)

        recycler_view_vertical.layoutManager = GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false)
        vrAdapter = VrAdapter(this)
        recycler_view_vertical.adapter = vrAdapter
    }

    @SuppressLint("ResourceAsColor")
    override fun hrOnClickedListener(hrItems: HrData, categoryTitle : HrAdapter.HrHolder) {
        //categoryTitle.category.setBackgroundColor(Color.CYAN)
        when(hrItems.categoryTitle) {
            "All Category" -> { productsFiltering("/")
//            val isSeleted = categoryTitle.itemId.toInt()
//            var position = categoryTitle.layoutPosition
//            if (isSeleted == position) {
//                categoryTitle.category.setBackgroundColor(Color.BLUE)
//            }
            }  //here we call verticalRecyclerView() because we have no all category in api.
            "electronics" -> productsFiltering("/category/electronics")
            "jewelery" -> productsFiltering("/category/jewelery")
            "men's clothing" -> productsFiltering("/category/men's clothing")
            "women's clothing" -> productsFiltering("/category/women's clothing")
        }

    }

    override fun vrOnClickedListener(vrItems: VrData) {
        val itemsId = vrItems.id
        val title = vrItems.title
        val detail = vrItems.description
        val category = vrItems.category
        val price = vrItems.price
        val image = vrItems.image
        val rating = vrItems.rating

        val intent = Intent(this@MainActivity,ItemsDetailsActivity::class.java)
        intent.putExtra("id", itemsId)
        intent.putExtra("title",title)
        intent.putExtra("detail", detail)
        intent.putExtra("category", category)
        intent.putExtra("price", price)
        intent.putExtra("image", image)
        intent.putExtra("rating", rating)
        startActivity(intent)

    }

    private fun myAlertDialog(myfunction: () -> Unit) {
        AlertDialog.Builder(this)
            .setTitle("Alert !")
            .setMessage("Nework Issue Please Try Again.")
            .setPositiveButton("Try Agian", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    myfunction()
                    p0?.dismiss()
                }
            })
            .setNegativeButton("No", object : DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    p0?.dismiss()
                }
            })
            .show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.filter, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_filter -> productsFiltering("?sort=desc")
            R.id.menu_5 -> productsFiltering("?limit=5")
            R.id.menu_10 -> productsFiltering("?limit=10")
            R.id.menu_15 -> productsFiltering("?limit=15")
            R.id.menu_all -> productsFiltering("/")
        }
        return super.onOptionsItemSelected(item)
    }
}