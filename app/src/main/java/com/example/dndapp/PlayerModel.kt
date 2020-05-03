package com.example.dndapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
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

    val database = FirebaseDatabase.getInstance()

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

    fun saveCharacterToFireBase(){
        val charRef = database.getReference("characters").child(localCharacter.name)
        charRef.setValue(localCharacter)
    }

    fun updatePlayerName(name: String){
        localCharacter.name = name
        liveDataCharacter.postValue(localCharacter)
    }

    fun updatePlayerAC(ac: Int){
        localCharacter.ac = ac
        liveDataCharacter.postValue(localCharacter)
    }

    fun updatePlayerMaxHP(max: Int){
        localCharacter.maxHP = max
        liveDataCharacter.postValue(localCharacter)
    }

    fun updatePlayerHP(hp: Int){
        localCharacter.currentHP = hp
        liveDataCharacter.postValue(localCharacter)
    }

    fun updatePlayerLevel(level: Int){
        localCharacter.level = level
        liveDataCharacter.postValue(localCharacter)
    }

    fun callAPI(query: String?, queryType: Char): JSONObject?{
        val client = OkHttpClient()

        var baseURL: String
        var json: JSONObject? = null

        if (queryType == 's'){
            baseURL = "https://api.open5e.com/spells/"
        }
        else {
            baseURL = "https://api.open5e.com/magicitems/"
        }

        val request = Request.Builder()
            .url(baseURL + query)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("FAILED", "API CALL FAILED")
            }
            override fun onResponse(call: Call, response: Response) {
                json = JSONObject(response.body()?.string())
            }
        })

        while(json == null){
            Thread.sleep(10)
        }
        return json
    }

    fun getCharacter(): MutableLiveData<Character>{
        return liveDataCharacter
    }
}