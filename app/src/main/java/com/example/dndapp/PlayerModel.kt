package com.example.dndapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlayerModel : ViewModel(){

     private val liveDataCharacter = MutableLiveData<Character>()
}