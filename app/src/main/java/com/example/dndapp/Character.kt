package com.example.dndapp

class Character() {

    var name: String = ""
    var items: String = ""

    var ac: Int = 0
    var maxHP: Int = 0
    var currentHP: Int = 0

    var strength: Int = 0
    var dexterity: Int = 0
    var constitution: Int = 0
    var intelligence: Int = 0
    var wisdom: Int = 0
    var charisma: Int = 0

    var acro: Int = 0
    var animal: Int = 0
    var arcana: Int = 0
    var athle: Int = 0
    var decep: Int = 0
    var endur: Int = 0
    var hist: Int = 0
    var insi: Int = 0
    var intim: Int = 0
    var invest: Int = 0
    var medic: Int = 0
    var nature: Int = 0
    var percep: Int = 0
    var perform: Int = 0
    var presau: Int = 0
    var religion: Int = 0
    var sleight: Int = 0
    var stealth: Int = 0
    var survi: Int = 0

    var proficiency = emptyArray<Int>()
    var expertise = emptyArray<Int>()

    fun setStr(value: Int){
        strength = value
    }

    fun setDex(value: Int){
        dexterity = value
    }

    fun setCon(value: Int){
        constitution = value
    }

    fun setInt(value: Int){
        intelligence = value
    }

    fun setWis(value: Int){
        intelligence = value
    }
}


