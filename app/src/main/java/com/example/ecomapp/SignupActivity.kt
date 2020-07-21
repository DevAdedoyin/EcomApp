package com.example.ecomapp

import android.app.DownloadManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        btnSwitchToLogin.setOnClickListener {

            var intent = Intent(this, LoginActivity::class.java);
            startActivity(intent)
            finish()
        }

        btnSignup.setOnClickListener {

            if (passwordSignup.text.toString().equals(confirmPassword.text.toString())){

                var signupURL = "http://192.168.56.1/OnlineStoreApp/add_user.php?email=" + emailSignup.text.toString() + "&name" +
                                   usernameSIgnup.text.toString() + "&password" + passwordSignup.text.toString()
                var signupQueue: RequestQueue = Volley.newRequestQueue(this@SignupActivity)
                var signupResponse = StringRequest(Request.Method.GET, signupURL, Response.Listener {

                    response->

                    if (response.equals("User Already Exist")) {

                        val dialogBuilder = AlertDialog.Builder(this@SignupActivity)
                        dialogBuilder.setTitle("Message")
                            .setMessage(response)
                            .create().show()

                    }else{

                        Person.email = emailSignup.text.toString()
                        val dialogBuilder = AlertDialog.Builder(this@SignupActivity)
                        dialogBuilder.setTitle("Message")
                            .setMessage("Congratulations!!! Your registration is successful")
                            .create().show()
                        var intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()

                    }

                }, Response.ErrorListener {

                        error ->

                    val dialogBuilder = AlertDialog.Builder(this@SignupActivity)
                    dialogBuilder.setTitle("Message").setMessage(error.message).create().show()

                })

               signupQueue.add(signupResponse)

            }else{

                val dialogBuilder = AlertDialog.Builder(this@SignupActivity)
                dialogBuilder.setTitle("Message").setMessage("Password Mismatch").create().show()

            }

        }

    }
}
