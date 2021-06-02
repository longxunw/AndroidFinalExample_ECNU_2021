package com.wlx.androidfinalexample.ex9

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wlx.androidfinalexample.MyDatabaseHelper
import com.wlx.androidfinalexample.R

class Ex9Activity : AppCompatActivity() {
    private val contractMap = HashMap<String, ContactItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex9)
        val dbHelper = MyDatabaseHelper(this, "Exercise.db", 1)
        val launchBtn: Button = findViewById(R.id.launch)
        val displayBtn: Button = findViewById(R.id.display)
        launchBtn.setOnClickListener {
            getContact()
            val db = dbHelper.writableDatabase
            for (key in contractMap.keys) {
                val item = contractMap[key] as ContactItem
                val value = ContentValues().apply {
                    put("id", item.id)
                    put("name", item.name)
                    put("phone_num", item.phoneNum)
                    put("email", item.email)
                }
                db.insert("contact", null, value)
            }
            Toast.makeText(this, "加载完毕", Toast.LENGTH_SHORT).show()
        }
        displayBtn.setOnClickListener {
            val intent = Intent(this, Ex9BActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getContact() {
        this.contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null, null, null, null
        )?.apply {
            while (moveToNext()) {
                val contactId = getString(getColumnIndex(ContactsContract.Contacts._ID))
                val contactItem = ContactItem(
                    contactId,
                    getString(getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)),
                    "", ""
                )
                applicationContext.contentResolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                    null, null
                )?.apply {
                    while (moveToNext()) {
                        contactItem.phoneNum =
                            getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                        break
                    }
                    close()
                }
                applicationContext.contentResolver.query(
                    ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + contactId,
                    null, null
                )?.apply {
                    while (moveToNext()) {
                        contactItem.email =
                            getString(getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA))
                        break
                    }
                    close()
                }
                contractMap[contactId] = contactItem
            }
            close()
        }
    }
}