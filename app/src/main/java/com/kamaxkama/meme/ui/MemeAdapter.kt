package com.kamaxkama.meme.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kamaxkama.meme.DetailActivity
import com.kamaxkama.meme.R
import com.kamaxkama.core.domain.model.Meme

class MemeAdapter(private val memeList: List<Meme>) :
    RecyclerView.Adapter<MemeAdapter.MemeViewHolder>() {

    class MemeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgMeme: ImageView = view.findViewById(R.id.imgMeme)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
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

        // ✅ Taruh di dalam onBindViewHolder
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_MEME, meme)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = memeList.size
}
