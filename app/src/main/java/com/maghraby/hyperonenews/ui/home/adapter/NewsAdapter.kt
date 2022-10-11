package com.maghraby.hyperonenews.ui.home.adapter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maghraby.hyperonenews.R
import com.maghraby.hyperonenews.data.database.entity.NewsEntity
import com.maghraby.hyperonenews.data.models.NewModel
import javax.inject.Inject

class NewsAdapter @Inject constructor( private val news : ArrayList<NewsEntity>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>(){


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private fun rotate(iv: ImageView, value: Float){
            iv.apply {
                animate().rotation(value).setDuration(600)
            }
        }
        private fun toggleVisibility(tv: RelativeLayout, show: Boolean) {
            if(show){
                tv.apply {
                    alpha = 0f
                    visibility = VISIBLE
                }
                tv.animate().apply {
                    alpha(1f)
                    duration = 600
                    setListener(null)
                }
            }else{
                tv.animate().apply {
                    alpha(0f)
                    duration = 600
                    setListener(object  : AnimatorListenerAdapter(){
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                            tv.visibility = GONE
                        }
                    })

                }
            }
        }
        fun bind(index: NewsEntity) {
            if(index.isExpanded){
                itemView.findViewById<RelativeLayout>(R.id.moreRL).visibility = VISIBLE
                itemView.findViewById<ImageView>(R.id.moreButton).rotation = 180f
            }else{
                itemView.findViewById<RelativeLayout>(R.id.moreRL).visibility = GONE
                itemView.findViewById<ImageView>(R.id.moreButton).rotation = 0f
            }
            Glide.with(itemView.context)
                .load(index.urlToImage).dontAnimate()
                .into(itemView.findViewById(R.id.newIv))

            itemView.findViewById<TextView>(R.id.descriptionTv).text = index.description
            itemView.findViewById<TextView>(R.id.titleTv).text = index.title

            itemView.findViewById<ImageView>(R.id.moreButton).setOnClickListener {
                index.isExpanded = !index.isExpanded
                toggleVisibility(itemView.findViewById(R.id.moreRL),index.isExpanded)
                rotate( itemView.findViewById(R.id.moreButton),if(index.isExpanded) 180f else 0f)
            }
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

    fun addNews(list: List<NewsEntity>){
        news.clear()
        news.addAll(list)
    }

}