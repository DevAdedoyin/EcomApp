package com.example.ecomapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {

            val loginURL = "http://192.168.56.1/OnlineStoreApp/auth_login.php?email=" +
                    emailLogin.text.toString() + "&password=" + passwordLogin.text.toString()
            var loginQ = Volley.newRequestQueue(this@LoginActivity)
            var loginR = StringRequest(Request.Method.GET, loginURL, Response.Listener {

                response ->

                if (response.equals("User Already Exist")){

                    val dialogBuilder = AlertDialog.Builder(this@LoginActivity)
                    dialogBuilder.setTitle("Message")
                        .setMessage(response).create().show()

                }else{

                    Person.email = emailLogin.text.toString()
                    val dialogBuilder = AlertDialog.Builder(this@LoginActivity)
                    dialogBuilder.setTitle("Message")
                        .setMessage(response).create().show()
                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                }

            }, Response.ErrorListener {

                error ->

                val dialogBuilder = AlertDialog.Builder(this@LoginActivity)
                dialogBuilder.setTitle("Message")
                    .setMessage(error.message)
                    .create().show()

            })

            loginQ.add(loginR)

        }

        btnSwitchToSignUp.setOnClickListener {

            var intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)

        }

    }
}
