package com.example.rickandmorty

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailsScreen : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_screen)

        val image_view = findViewById<ImageView>(R.id.popup_image)
        val name_view = findViewById<TextView>(R.id.name)
        val species_view = findViewById<TextView>(R.id.species)
        val gender_view = findViewById<TextView>(R.id.gender)
        val status_view = findViewById<TextView>(R.id.status)

        val image = intent.getStringExtra("image")
        val name = intent.getStringExtra("name")
        val species = intent.getStringExtra("species")
        val gender = intent.getStringExtra("gender")
        val staus = intent.getStringExtra("status")

        Glide.with(this).load(image).into(image_view)
        name_view.text = name
        species_view.text = species
        gender_view.text = gender
        status_view.text = staus
    }
}