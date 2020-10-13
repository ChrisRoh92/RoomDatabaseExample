package com.example.roomdatabaseexample.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.repository.database.Voc

class VocListAdapter(var content:ArrayList<Voc>):RecyclerView.Adapter<VocListAdapter.ViewHolder>()
{
    // Array with the different status drawables:
    private val statusDrawables = arrayOf(R.drawable.ic_open,R.drawable.ic_work,R.drawable.ic_done)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_voc,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return content.size
    }

    override fun onBindViewHolder(holder: VocListAdapter.ViewHolder, position: Int) {
        val voc = content[position]
        holder.tvMain.text = voc.foreignWord
        holder.tvSub.text = voc.nativeWord
        holder.image.setImageResource(statusDrawables[voc.status])
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val tvMain:TextView = itemView.findViewById(R.id.item_main)
        val tvSub:TextView = itemView.findViewById(R.id.item_sub)
        val image:ImageView = itemView.findViewById(R.id.item_image)
    }

    fun updateContent(content:ArrayList<Voc>)
    {
        this.content = content
        notifyDataSetChanged()
    }

}