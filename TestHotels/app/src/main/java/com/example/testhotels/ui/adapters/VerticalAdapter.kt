package com.example.testhotels.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testhotels.databinding.ItemRoomBinding
import com.example.testhotels.entity.room.Room
import com.example.testhotels.ui.RoomFragment

class VerticalAdapter(
    //private val fragment: RoomFragment
) :
    RecyclerView.Adapter<VerticalViewHolder>() {

    private var roomList: List<Room> = emptyList()

    fun setRooms(rooms: List<Room>) {
        roomList = rooms
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalViewHolder {
        return VerticalViewHolder(
            ItemRoomBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VerticalViewHolder, position: Int) {
        val featuresAdapter = FeaturesAdapter(roomList[position].peculiarities)
        val viewPagerAdapter = ViewPagerAdapter(roomList[position].image_urls)

        holder.binding.viewpagerRoom.adapter = viewPagerAdapter
        holder.binding.recyclerPeculiarities.adapter = featuresAdapter
        holder.binding.indicatorRoom.setViewPager(holder.binding.viewpagerRoom)
        holder.binding.roomName.text = roomList[position].name
        holder.binding.price.text = "${roomList[position].price} P"
        holder.binding.zaTyp.text = roomList[position].price_per

    }


    override fun getItemCount(): Int = roomList.size
}
class VerticalViewHolder(val binding: ItemRoomBinding) :
    RecyclerView.ViewHolder(binding.root)