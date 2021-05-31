package com.wlx.androidfinalexample.ex3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.wlx.androidfinalexample.R

class ContactAdapter(context: Context, private val resourceId: Int, data: List<ContactItem>) :
    ArrayAdapter<ContactItem>(context, resourceId, data) {
    inner class ViewHolder {
        lateinit var name: TextView
        lateinit var phoneNumber: TextView
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val contactItem = getItem(position)
        lateinit var view: View
        lateinit var viewHolder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(resourceId, parent, false)
            viewHolder = ViewHolder()
            viewHolder.name = view.findViewById(R.id.name)
            viewHolder.phoneNumber = view.findViewById(R.id.number)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        if (contactItem != null) {
            viewHolder.phoneNumber.text = contactItem.phoneNumber
            viewHolder.name.text = contactItem.name
        }

        return view
    }

}