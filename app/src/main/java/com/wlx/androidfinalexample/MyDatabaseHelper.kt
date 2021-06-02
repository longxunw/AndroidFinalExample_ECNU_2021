package com.wlx.androidfinalexample

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(val context: Context, name: String, version: Int) :
    SQLiteOpenHelper(context, name, null, version) {

    private val createContact = "create table contact (" +
            " id text primary key," +
            "name text," +
            "phone_num text," +
            "email text)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createContact)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}