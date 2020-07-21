package com.example.ecomapp


import android.content.Intent
import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

/**
 * A simple [Fragment] subclass.
 */
class AmountFragment : android.app.DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var fragmentView =  inflater.inflate(R.layout.fragment_amount, container, false)

        var edtEnterAmount = fragmentView.findViewById<EditText>(R.id.edtAmount)
        var btnAddToCart = fragmentView.findViewById<ImageView>(R.id.imgCart)

        btnAddToCart.setOnClickListener {
            var pURL = "http://192.168.56.1/OnlineStoreApp/temporary_placed_order.php?email=${Person.email }&amount=${edtEnterAmount.text}"
            var requestQ = Volley.newRequestQueue(activity)
            var stringRequest = StringRequest(Request.Method.GET, pURL, Response.Listener {

                var intent = Intent(activity, CartProductActivity::class.java)

            }, Response.ErrorListener {
                error ->
            })

            requestQ.add(stringRequest)

        }

        return fragmentView
    }


}
