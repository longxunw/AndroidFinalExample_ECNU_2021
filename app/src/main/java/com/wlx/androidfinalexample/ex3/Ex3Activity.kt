package com.wlx.androidfinalexample.ex3

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.wlx.androidfinalexample.R

class Ex3Activity : AppCompatActivity() {
    private val TAG = "Ex3Activity"
    private val contractMap = HashMap<String, ContactItem>()
    private val contractList = ArrayList<ContactItem>()
    private lateinit var curContract: ContactItem
    private lateinit var adapter: ContactAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex3)
        requestContactPermission()
        val listView: ListView = findViewById(R.id.list)
        contractList.clear()
        for (key in contractMap.keys) {
            contractList.add(contractMap[key]!!)
        }
        adapter = ContactAdapter(this, R.layout.ex3_list_item, contractList)
        listView.adapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            curContract = contractList[position]
            Log.d(TAG, "onCreate: ${curContract.toString()}")
            showDialog()
        }
    }

    private fun getContact() {
        this.contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null, null, null, null
        )?.apply {
            while (moveToNext()) {
                val contactItem = ContactItem(
                    getString(getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)),
                    arrayListOf(), arrayListOf()
                )
                val contactId = getString(getColumnIndex(ContactsContract.Contacts._ID))
                applicationContext.contentResolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                    null, null
                )?.apply {
                    while (moveToNext()) {
                        contactItem.phoneNumber.add(getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)))
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
                        contactItem.email.add(getString(getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA)))
                    }
                    close()
                }
                contractMap[contactId] = contactItem
            }
            close()
        }
        Log.d(TAG, "getContact: ${contractMap.toString()}")
    }

    private fun showDialog() {
        AlertDialog.Builder(this).apply {
            setTitle("联系人信息")
            setMessage(
                "姓名：${curContract.name}\n电话：${curContract.phoneNumber[0]}\n邮箱：" +
                        if (curContract.email.isNotEmpty()) curContract.email[0] else "暂无邮箱"
            )
            setCancelable(false)
            setPositiveButton("拨打电话") { dialog, which ->
                requestCallPermission()
            }
            setNegativeButton("取消") { dialog, which ->

            }
            show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            0 -> {
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    getContact()
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show()
                }
            }
            1 -> {
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    call()
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show()
                }
            }
        }
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED && requestCode == 1) {
            getContact()
        } else {
            Toast.makeText(this, "获取联系人权限失败", Toast.LENGTH_SHORT).show()
        }
    }

    private fun requestCallPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
        } else {
            call()
        }
    }

    private fun requestContactPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), 0)
        } else {
            getContact()
        }
    }

    private fun call() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:${curContract.phoneNumber[0]}")
            startActivity(intent)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

}