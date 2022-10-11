package com.maghraby.hyperonenews.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.maghraby.hyperonenews.R
import com.maghraby.hyperonenews.data.models.Source
import javax.inject.Inject

class SourcesAdapter @Inject constructor(private val sources: ArrayList<Source>) : RecyclerView.Adapter<SourcesAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(source: Source){
            itemView.findViewById<TextView>(R.id.sourceNameTV).text = source.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.source_item,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(sources[position])
    }

    override fun getItemCount(): Int {
        return sources.size
    }
    fun addSources(list: List<Source>){
        sources.clear()
        sources.addAll(list)
    }

}