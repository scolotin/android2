package com.example.android2.view.actors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android2.R
import com.example.android2.databinding.ItemFragmentActorsBinding
import com.example.android2.model.Actor
import com.example.android2.model.OnActorsItemViewClickListener

class ActorsFragmentAdapter(private var onItemViewClickListener: OnActorsItemViewClickListener?) : RecyclerView.Adapter<ActorsFragmentAdapter.ActorsFragmentViewHolder>(){

    private var actorsList: List<Actor> = listOf()

    fun setActorsList(actorsList: List<Actor>) {
        this.actorsList = actorsList
        notifyDataSetChanged()
    }

    fun removeListener() {
        onItemViewClickListener = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsFragmentViewHolder {
        val inflater = LayoutInflater.from(parent.context).apply {
            inflate(R.layout.item_fragment_actors, parent, false)
        }
        val viewBinding = ItemFragmentActorsBinding.inflate(inflater, parent, false)
        return ActorsFragmentViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = actorsList.size

    override fun onBindViewHolder(holder: ActorsFragmentViewHolder, position: Int) {
        holder.viewBinding.run {
            actorName.text = actorsList[position].name
        }

        holder.itemView.setOnClickListener {
            onItemViewClickListener?.onItemViewClick(actorsList[position])
        }
    }

    class ActorsFragmentViewHolder(val viewBinding: ItemFragmentActorsBinding) : RecyclerView.ViewHolder(viewBinding.root)
}