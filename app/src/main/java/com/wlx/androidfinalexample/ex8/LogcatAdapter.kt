package com.wlx.androidfinalexample.ex8

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.wlx.androidfinalexample.R

class LogcatAdapter(val ex8Activity: Ex8Activity, val logcatList: List<Logcat>) :
    RecyclerView.Adapter<LogcatAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val time: TextView = view.findViewById(R.id.logcat_time)
        val title: TextView = view.findViewById(R.id.logcat_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(ex8Activity).inflate(R.layout.logcat_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = logcatList[position]
        holder.time.text = item.time
        holder.title.text = item.title
        holder.itemView.setOnClickListener {
            AlertDialog.Builder(ex8Activity).apply {
                setTitle("联系人信息")
                setMessage(
                    "时间：${item.time}\n标题：${item.title}\n描述：${item.content}"
                )
                setCancelable(false)
                setPositiveButton("删除") { dialog, which ->
                    ex8Activity.deleteLogcat(item.id)
                }
                setNegativeButton("取消") { dialog, which ->

                }
                show()
            }
        }
    }

    override fun getItemCount(): Int {
        return logcatList.size
    }
}