package com.example.dndapp

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController

class stats : Fragment(){

    var strength: Int = 0
    var dexterity: Int = 0
    var constitution: Int = 0
    var intelligence: Int = 0
    var wisdom: Int = 0
    var charisma: Int = 0

    var ac: Int = 0
    var maxHP: Int = 0
    var currentHP: Int = 0
    var name: String = ""
    var level: Int = 0

    var created: Boolean = false

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val model = activity?.run{ ViewModelProviders.of(this).get(PlayerModel::class.java)}?: throw Exception("Invalid Activity")

        model?.liveDataCharacter?.observe(this, androidx.lifecycle.Observer { character ->
            if (created){
                strength = character.strength
                dexterity = character.dexterity
                constitution = character.constitution
                intelligence = character.intelligence
                wisdom = character.wisdom
                charisma = character.charisma

                ac = character.ac
                maxHP = character.maxHP
                currentHP = character.currentHP
                name = character.name
                level = character.level
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stats, container, false)

        val model = activity?.run{ ViewModelProviders.of(this).get(PlayerModel::class.java)}?: throw Exception("Invalid Activity")

        val strText: EditText? = view?.findViewById(R.id.strScore)
        val dexText: EditText? = view?.findViewById(R.id.dexScore)
        val conText: EditText? = view?.findViewById(R.id.conScore)
        val intText: EditText? = view?.findViewById(R.id.intScore)
        val wisText: EditText? = view?.findViewById(R.id.wisScore)
        val chaText: EditText? = view?.findViewById(R.id.chaScore)

        val nameText: EditText? = view?.findViewById(R.id.charName)
        val levelText: EditText? = view?.findViewById(R.id.levelText)
        val acText: EditText? = view?.findViewById(R.id.acTEXT)
        val maxHPText: EditText? = view?.findViewById(R.id.maxHPText)
        val hpText: EditText? = view?.findViewById(R.id.currHPText)

        val strMod: TextView? = view?.findViewById(R.id.strModText)
        val dexMod: TextView? = view?.findViewById(R.id.dexModText3)
        val conMod: TextView? = view?.findViewById(R.id.conModText2)
        val intMod: TextView? = view?.findViewById(R.id.intModText2)
        val wisMod: TextView? = view?.findViewById(R.id.wisModText)
        val chaMod: TextView? = view?.findViewById(R.id.chaModText2)

        val saveButton: Button? = view?.findViewById(R.id.savePlayer)

        saveButton?.setOnClickListener{
            model.saveCharacterToFireBase()
            Toast.makeText(activity?.applicationContext, "Character Saved", Toast.LENGTH_SHORT).show()
        }

        if (this.arguments?.get("name") != null){
            model.updateStat("str", Integer.valueOf(this.arguments?.get("str").toString()))
            model.updateStat("dex", Integer.valueOf(this.arguments?.get("dex").toString()))
            model.updateStat("con", Integer.valueOf(this.arguments?.get("con").toString()))
            model.updateStat("int", Integer.valueOf(this.arguments?.get("int").toString()))
            model.updateStat("wis", Integer.valueOf(this.arguments?.get("wis").toString()))
            model.updateStat("cha", Integer.valueOf(this.arguments?.get("cha").toString()))
            model.updatePlayerAC(Integer.valueOf(this.arguments?.get("ac").toString()))
            model.updatePlayerLevel(Integer.valueOf(this.arguments?.get("level").toString()))
            model.updatePlayerName(this.arguments?.get("name").toString())
            model.updatePlayerHP(Integer.valueOf(this.arguments?.get("hp").toString()))
            model.updatePlayerMaxHP(Integer.valueOf(this.arguments?.get("maxHP").toString()))
        }

        strText?.setText(model.getCharacter().strength.toString())
        dexText?.setText(model.getCharacter().dexterity.toString())
        conText?.setText(model.getCharacter().constitution.toString())
        intText?.setText(model.getCharacter().intelligence.toString())
        wisText?.setText(model.getCharacter().wisdom.toString())
        chaText?.setText(model.getCharacter().charisma.toString())
        acText?.setText(model.getCharacter().ac.toString())
        levelText?.setText(model.getCharacter().level.toString())
        nameText?.setText(model.getCharacter().name)
        hpText?.setText(model.getCharacter().currentHP.toString())
        maxHPText?.setText(model.getCharacter().maxHP.toString())

        (view.findViewById(R.id.gotoSkills) as Button).setOnClickListener {
            it.findNavController().navigate(R.id.action_stats_to_skills)
        }

        acText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (!s.isBlank() || !s.isEmpty()) {
                    model.updatePlayerAC(Integer.valueOf(s.toString()))
                }
            }
        })

        nameText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (!s.isBlank() || !s.isEmpty()) {
                    model.updatePlayerName(s.toString())
                }
            }
        })

        levelText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (!s.isBlank() || !s.isEmpty()) {
                    model.updatePlayerLevel(Integer.valueOf(s.toString()))
                }
            }
        })

        maxHPText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (!s.isBlank() || !s.isEmpty()) {
                    model.updatePlayerMaxHP(Integer.valueOf(s.toString()))
                }
            }
        })

        hpText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (!s.isBlank() || !s.isEmpty()) {
                    model.updatePlayerHP(Integer.valueOf(s.toString()))
                }
            }
        })

        strText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (!s.isBlank() || !s.isEmpty()) {
                    model?.updateStat("str", Integer.valueOf(s.toString()))

                    var newMod: Int = ((Integer.valueOf(s.toString())) / 2) - 5

                    if (newMod > 0){
                        strMod?.setText("+" + newMod.toString())
                    }
                    else {
                        strMod?.setText(newMod.toString())
                    }
                }
            }
        })

        dexText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                if (!s.isBlank() || !s.isEmpty()) {
                    model?.updateStat("dex", Integer.valueOf(s.toString()))
                    var newMod: Int = ((Integer.valueOf(s.toString())) / 2) - 5
                    if (newMod > 0){
                        dexMod?.setText("+" + newMod.toString())
                    }
                    else {
                        dexMod?.setText(newMod.toString())
                    }
                }
            }
        })

        conText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                if (!s.isBlank() || !s.isEmpty()) {
                    model?.updateStat("con", Integer.valueOf(s.toString()))
                    var newMod: Int = ((Integer.valueOf(s.toString())) / 2) - 5
                    if (newMod > 0){
                        conMod?.setText("+" + newMod.toString())
                    }
                    else {
                        conMod?.setText(newMod.toString())
                    }
                }
            }
        })

        intText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (!s.isBlank() || !s.isEmpty()) {
                    model?.updateStat("int", Integer.valueOf(s.toString()))
                    var newMod: Int = ((Integer.valueOf(s.toString())) / 2) - 5
                    if (newMod > 0){
                        intMod?.setText("+" + newMod.toString())
                    }
                    else {
                        intMod?.setText(newMod.toString())
                    }
                }
            }
        })

        wisText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (!s.isBlank() || !s.isEmpty()) {
                    model?.updateStat("wis", Integer.valueOf(s.toString()))
                    var newMod: Int = ((Integer.valueOf(s.toString())) / 2) - 5
                    if (newMod > 0){
                        wisMod?.setText("+" + newMod.toString())
                    }
                    else {
                        wisMod?.setText(newMod.toString())
                    }
                }
            }
        })

        chaText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (!s.isBlank() || !s.isEmpty()) {
                    model?.updateStat("cha", Integer.valueOf(s.toString()))
                    var newMod: Int = ((Integer.valueOf(s.toString())) / 2) - 5
                    if (newMod > 0){
                        chaMod?.setText("+" + newMod.toString())
                    }
                    else {
                        chaMod?.setText(newMod.toString())
                    }
                }
            }
        })

        created = true

        return view
    }
}


