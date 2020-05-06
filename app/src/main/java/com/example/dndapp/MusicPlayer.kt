package com.example.dndapp


import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import java.io.IOException
import java.util.*
import android.media.MediaPlayer.create as create1


class MusicPlayer(val musicService: MusicService): MediaPlayer.OnCompletionListener {

    val MUSICPATH = arrayOf(R.raw.dungeon, R.raw.battle, R.raw.goblincave, R.raw.nicevillage, R.raw.spookyvillage, R.raw.tavern)

    val MUSICNAME = arrayOf("dungeon", "battle", "goblin cave", "nice village", "spooky village", "tavern")

    // duration of music in seconds
    val MUSICDURATION = arrayOf(600, 600, 600, 600, 600, 600)

    var player: MediaPlayer = create1(musicService.applicationContext, MUSICPATH[0])
    var currentPosition = 0
    var musicIndex = 0
    private var musicStatus = 0 //0: before starts 1: playing 2: paused

    fun getMusicStatus(): Int {
        return musicStatus
    }

    fun getMusicName(): String {
        return MUSICNAME[musicIndex]
    }

    // gets the index for a certain song name
    private fun getSongIndex(name: String?) : Int{
        var index: Int = 0
        for (song in MUSICNAME){
            if (song.equals(name)){
                return index
            }
            index++
        }
        return -1
    }

    fun playMusic(name: String?) {
        var index = getSongIndex(name)
        player = create1(musicService.applicationContext, MUSICPATH[index])
        player.setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build())
        try {
            player.setOnCompletionListener(this)
            player.start()
            musicService.onUpdateMusicName(getMusicName())
        } catch (ex: IOException) {
            ex.printStackTrace()
        }

        musicStatus = 1
    }

    fun pauseMusic() {
        if (player.isPlaying()) {
            player.pause()
            currentPosition = player.getCurrentPosition()
            musicStatus = 2
        }
    }

    fun resumeMusic() {
        player.seekTo(currentPosition)
        player.start()
        musicStatus = 1
    }


    override fun onCompletion(mp: MediaPlayer?) {
        musicIndex = (musicIndex + 1) % 2
        player.release()
    }
}