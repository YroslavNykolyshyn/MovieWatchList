package com.example.moviewatchlist.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviewatchlist.R
import com.example.moviewatchlist.data.MoviesData

class MyAdapter(private var dataList: ArrayList<MoviesData>, private val  listener: OnItemClickListener) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    interface OnItemClickListener{
        fun onItemClick(moviesData: String?)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_item,
            parent, false
        )
        return MyViewHolder(view)
    }

    fun updateMoviesList(movList: List<MoviesData>){
        this.dataList.clear()
        this.dataList.addAll(movList)
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    val currentItem = dataList[position]
    Glide.with(holder.movieImg).load(currentItem.movieImage).into(holder.movieImg)
    holder.movieDuration.text = currentItem.movieDuration.toString()
    holder.movieGenre.text = currentItem.movieGenre
    holder.movieTitle.text = currentItem.movieTitle
    holder.movieWatch.text = currentItem.movieWatch.toString()
    }


inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val movieImg: ImageView = itemView.findViewById(R.id.movieImg)
    val movieTitle : TextView = itemView.findViewById(R.id.movieTitle)
    val movieDuration : TextView = itemView.findViewById(R.id.movieDuration)
    val movieGenre : TextView = itemView.findViewById(R.id.movieGenre)
    val movieWatch : TextView = itemView.findViewById(R.id.movieWatch)
    init {
        itemView.setOnClickListener{
            val position = adapterPosition
            val selectedItem = dataList[position]
            listener.onItemClick(selectedItem.movieTitle)
            listener.onItemClick(selectedItem.movieDescription)
        }
    }
    }
}

