package com.example.ecomapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_brand_products.*

class BrandProducts : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brand_products)

        var productArray = ArrayList<Products>()
        var products = intent.getStringExtra("BRAND")
        var productsURL = "http://192.168.56.1/OnlineStoreApp/brand_products.php?brand=$products"
        val requestQueue = Volley.newRequestQueue(this)
        val response = JsonArrayRequest(Request.Method.GET, productsURL, null, Response.Listener {

            response ->

            for (prductJOIndex in 0.until(response.length())){

                productArray.add(Products(response.getJSONObject(prductJOIndex).getInt("id"), response.getJSONObject(prductJOIndex).getString("name"),
                    response.getJSONObject(prductJOIndex).getInt("price"), response.getJSONObject(prductJOIndex).getString("picture")))

            }

            recyclerViewProducts.layoutManager = LinearLayoutManager(this@BrandProducts)
            recyclerViewProducts.adapter = ProductAdapter(this@BrandProducts, productArray)

        }, Response.ErrorListener {
            error ->
        })

        requestQueue.add(response)
    }
}
