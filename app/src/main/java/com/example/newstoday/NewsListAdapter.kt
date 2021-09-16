package com.example.newstoday

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter(private val listener : NewsItemClicked) : RecyclerView.Adapter<NewsViewHolder>() {
    private val items: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        val newsViewHolder = NewsViewHolder(view)

        view.setOnClickListener {
            listener.onItemClicked(items[newsViewHolder.adapterPosition])
        }
        return newsViewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.newsTitle.text = currentItem.newsTitle
        holder.newsAuthor.text = currentItem.newsAuthor
        Glide.with(holder.newsImage).load(currentItem.imageUrl).into(holder.newsImage)
    }

    fun updateNews(updatedNews : ArrayList<News>){
        items.clear()
        items.addAll(updatedNews)
        notifyDataSetChanged()
    }

}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val newsTitle:TextView = itemView.findViewById(R.id.tvNewsTitle)
    val newsImage:ImageView  = itemView.findViewById(R.id.ivNewsImage)
    val newsAuthor:TextView = itemView.findViewById(R.id.tvAuthor)
}

interface NewsItemClicked{
    fun onItemClicked(item : News)
}

