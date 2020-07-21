package com.example.ecomapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_show_json_object_ativity.*

class ShowJsonObjectAtivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_json_object_ativity)

        btnGetPeroduct.setOnClickListener {

            val productURL = "http://192.168.56.1/ecommerce/present_json.php?id=" + edtProductId.text
            var requestQ: RequestQueue = Volley.newRequestQueue(this@ShowJsonObjectAtivity)
            var jsonRequest = JsonObjectRequest(Request.Method.GET, productURL, null,

                Response.Listener {response ->
                    textView2.text = response.getString("name")
                    textView3.text = response.getString("price")

            },
                Response.ErrorListener { error ->
                    textView2.text = error.message
                    textView3.text = error.message
                })

            requestQ.add(jsonRequest)

        }

    }
}