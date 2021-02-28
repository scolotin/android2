package com.example.android2.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android2.R
import com.example.android2.databinding.ItemFragmentMainBinding
import com.example.android2.model.Film
import com.example.android2.model.OnItemViewClickListener
import com.squareup.picasso.Picasso

class MainFragmentAdapter(private var onItemViewClickListener: OnItemViewClickListener?) : RecyclerView.Adapter<MainFragmentAdapter.MainFragmentViewHolder>(){

    private var filmList: List<Film> = listOf()

    fun setFilmList(filmList: List<Film>) {
        this.filmList = filmList
        notifyDataSetChanged()
    }

    fun removeListener() {
        onItemViewClickListener = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFragmentViewHolder {
        val inflater = LayoutInflater.from(parent.context).apply {
            inflate(R.layout.item_fragment_main, parent, false)
        }
        val viewBinding = ItemFragmentMainBinding.inflate(inflater, parent, false)
        return MainFragmentViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = filmList.size

    override fun onBindViewHolder(holder: MainFragmentViewHolder, position: Int) {
        holder.viewBinding.run {
            filmTitle.text = filmList[position].title
            year.text = filmList[position].release_date
            rate.text = filmList[position].vote_average.toString()
            Picasso.get().load("https://image.tmdb.org/t/p/w780${filmList[position].poster_path}")
              .into(poster)
        }

        holder.itemView.setOnClickListener {
            onItemViewClickListener?.onItemViewClick(filmList[position])
        }
    }

    class MainFragmentViewHolder(val viewBinding: ItemFragmentMainBinding) : RecyclerView.ViewHolder(viewBinding.root)
}