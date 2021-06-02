package com.wlx.androidfinalexample.ex6

import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.wlx.androidfinalexample.R

class Ex6Activity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex6)
        drawerLayout = findViewById(R.id.drawer_layout)
        val webView: WebView = findViewById(R.id.web_view)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        val seiText: TextView = findViewById(R.id.sei_text)
        seiText.setOnClickListener {
            webView.loadUrl("http://www.sei.ecnu.edu.cn")
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        val edText: TextView = findViewById(R.id.ed_text)
        edText.setOnClickListener {
            webView.loadUrl("http://www.ed.ecnu.edu.cn")
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        val femText: TextView = findViewById(R.id.fem_text)
        femText.setOnClickListener {
            webView.loadUrl("http://fem.ecnu.edu.cn")
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_point_24)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }

}