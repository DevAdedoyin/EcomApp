package com.example.ecomapp

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_row.view.*

class ProductAdapter(var context: Context, var arrayList: ArrayList<Products>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var productHolder = LayoutInflater.from(context).inflate(R.layout.product_row, parent, false)

        return ProductViewHolder(productHolder)
    }

    override fun getItemCount(): Int {
           return arrayList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as ProductViewHolder).initializeUiComponents(arrayList.get(position).id, arrayList.get(position).name, arrayList.get(position).price, arrayList.get(position).profilePicture)

    }

    inner class ProductViewHolder(pView: View): RecyclerView.ViewHolder(pView){

        fun initializeUiComponents(id: Int, name: String, price: Int, picName: String){

            itemView.pid.text = id.toString()
            itemView.name.text = name
            itemView.price.text = price.toString()

            var picURL = "http://192.168.56.1/OnlineStoreApp/images/"
            picURL = picURL.replace(" ", "%20")
            Picasso.get().load(picURL + picName).into(itemView.pImage)

            itemView.imageAdd.setOnClickListener {

                Person.addToCartProductID = id
                var amountFragment = AmountFragment()
                var fragmentManager = (itemView.context as Activity).fragmentManager
                amountFragment.show(fragmentManager, "TAG")
            }

        }

    }
}