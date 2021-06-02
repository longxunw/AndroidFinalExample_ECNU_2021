package com.wlx.androidfinalexample.ex8

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wlx.androidfinalexample.R

class Ex8Activity : AppCompatActivity() {
    companion object {
        val logcatList = arrayListOf<Logcat>(
            Logcat(0, "2021-06-02 14:35:25.907", "D/Ex7Service", "onDestroy: "),
            Logcat(
                1,
                "2021-01-02 13:36:25.909",
                "W/System.err",
                "at com.wlx.androidfinalexample.ex7.Ex7Service\$Ex7Thread.run"
            ),
            Logcat(2, "2021-06-02 14:36:43.264", "D/Ex7Service", "onStartCommand:")
        )
    }

    private lateinit var adapter: LogcatAdapter
    private val TAG = "Ex8Activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex8)
        val recyclerView: RecyclerView = findViewById(R.id.logcat_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = LogcatAdapter(this, logcatList)
        recyclerView.adapter = adapter
    }

    fun deleteLogcat(id: Int) {
        for (logcat in logcatList) {
            if (id == logcat.id) {
                logcatList.remove(logcat)
                break
            }
        }
        adapter.notifyDataSetChanged()
    }

}