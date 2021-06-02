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

    private val createEx10 = "create table ex10 (" +
            " string text)"

    private val createEx11 = "create table ex11 (" +
            " num integer)"


    override fun onCreate(db: SQLiteDatabase?) {
        db?.let {
            db.execSQL(createContact)
            db.execSQL(createEx10)
            db.execSQL(createEx11)
        }

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion == 1 && newVersion == 2) {
            db?.let {
                db.execSQL(createEx10)
                db.execSQL(createEx11)
            }
        }
    }

}