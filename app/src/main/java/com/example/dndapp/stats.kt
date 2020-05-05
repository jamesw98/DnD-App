package com.example.dndapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController

class stats : Fragment(){
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
        }

        if(this.arguments?.get("name") != null){
            strText?.setText(this.arguments?.get("str").toString())
            dexText?.setText(this.arguments?.get("dex").toString())
            conText?.setText(this.arguments?.get("con").toString())
            intText?.setText(this.arguments?.get("int").toString())
            wisText?.setText(this.arguments?.get("wis").toString())
            chaText?.setText(this.arguments?.get("cha").toString())

            acText?.setText(this.arguments?.get("ac").toString())
            levelText?.setText(this.arguments?.get("level").toString())
            nameText?.setText(this.arguments?.get("name").toString())

            hpText?.setText(this.arguments?.get("hp").toString())
            maxHPText?.setText(this.arguments?.get("maxHP").toString())
        }

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

        return view
    }
}


