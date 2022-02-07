package com.example.mockexam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load

class LagosAdapters : ListAdapter<LagosSecondData, LagosAdapters.LagosHolders>(DiffCallback) {
    class LagosHolders(view: View) : RecyclerView.ViewHolder(view) {
        val ownerNaming = view.findViewById<TextView>(R.id.ownerName)
        val ownerImaging = view.findViewById<ImageView>(R.id.ownerImage)
        val favorite = view.findViewById<Button>(R.id.favoriteButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LagosHolders {
        return LagosAdapters.LagosHolders(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))
    }

    override fun onBindViewHolder(holder: LagosHolders, position: Int) {
    holder.ownerImaging.load(getItem(position).avatar_url) {
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_broken)
    }
        holder.ownerNaming.text = getItem(position).login
        holder.favorite.setOnClickListener {
            val lists = getItem(position)
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(
                names = lists.login,
                theUrl = lists.url,
                id = lists.id,
                avatar = lists.avatar_url
            )
            Navigation.findNavController(it).navigate(action)

        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<LagosSecondData>() {
            override fun areItemsTheSame(
                oldItem: LagosSecondData,
                newItem: LagosSecondData
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: LagosSecondData,
                newItem: LagosSecondData
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

}