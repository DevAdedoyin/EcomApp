//package com.example.ecomapp
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//
//class RVAdapter(var context: Context,var arrayList: ArrayList<Products>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//
//        var inflateRV = LayoutInflater.from(context).inflate(R.layout.rv_row, parent, false)
//        return ProductViewHolder(inflateRV)
//
//    }
//
//    override fun getItemCount(): Int {
//        return arrayList.size
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//
//        (holder as ProductViewHolder).initializeUIComponents(
//            arrayList[position].id,
//            arrayList[position].name,
//            arrayList[position].price,
//            arrayList[position].profilePicture)
//    }
//
//    inner class ProductViewHolder(myView: View): RecyclerView.ViewHolder(myView){
//
//        var pId = myView.findViewById<TextView>(R.id.productId)
//        var pName = myView.findViewById<TextView>(R.id.productName)
//        var pPrice = myView.findViewById<TextView>(R.id.productPrice)
//        var pProfilePicture = myView.findViewById<ImageView>(R.id.productImage)
//
//        fun initializeUIComponents(productId: Int, productName: String, productPrice: Double, productProfilePicture: Int){
//
//            pId.text = productId.toString()
//            pName.text = productName
//            pPrice.text = productPrice.toString()
//            pProfilePicture.setImageResource(productProfilePicture)
//
//
//        }
//    }
//
//
//
//}