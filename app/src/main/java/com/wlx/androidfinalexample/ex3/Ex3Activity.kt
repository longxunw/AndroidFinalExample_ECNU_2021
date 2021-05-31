package com.wlx.androidfinalexample.ex3

import android.os.Bundle
import android.provider.ContactsContract
import androidx.appcompat.app.AppCompatActivity
import com.wlx.androidfinalexample.R

class Ex3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex3)
    }

    private fun getContact(): ArrayList<ContactItem> {
        val contactList = ArrayList<ContactItem>()
        val cr = applicationContext.contentResolver
        val cursor = cr.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            arrayOf(
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
            ), null, null, null
        )
        if (cursor != null) {
            while (cursor.moveToNext()) {
                contactList.add(
                    ContactItem(
                        cursor.getString(
                            cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME),
                        ), cursor.getString(
                            cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER),
                        ),
                        ""
                    )

                )
            }
        }
        return contactList
    }
}