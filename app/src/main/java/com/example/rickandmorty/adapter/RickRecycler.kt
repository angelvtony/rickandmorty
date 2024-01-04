package com.example.rickandmorty.adapter

import android.annotation.SuppressLint
import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.models.Result
import de.hdodenhof.circleimageview.CircleImageView
import java.util.Locale

class RickRecycler : RecyclerView.Adapter<RickRecycler.ViewHolder>() {
    private var list = ArrayList<Result>()
    private var filteredList = ArrayList<Result>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Result>) {
        this.list.clear()
        this.list.addAll(list)
        this.filteredList = ArrayList(this.list)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filter(query: String) {
        filteredList = if (query.isEmpty()) {
            list
        } else {
            val resultList = ArrayList<Result>()
            for (row in list) {
                if (row.name.toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT))) {
                    resultList.add(row)
                }
            }
            resultList
        }
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val characterImage: CircleImageView = view.findViewById(R.id.characterImage)
        val name: TextView = view.findViewById(R.id.showName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = filteredList[position]
        Glide.with(holder.itemView.context).load(item.image).into(holder.characterImage)

        holder.name.text = item.name

        holder.characterImage.setOnClickListener {
            val dialog = Dialog(holder.itemView.context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.image_popup)

            val image = dialog.findViewById<ImageView>(R.id.popup_image)
            Glide.with(holder.itemView.context).load(item.image).into(image)

            dialog.show()
        }
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }
}