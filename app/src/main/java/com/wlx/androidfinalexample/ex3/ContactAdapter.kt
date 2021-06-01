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
    inner class ViewHolder(val name: TextView, val phoneNumber: TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(resourceId, parent, false)
            val name: TextView = view.findViewById(R.id.name)
            val phoneNumber: TextView = view.findViewById(R.id.number)
            viewHolder = ViewHolder(name, phoneNumber)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        val contactItem = getItem(position)
        if (contactItem != null) {
            val phoneStr = contactItem.phoneNumber[0] +
                    if (contactItem.phoneNumber.size > 1) "等${contactItem.phoneNumber.size}个" else ""
            viewHolder.phoneNumber.text = phoneStr
            viewHolder.name.text = contactItem.name
        }

        return view
    }

}