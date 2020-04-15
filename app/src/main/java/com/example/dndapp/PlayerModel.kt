package com.example.dndapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class PlayerModel : ViewModel(){

    private val liveDataCharacter = MutableLiveData<Character>()
    private val localCharacter = Character()

    private var viewModelJob: Job
    private var ioScope: CoroutineScope

    init {
        viewModelJob = Job()
        ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)
        liveDataCharacter.value = localCharacter
    }

    fun updateStat(stat: String, value: Int){
        when(stat) {
            "str" -> localCharacter.setStr(value)
            "dex" -> localCharacter.setDex(value)
            "con" -> localCharacter.setCon(value)
            "int" -> localCharacter.setInt(value)
            "wis" -> localCharacter.setWis(value)
            "cha" -> localCharacter.setCha(value)
        }
        //TODO update skills
    }
}