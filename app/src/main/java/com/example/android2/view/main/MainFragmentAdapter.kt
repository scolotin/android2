package com.example.android2.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android2.R
import com.example.android2.databinding.ItemFragmentMainBinding
import com.example.android2.model.Film
import com.example.android2.model.OnItemViewClickListener

class MainFragmentAdapter(private var onItemViewClickListener: OnItemViewClickListener?) : RecyclerView.Adapter<MainFragmentViewHolder>(){
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
        return MainFragmentViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = filmList.size

    override fun onBindViewHolder(holder: MainFragmentViewHolder, position: Int) = holder.itemView.run {
        viewBinding.filmTitle.text = filmList[position].name
        viewBinding.year.text = filmList[position].year.toString()
        viewBinding.rate.text = filmList[position].rate.toString()
        setOnClickListener {
            onItemViewClickListener?.onItemViewClick(filmList[position])
        }
    }
}

class MainFragmentViewHolder(itemFragmentMainBinding: ItemFragmentMainBinding) : RecyclerView.ViewHolder(itemFragmentMainBinding.root)