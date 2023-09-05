package com.example.testhotels.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.testhotels.R

class ViewPagerAdapter(
    private var onboardingPicture: List<String>
) : RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {

    class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemOnboardingPicture: ImageView = itemView.findViewById(R.id.onboarding_picture)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        return PagerViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_viewpager,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return onboardingPicture.size
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.itemOnboardingPicture.load(onboardingPicture[position])
    }
}
