package com.example.ecommerceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import kotlinx.android.synthetic.main.activity_log_in.*

class LogIn : AppCompatActivity() {
    var userEmail: String? = null
    var userName: String? = null
    var userPassword: String? = null
    var edUserName: String? = null
    var edUserPassword: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        supportActionBar?.hide()
        logIn()
    }

    private fun logInDataFromAPI() {
        val url = "https://fakestoreapi.com/users"
        var jsonRequest = JsonArrayRequest(Request.Method.GET, url, null, {
            var arrayList = ArrayList<LogInData>()
            for (i in 0 until it.length()) {
                var myJsonObj = it.getJSONObject(i)
                var myLogInData = LogInData(
                    myJsonObj.getString("email"),
                    myJsonObj.getString("username"),
                    myJsonObj.getString("password"))
                arrayList.add(myLogInData)
                //// LogIn Check
                userEmail = myLogInData.email
                userName = myLogInData.username
                userPassword = myLogInData.password

                if (userEmail != null && userPassword != null) {
                    if (edUserName.equals(userEmail) && edUserPassword.equals(userPassword)) {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        ed_user_pass.error = "Check Password"
                        ed_username.error = "Check Email"
                    }
                }
            }

        }, {
            Log.d("TAG", "Problem")
            Toast.makeText(this, "Network Problem", Toast.LENGTH_SHORT).show()
        })

        MySingletone.getInstance(this).addToRequestQueue(jsonRequest)
    }

    private fun logIn() {
        btn_login.setOnClickListener {

            edUserName = ed_username.text.toString().trim()
            edUserPassword = ed_user_pass.text.toString().trim()

            if (edUserName!!.isEmpty() || edUserName == "") {
                ed_username.error = "Please Enter Correct User Name"

            } else if (edUserPassword!!.isEmpty() || edUserPassword == "") {
                ed_user_pass.error = "Please Enter Correct Password"
            } else {
                logInDataFromAPI()
            }
        }
    }
}