package com.example.android2.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android2.R
import com.example.android2.databinding.ItemFragmentMainBinding
import com.example.android2.model.Film
import com.example.android2.model.OnItemViewClickListener
import com.squareup.picasso.Picasso

class MainFragmentAdapter(private var onItemViewClickListener: OnItemViewClickListener?) : RecyclerView.Adapter<MainFragmentAdapter.MainFragmentViewHolder>(){

    private lateinit var viewBinding: ItemFragmentMainBinding

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
        viewBinding = ItemFragmentMainBinding.inflate(inflater, parent, false)
        return MainFragmentViewHolder(viewBinding.root)
    }

    override fun getItemCount(): Int = filmList.size

    override fun onBindViewHolder(holder: MainFragmentViewHolder, position: Int) {
        viewBinding.filmTitle.text = filmList[position].title
        viewBinding.year.text = filmList[position].release_date
        viewBinding.rate.text = filmList[position].vote_average.toString()
        Picasso.get().load("https://image.tmdb.org/t/p/w780${filmList[position].poster_path}")
            .into(viewBinding.poster)

        holder.itemView.setOnClickListener {
            onItemViewClickListener?.onItemViewClick(filmList[position])
        }
    }

    class MainFragmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}