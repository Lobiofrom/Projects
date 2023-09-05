package com.example.testhotels.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testhotels.R

class FeaturesAdapter(
    private var features: List<String>
) : RecyclerView.Adapter<FeaturesAdapter.FeaturesViewHolder>() {

    class FeaturesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.peculiarities)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturesViewHolder {
        return FeaturesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_peculiarities,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return features.size
    }

    override fun onBindViewHolder(holder: FeaturesViewHolder, position: Int) {
        holder.text.text = features[position]
    }
}
