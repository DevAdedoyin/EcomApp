package com.example.ecomapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var shareP: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         // SharedPrefences
//        btnAddProduct.setOnClickListener {
//
//            shareP = getSharedPreferences("products", Context.MODE_PRIVATE)
//            shareP?.edit()?.putString("product_name", txtInputProduct.text.toString())?.commit()
//
//        }
//
//        btnGetProduct.setOnClickListener {
//
//            txtShowProduct.text = shareP?.getString("product_name", "Nothing")
//
//        }

        var brandURL = "http://192.168.56.1/OnlineStoreApp/fetch_brands.php"
        var brandArray = ArrayList<String>()
        var brandRequest: RequestQueue = Volley.newRequestQueue(this@MainActivity)
        var brandResponse = JsonArrayRequest(Request.Method.GET, brandURL, null, Response.Listener {

                response ->

            for (resp in 0.until(response.length())){

                brandArray.add(response.getJSONObject(resp).getString("brand"))

            }

            val adapter = ArrayAdapter(this@MainActivity, R.layout.textview, brandArray)
            brandListView.adapter = adapter

        }, Response.ErrorListener {

            error ->

            val dialogBuilder = AlertDialog.Builder(this@MainActivity)
            dialogBuilder.setTitle("Message")
                .setMessage(error.message)
                .create().show()

        })

        brandRequest.add(brandResponse)

        brandListView.setOnItemClickListener { adapterView, view, position, id ->
            var selectedItem = brandArray.get(position)
            var intent = Intent(this, BrandProducts::class.java)
            intent.putExtra("BRAND", selectedItem)
            startActivity(intent)

        }
    }
}