package com.example.dndapp


import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf

class SoundBoard : Fragment() {

    companion object {
        const val TAG = "HOKIECOMP_TAG"
        const val USERNAME = "CS3714"
        const val URL = "https://posthere.io/"
        const val ROUTE ="2454-4b47-a614"
        const val INITIALIZE_STATUS = "intialization status"
        const val MUSIC_PLAYING = "music playing"
    }

    val backGroundMusicSpinner: Spinner? = null

    val play: Button? = null

    var musicSelection: String? = null

    var trackPlaying: Boolean? = null



    var musicService: MusicService? = null
    var startMusicServiceIntent: Intent? = null
    var isInitialized = false
    var isBound = false



    override fun onPause() {
        super.onPause()
        if (isBound) {
            activity?.unbindService(musicServiceConnection)
            isBound = false
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(INITIALIZE_STATUS, isInitialized)
        super.onSaveInstanceState(outState)
    }

    override fun onResume() {
        super.onResume()

        if (isInitialized && !isBound) {
            activity?.bindService(startMusicServiceIntent, musicServiceConnection, Context.BIND_AUTO_CREATE)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            isInitialized = savedInstanceState.getBoolean(INITIALIZE_STATUS)
        }
        startMusicServiceIntent = Intent(activity?.applicationContext, MusicService::class.java)
        if (!isInitialized) {
            activity?.startService(startMusicServiceIntent)
            isInitialized = true
        }
    }

    private val musicServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
            Log.d(">>Test", "service connection")
            val binder = iBinder as MusicService.MyBinder
            musicService = binder.getService()
            isBound = true
            when (musicService?.getPlayingStatus()) {
                0 -> play?.setText("Start")
                1 -> play?.setText("Pause")
                2 -> play?.setText("Resume")
            }
        }

        override fun onServiceDisconnected(componentName: ComponentName) {
            musicService = null
            isBound = false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_soundboard, container, false)
        val backgroundMusicSpinner: Spinner? = view?.findViewById(R.id.backgroundMusic)
        val play: Button? = view?.findViewById(R.id.playButton)


        ArrayAdapter.createFromResource(
            activity!!.applicationContext,
            R.array.backgroundMusicList,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            backgroundMusicSpinner?.adapter = adapter
        }
        trackPlaying = false

        backgroundMusicSpinner?.setSelection(0)
        musicSelection = backgroundMusicSpinner?.adapter?.getItem(0).toString().toLowerCase()
        backgroundMusicSpinner?.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // intentionally blank
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                musicSelection = parent?.getItemAtPosition(position).toString().toLowerCase()
                musicService?.pauseMusic()

                musicService?.startMusic(musicSelection)
                musicService?.pauseMusic()
                play?.setText("Play")
            }
        }

        play?.setOnClickListener{
            if (isBound) {
                Log.d(">>test", "music should be playing")
                when (musicService?.getPlayingStatus()) {
                    0 -> {
                        musicService?.startMusic(musicSelection)
                        play?.setText("Pause")
                        trackPlaying = true
                    }
                    1 -> {
                        musicService?.pauseMusic()
                        play?.setText("Play")
                    }
                    2 -> {
                        musicService?.resumeMusic()
                        play?.setText("Pause")
                        trackPlaying = true
                    }
                }
            }
        }

        return view
    }
}