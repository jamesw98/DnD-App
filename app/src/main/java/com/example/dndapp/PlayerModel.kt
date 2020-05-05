package com.example.dndapp

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList
import kotlin.coroutines.CoroutineContext

class PlayerModel : ViewModel(){

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob+ Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)

    val liveDataAdapter = MutableLiveData<ArrayList<Character>>()
    private var dataAdapter = ArrayList<Character>()

    val liveDataCharacter = MutableLiveData<Character>()
    private val localCharacter = Character()

    private lateinit var viewModelJob: Job
    private lateinit var ioScope: CoroutineScope

    val database = FirebaseDatabase.getInstance()

    init {
        viewModelJob = Job()
        ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)
        liveDataCharacter.value = localCharacter
        liveDataAdapter.value = dataAdapter
    }

    fun getCharacter(): Character{
        return localCharacter
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

    fun callAPICharacters(){
        val characterRef = database.getReference("characters")
        val ref = database.getReference("characters").child("REFRESHERCHAR")
        ref.setValue(UUID.randomUUID().toString())

        val listener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                // intentionally blank
            }

            override fun onDataChange(data: DataSnapshot) {
                dataAdapter.clear()

                for (i in data.children){

                    if (!i.key.equals("REFRESHERCHAR")){
                        var tempHash = i.value as HashMap<*, *>

                        tempHash = tempHash as HashMap<String, *>
                        var tempStr: Long = tempHash.getValue("strength") as Long
                        var tempDex: Long = tempHash.getValue("dexterity") as Long
                        var tempCon: Long = tempHash.getValue("constitution") as Long
                        var tempInt: Long = tempHash.getValue("intelligence") as Long
                        var tempWis: Long = tempHash.getValue("wisdom") as Long
                        var tempCha: Long = tempHash.getValue("charisma") as Long
                        var ac: Long = tempHash.getValue("ac") as Long
                        var level: Long = tempHash.getValue("level") as Long
                        var currHP: Long = tempHash.getValue("currentHP") as Long
                        var maxHP: Long = tempHash.getValue("maxHP") as Long

                        var name: String = tempHash.getValue("name") as String

                        var tempChar = Character()
                        tempChar.setStr(Integer.valueOf(tempStr.toString()))
                        tempChar.setDex(Integer.valueOf(tempDex.toString()))
                        tempChar.setCon(Integer.valueOf(tempCon.toString()))
                        tempChar.setInt(Integer.valueOf(tempInt.toString()))
                        tempChar.setWis(Integer.valueOf(tempWis.toString()))
                        tempChar.setCha(Integer.valueOf(tempCha.toString()))
                        tempChar.name = name
                        tempChar.ac = Integer.valueOf(ac.toString())
                        tempChar.level = Integer.valueOf(level.toString())
                        tempChar.currentHP = Integer.valueOf(currHP.toString())
                        tempChar.maxHP = Integer.valueOf(maxHP.toString())

                        dataAdapter.add(tempChar)
                    }
                }
            }
        }
        characterRef.addValueEventListener(listener)
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
}