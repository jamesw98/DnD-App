package com.example.dndapp
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import android.view.Gravity
import android.widget.Toast

class NetworkChecker : BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?) {
        val conn = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = conn.activeNetworkInfo

        if (networkInfo?.type != ConnectivityManager.TYPE_WIFI){
            var toast = Toast.makeText(context, "Internet connection lost. Searching, Saving, and Loading Characters will not work", Toast.LENGTH_LONG)
            toast.show()
        }
    }
}