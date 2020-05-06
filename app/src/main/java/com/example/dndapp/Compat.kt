package com.example.dndapp


import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf

class Compat : AppCompatActivity() {
    var musicService: MusicService? = null
    var startMusicServiceIntent: Intent? = null
    var isInitialized = false
    var isBound = false

    private val musicServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
            val binder = iBinder as MusicService.MyBinder
            musicService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(componentName: ComponentName) {
            musicService = null
            isBound = false
        }
    }



    override fun onResume() {
        super.onResume()
        if (isInitialized && !isBound) {
            bindService(startMusicServiceIntent, musicServiceConnection, Context.BIND_AUTO_CREATE)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(SoundBoard.INITIALIZE_STATUS, isInitialized)
        super.onSaveInstanceState(outState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //this restores the textview
        if (savedInstanceState != null) {
            isInitialized = savedInstanceState.getBoolean(SoundBoard.INITIALIZE_STATUS)
        }
        startMusicServiceIntent = Intent(this, MusicService::class.java)
        if (!isInitialized) {
            startService(startMusicServiceIntent)
            isInitialized = true
        }

    }
}