package com.wlx.androidfinalexample.ex4

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.baidu.location.BDLocation
import com.baidu.location.BDLocationListener
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption
import com.baidu.mapapi.SDKInitializer
import com.baidu.mapapi.map.BaiduMap
import com.baidu.mapapi.map.MapStatusUpdateFactory
import com.baidu.mapapi.map.MapView
import com.baidu.mapapi.map.MyLocationData
import com.baidu.mapapi.model.LatLng
import com.wlx.androidfinalexample.R

class Ex4Activity : AppCompatActivity() {
    private val TAG = "Ex4Activity"
    private lateinit var mLocationClient: LocationClient

    private lateinit var positionText: TextView
    private lateinit var mapView: MapView
    private lateinit var baiduMap: BaiduMap
    private var isFirstLocate = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLocationClient = LocationClient(applicationContext)
        mLocationClient.registerLocationListener(MyLocationListener())
        SDKInitializer.initialize(applicationContext)
        setContentView(R.layout.activity_ex4)
        mapView = findViewById(R.id.map_view)
        baiduMap = mapView.map
        baiduMap.isMyLocationEnabled = true
        baiduMap.mapType = BaiduMap.MAP_TYPE_NORMAL
        positionText = findViewById(R.id.now_location)
        val permissionList = ArrayList<String>()
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE)
        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (permissionList.isNotEmpty()) {
            val permissions = permissionList.toTypedArray()
            ActivityCompat.requestPermissions(this, permissions, 1)
        } else {
            Log.d(TAG, "onCreate: 111")
            requestLocation()
        }
        val toEcnu: Button = findViewById(R.id.to_ecnu)
        toEcnu.setOnClickListener {
//            val locData = MyLocationData.Builder()
//                .latitude(31.233168)
//                .longitude(121.412119)
//                .build()
            val location = BDLocation()
            location.longitude = 121.412119
            location.latitude = 31.233168
            navigateTo(location)
//            baiduMap.setMyLocationData(locData)
            val ll = LatLng(location.latitude, location.longitude)
            var update = MapStatusUpdateFactory.newLatLng(ll)
            baiduMap.animateMapStatus(update)
            update = MapStatusUpdateFactory.zoomTo(18f)
            baiduMap.animateMapStatus(update)
            Log.d(TAG, "onCreate: to_ecnu")
        }
    }

    private fun navigateTo(location: BDLocation) {
        if (isFirstLocate) {
            Log.d(TAG, " " + location.latitude)
            Log.d(TAG, " " + location.longitude)
            Toast.makeText(this, "nav to " + location.addrStr, Toast.LENGTH_SHORT).show()
            val ll = LatLng(location.latitude, location.longitude)
            var update = MapStatusUpdateFactory.newLatLng(ll)
            baiduMap.animateMapStatus(update)
            update = MapStatusUpdateFactory.zoomTo(18f)
            baiduMap.animateMapStatus(update)
            isFirstLocate = false
        }
        val locationBuilder = MyLocationData.Builder()
        locationBuilder.latitude(location.latitude)
        locationBuilder.longitude(location.longitude)
        val locationData = locationBuilder.build()
        baiduMap.setMyLocationData(locationData)
    }

    private fun requestLocation() {
        initLocation()
        mLocationClient.start()
        Log.d(TAG, "requestLocation: 333")
    }

    private fun initLocation() {
        val option = LocationClientOption()
        option.isOpenGps = true
        option.setScanSpan(1000)
        option.setCoorType("bd09ll")
        option.setIsNeedAddress(true)
        mLocationClient.locOption = option
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mLocationClient.stop()
        mapView.onDestroy()
        baiduMap.isMyLocationEnabled = false
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> if (grantResults.isNotEmpty()) {
                for (result in grantResults) {
                    if (result != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show()
                        finish()
                        return
                    }
                }
                requestLocation()
            } else {
                Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show()
                finish()
            }
            else -> {
            }
        }
    }

    inner class MyLocationListener : BDLocationListener {
        override fun onReceiveLocation(location: BDLocation?) {
            val currentPosition = StringBuilder()
            location?.let {
                currentPosition.append("纬度：").append(location.getLatitude()).append("\n")
                currentPosition.append("经线：").append(location.getLongitude()).append("\n")
                currentPosition.append("国家：").append(location.getCountry()).append("\n")
                currentPosition.append("省：").append(location.getProvince()).append("\n")
                currentPosition.append("市：").append(location.getCity()).append("\n")
                currentPosition.append("区：").append(location.getDistrict()).append("\n")
                currentPosition.append("街道：").append(location.getStreet()).append("\n")
//                if (location.getLocType() == BDLocation.TypeGpsLocation) {
//                    currentPosition.append("GPS")
//                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
//                    currentPosition.append("网络")
//                }
                positionText.text = currentPosition
//                val locData = MyLocationData.Builder()
//                    .accuracy(location.radius)
//                    .direction(location.direction)
//                    .latitude(location.latitude)
//                    .longitude(location.longitude)
//                    .build()
//                baiduMap.setMyLocationData(locData)
                if (location.locType == BDLocation.TypeGpsLocation
                    || location.locType == BDLocation.TypeNetWorkLocation
                ) {
                    navigateTo(location)
                }

//                location.latitude = 31.230623
//                location.longitude = 121.403342
//                navigateTo(location)
            }
        }
    }
}