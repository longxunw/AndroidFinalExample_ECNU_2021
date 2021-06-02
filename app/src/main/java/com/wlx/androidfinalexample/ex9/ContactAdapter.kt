package com.wlx.androidfinalexample.ex9

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wlx.androidfinalexample.R

class ContactAdapter(val context: Context, val dataList: List<ContactItem>) :
    RecyclerView.Adapter<ContactAdapter.ViewHoler>() {
    inner class ViewHoler(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val phoneNum: TextView = view.findViewById(R.id.phone_num)
        val email: TextView = view.findViewById(R.id.email)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoler {
        return ViewHoler(LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHoler, position: Int) {
        val item = dataList[position]
        holder.name.text = item.name
        holder.phoneNum.text = item.phoneNum
        holder.email.text = item.email
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}