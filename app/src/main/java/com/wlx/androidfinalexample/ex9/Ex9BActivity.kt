package com.wlx.androidfinalexample.ex9

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wlx.androidfinalexample.MyDatabaseHelper
import com.wlx.androidfinalexample.R

class Ex9BActivity : AppCompatActivity() {
    private val contactList = ArrayList<ContactItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex9_b)
        val dbHelper = MyDatabaseHelper(this, "Exercise.db", 1)
        val db = dbHelper.writableDatabase
        val sql = "select * from contact order by name asc"
        val cursor = db.rawQuery(sql, null)
        cursor.apply {
            while (moveToNext()) {
                val item = ContactItem(
                    getString(getColumnIndex("id")),
                    getString(getColumnIndex("name")),
                    getString(getColumnIndex("phone_num")),
                    getString(getColumnIndex("email"))
                )
                contactList.add(item)
            }
            close()
        }
        val recyclerView: RecyclerView = findViewById(R.id.contact_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ContactAdapter(this, contactList)
    }
}