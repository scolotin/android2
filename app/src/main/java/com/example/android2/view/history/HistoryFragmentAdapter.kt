package com.example.android2.view.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android2.R
import com.example.android2.databinding.ItemFragmentHistoryBinding
import com.example.android2.model.Film

class HistoryFragmentAdapter : RecyclerView.Adapter<HistoryFragmentAdapter.RecyclerItemViewHolder>() {

    private var data: List<Film> = arrayListOf()

    fun setData(data: List<Film>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val inflater = LayoutInflater.from(parent.context).apply {
            inflate(R.layout.item_fragment_history, parent, false)
        }
        val viewBinding = ItemFragmentHistoryBinding.inflate(inflater, parent, false)
        return RecyclerItemViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(private val viewBinding: ItemFragmentHistoryBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(film: Film) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                if (film.note != null) {
                    viewBinding.recyclerViewItem.text = String.format("\"%s\" %s %s", film.title, film.release_date, film.note)
                }
                else {
                    viewBinding.recyclerViewItem.text = String.format("\"%s\" %s", film.title, film.release_date)
                }
            }
        }
    }
}