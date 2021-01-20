package com.tech.locationtest

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    var tv1:TextView?=null
    var tv2:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv1=findViewById(R.id.txt_lat)
        tv2=findViewById(R.id.txt_long)



      var status=  ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)
        if(status==PackageManager.PERMISSION_GRANTED)
        {
            getCurrentLocation()
        }
        else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION),15)
        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            getCurrentLocation()
        }else
        {
            Toast.makeText(this,"user is not allowed",Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("MissingPermission")
    fun getCurrentLocation() {

        //step 1
       var locationManager= getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)


        //2 kind of providers
            //1.GPS   2.Network
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
            1000.toLong(),
            1.toFloat(),
            object : LocationListener {
                override fun onLocationChanged(location: Location) {

                    tv1?.text=location.latitude.toString()
                    tv2?.text=location.longitude.toString()
                }

            })

//        locationManager.removeUpdates( object : LocationListener {
//            override fun onLocationChanged(location: Location) {
//
//                tv1?.text=location.latitude.toString()
//                tv2?.text=location.longitude.toString()
//            }
//
//        })
    }


}