package com.mirkamol.mydemo4recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mirkamol.mydemo4recyclerview.R
import com.mirkamol.mydemo4recyclerview.listener.onBottomReachedListener
import com.mirkamol.mydemo4recyclerview.model.Member

class CustomAdapter(val members: List<Member>, val listener: onBottomReachedListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_ITEM_YES_VIEW = 0
    private val TYPE_ITEM_NOT_VIEW = 1


    override fun getItemViewType(position: Int): Int {
        val member = members[position]
        return if (member.available) TYPE_ITEM_YES_VIEW else TYPE_ITEM_NOT_VIEW
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_ITEM_YES_VIEW) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_custom_layout_yes, parent, false)
            return CustomViewYesHolder(view)
        }

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_custom_layout_not, parent, false)
        return CustomViewNotHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (position == members.size - 1) {
            listener.onBottomReached(position)
        }


        val member = members.get(position)
        if (holder is CustomViewYesHolder) {
            holder.name.setText(member.name)
            holder.phoneNumber.setText(member.phoneNumber)
        }

        if (holder is CustomViewNotHolder) {
            holder.name.setText("This name is not available")
            holder.phoneNumber.setText("This phoneNumber is not available")
        }


    }

    override fun getItemCount(): Int {
        return members.size
    }


    class CustomViewYesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name = itemView.findViewById<TextView>(R.id.tv_name)
        val phoneNumber = itemView.findViewById<TextView>(R.id.phone_number)

    }

    class CustomViewNotHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name = itemView.findViewById<TextView>(R.id.tv_name_not)
        val phoneNumber = itemView.findViewById<TextView>(R.id.phone_number_not)

    }


}