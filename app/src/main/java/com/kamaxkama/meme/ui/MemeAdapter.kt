package com.kamaxkama.meme.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kamaxkama.core.domain.model.Meme
import com.kamaxkama.meme.R

class MemeAdapter(
    private val memeList: List<Meme>,
    private val onItemClick: (Meme) -> Unit
) : RecyclerView.Adapter<MemeAdapter.MemeViewHolder>() {

    class MemeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val imgMeme: ImageView = view.findViewById(R.id.imgMeme)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meme, parent, false)
        return MemeViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemeViewHolder, position: Int) {
        val meme = memeList[position]
        holder.tvTitle.text = meme.title
        Glide.with(holder.itemView.context)
            .load(meme.imageUrl)
            .into(holder.imgMeme)

        holder.itemView.setOnClickListener {
            onItemClick(meme)
        }
    }

    override fun getItemCount(): Int = memeList.size
}
