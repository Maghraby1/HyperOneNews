package com.maghraby.hyperonenews.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maghraby.hyperonenews.R
import com.maghraby.hyperonenews.data.models.NewModel
import javax.inject.Inject

class NewsAdapter @Inject constructor( private val news : ArrayList<NewModel>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>(){


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(index: NewModel) {

            Glide.with(itemView.context)
                .load(index.urlToImage).dontAnimate()
                .into(itemView.findViewById(R.id.newIv))

            itemView.findViewById<TextView>(R.id.titleTv).text = index.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(news[position])
    }

    override fun getItemCount(): Int {
        return news.size
    }

    fun addNews(list: List<NewModel>){
        news.clear()
        news.addAll(list)
    }
}