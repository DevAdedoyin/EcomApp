package com.example.ecomapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresPermission
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_show_all_json_products.*

class ShowAllJsonProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_all_json_products)

        textView5.setOnClickListener {

            val productsURL = "http://192.168.56.1/ecommerce/present_json_array.php"
            var allProducts = ""
            var productRequestQueue: RequestQueue = Volley.newRequestQueue(this@ShowAllJsonProductsActivity)
            var productRequestListener = JsonArrayRequest(Request.Method.GET, productsURL, null,

                Response.Listener {response ->

                    for (productsIndex in 0.until(response.length())){

                        var pn = response.getJSONObject(productsIndex).getString("name")
                        var pp = response.getJSONObject(productsIndex).getString("price")

                        allProducts += pn + ": " + pp + "\n"

                    }

                    textView5.text = allProducts

                }, Response.ErrorListener {error ->

                    textView5.text = error.message

                })

            productRequestQueue.add(productRequestListener)

        }
    }
}
