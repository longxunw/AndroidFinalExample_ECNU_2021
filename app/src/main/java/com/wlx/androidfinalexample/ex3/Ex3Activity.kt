package com.wlx.androidfinalexample.ex3

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.wlx.androidfinalexample.R

class Ex3Activity : AppCompatActivity() {
    private val TAG = "Ex3Activity"
    private lateinit var contractList: ArrayList<ContactItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex3)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            Log.d(TAG, "onCreate: ")
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.READ_CONTACTS), 1
            )
        }
//        contractList = getContact()
//        val listView: ListView = findViewById(R.id.list)
//        val adapter = ContactAdapter(applicationContext, R.layout.ex3_list_item, contractList)
//        listView.adapter = adapter
//        listView.setOnItemClickListener { parent, view, position, id ->
//            val contract = contractList[position]
//        }
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

    private fun getEmail() {

    }

    private fun showDialog() {

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED && requestCode == 1) {
            contractList = getContact()
        } else {
            Toast.makeText(this, "获取联系人权限失败", Toast.LENGTH_SHORT).show()
        }
    }

}