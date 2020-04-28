package com.example.dndapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PlayerModel : ViewModel(){

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob+ Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)

    val liveDataCharacter = MutableLiveData<Character>()
    private val localCharacter = Character()

    private lateinit var viewModelJob: Job
    private lateinit var ioScope: CoroutineScope

    init {
        viewModelJob = Job()
        ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)
        liveDataCharacter.value = localCharacter
    }

    fun updateStat(stat: String, value: Int){
        when(stat.toLowerCase()) {
            "str" -> localCharacter.setStr(value)
            "dex" -> localCharacter.setDex(value)
            "con" -> localCharacter.setCon(value)
            "int" -> localCharacter.setInt(value)
            "wis" -> localCharacter.setWis(value)
            "cha" -> localCharacter.setCha(value)
        }

        // updates the livedata character
        liveDataCharacter.postValue(localCharacter)
    }

    fun updateName(name: String){
    }

    fun getCharacter(): MutableLiveData<Character>{
        return liveDataCharacter
    }
}