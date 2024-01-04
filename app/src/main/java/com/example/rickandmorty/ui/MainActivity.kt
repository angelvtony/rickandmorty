package com.example.rickandmorty.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.network.RetrofitInstance
import com.example.rickandmorty.models.RickMorty
import com.example.rickandmorty.adapter.RickRecycler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: RickRecycler

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = RickRecycler()

        val search = findViewById<SearchView>(R.id.search_view)
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText.orEmpty())
                return false
            }
        })

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        RetrofitInstance.api.getDetails().enqueue(object : Callback<RickMorty> {
            override fun onResponse(call: Call<RickMorty>, response: Response<RickMorty>) {
                if (response.body() != null) {
                    adapter.setData(response.body()!!.results)
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<RickMorty>, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }
        })
    }
}