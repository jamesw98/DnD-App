package com.example.dndapp
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.view.Gravity
import android.widget.Toast

class NetworkChecker : BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?) {
        //when (intent?.action){
        //    ConnectivityManager.CONNECTIVITY_ACTION -> Toast.makeText(context, "Internet connection lost", Toast.LENGTH_SHORT).show()
        //}
        var toast = Toast.makeText(context, "Internet connection lost", Toast.LENGTH_LONG)
        toast.setGravity(Gravity.TOP, 0, 100)
        toast.show()
    }

}