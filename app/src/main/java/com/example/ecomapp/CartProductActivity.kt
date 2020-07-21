package com.example.ecomapp

import android.app.DownloadManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_cart_product.*

class CartProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_product)

        var tempCartURL = "http://192.168.56.1/OnlineStoreApp/fetch_temporary_order.php"
        var tempProductList = ArrayList<String>()
        var requestQ = Volley.newRequestQueue(this)
        var responseQ = JsonArrayRequest(Request.Method.GET, tempCartURL, null, Response.Listener {

                response ->
                    for (JsonResp in 0.until(response.length())){

                        tempProductList.add("${response.getJSONObject(JsonResp).getInt("id")},\n" +
                                "${response.getJSONObject(JsonResp).getString("name")}\n" +
                                "${response.getJSONObject(JsonResp).getInt("price")}\n" +
                                "${response.getJSONObject(JsonResp).getString("email")}\n" +
                                "${response.getJSONObject(JsonResp).getInt("amount")}")

                    }

            var listviewAdapter = ArrayAdapter(this@CartProductActivity, android.R.layout.simple_list_item_1, tempProductList)
            tempListView.adapter = listviewAdapter

        }, Response.ErrorListener {
            error ->
        } )

        requestQ.add(responseQ)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.cart_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.contShopping){
            var intent = Intent(this@CartProductActivity, MainActivity::class.java)
            startActivity(intent)
        } else if(item.itemId == R.id.declineShopping){

            var declineURL = "http://192.168.56.1/OnlineStoreApp/delete_temporary_order.php"
            var requestQ = Volley.newRequestQueue(this@CartProductActivity)
            var decString = StringRequest(Request.Method.GET, declineURL, Response.Listener {

                response ->

                var intent = Intent(this@CartProductActivity, MainActivity::class.java)
                startActivity(intent)

            }, Response.ErrorListener {

            })
            requestQ.add(decString)
        } else if (item.itemId == R.id.verifyShopping ){

            var verifyOrderURL = "http://192.168.56.1/OnlineStoreApp/invoice.php?email=${Person.email}"
            var requestQ = Volley.newRequestQueue(this@CartProductActivity)
            var reponseQ = StringRequest(Request.Method.GET, verifyOrderURL, Response.Listener {
                response ->

                var intent = Intent(this, FinalizeShoppingActivity::class.java)
                Toast.makeText(this, response, Toast.LENGTH_LONG).show()
                intent.putExtra("LATEST_INVOICE_NUMBER", response)
                startActivity(intent)

            }, Response.ErrorListener {
                error ->



            })
        }
        return super.onOptionsItemSelected(item)
    }
}
