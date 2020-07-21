package com.example.ecomapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_image_down_load.*

class ImageDownLoad : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_down_load)

        downloadImage.setOnClickListener {

            val imageURL = "http://192.168.56.1/lionr.jpg"

            Picasso.get().load(imageURL).into(imageView);

        }

    }
}
