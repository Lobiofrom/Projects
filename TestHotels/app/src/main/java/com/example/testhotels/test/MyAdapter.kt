package com.example.testhotels.test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testhotels.databinding.ItemPassengerBinding

class MyAdapter(
    private var itemList: List<Item>
) : RecyclerView.Adapter<MyViewHolder>() {
    
    fun addItem(item: Item) {
        val updatedList = itemList.toMutableList()
        updatedList.add(item)
        itemList = updatedList
        notifyItemInserted(itemList.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemPassengerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemList[position]

        holder.binding.text.text = currentItem.text
        holder.binding.name.text = currentItem.name
        holder.binding.surname.text = currentItem.surname
        holder.binding.birthdate.text = currentItem.birthdate
        holder.binding.nationality.text = currentItem.nationality
        holder.binding.passportN.text = currentItem.passportN
        holder.binding.passportTime.text = currentItem.passportTime

    }

    override fun getItemCount() = itemList.size
}

class MyViewHolder(val binding: ItemPassengerBinding) : RecyclerView.ViewHolder(binding.root)

