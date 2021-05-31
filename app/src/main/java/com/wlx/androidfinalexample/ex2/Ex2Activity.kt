package com.wlx.androidfinalexample.ex2

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.wlx.androidfinalexample.R
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import kotlin.concurrent.thread

class Ex2Activity : AppCompatActivity() {
    private lateinit var code: EditText
    private lateinit var value:EditText
    private lateinit var msg:EditText
    private val TAG = " Ex2Activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex2)
        val get: Button = findViewById(R.id.get)
        code = findViewById(R.id.return_code)
        value= findViewById(R.id.return_value)
        msg = findViewById(R.id.return_msg)

        get.setOnClickListener {
            thread {
                try{
                    val client = OkHttpClient()
                    val request = Request.Builder()
                        .url("http://115.29.231.93:8080/CkeditorTest/AndroidTest?userId=10185101276&style=json")
                        .build()
                    Log.d(TAG, "onCreate: 111")
                    val response = client.newCall(request).execute()
                    Log.d(TAG, "onCreate: 222")
                    val responseData = response.body?.string()
                    responseData?.run {
                        updateText(responseData)
                    }
                    Log.d(TAG, "onCreate: 3333")
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
    }
    private fun updateText(jsonData: String){
        val jsonObject = JSONObject(jsonData)
        val returnCode = jsonObject.getInt("returnCode")
        code.setText(returnCode.toString())
        if(returnCode>=0){
            val returnValue = jsonObject.getString("returnValue")
            value.setText(returnValue)
        }else{
            val returnMsg = jsonObject.getString("returnMsg")
            msg.setText(returnMsg)
        }
    }
}