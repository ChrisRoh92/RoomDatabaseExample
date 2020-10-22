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

    // Interface:
    private lateinit var mItemListener:OnItemClickListener
    private lateinit var mItemLongListener:OnItemLongClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_voc,parent,false)
        return ViewHolder(view,mItemListener,mItemLongListener)
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

    class ViewHolder(itemView: View,mItemListener:OnItemClickListener,mItemLongListener:OnItemLongClickListener):RecyclerView.ViewHolder(itemView)
    {
        val tvMain:TextView = itemView.findViewById(R.id.item_main)
        val tvSub:TextView = itemView.findViewById(R.id.item_sub)
        val image:ImageView = itemView.findViewById(R.id.item_image)

        init {
            // Implement simple OnClickListener for each Entry
            itemView.setOnClickListener {
                mItemListener?.setOnItemClickListener(adapterPosition)
            }

            // Implement simple OnLongClickListener for each Entry:
            itemView.setOnLongClickListener {
                mItemLongListener?.setOnItemLongClickListener(adapterPosition)
                true
            }

        }
    }

    fun updateContent(content:ArrayList<Voc>)
    {
        this.content = content
        notifyDataSetChanged()
    }

    // OnItemClickListener:
    interface OnItemClickListener
    {
        fun setOnItemClickListener(pos:Int)
    }

    fun setOnItemClickListener(mItemListener:OnItemClickListener)
    {
        this.mItemListener = mItemListener
    }

    // OnLongItemClickListener:
    interface OnItemLongClickListener
    {
        fun setOnItemLongClickListener(pos:Int)
    }
    fun setOnItemLongClickListener(mItemLongListener:OnItemLongClickListener)
    {
        this.mItemLongListener = mItemLongListener
    }

}