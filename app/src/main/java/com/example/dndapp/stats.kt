package com.example.dndapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment

class stats : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stats, container, false)

        val strText: EditText? = view?.findViewById(R.id.strScore)
        val dexText: EditText? = view?.findViewById(R.id.dexScore)
        val conText: EditText? = view?.findViewById(R.id.conScore)
        val intText: EditText? = view?.findViewById(R.id.intScore)
        val wisText: EditText? = view?.findViewById(R.id.wisScore)
        val chaText: EditText? = view?.findViewById(R.id.chaScore)

        val strMod: TextView? = view?.findViewById(R.id.strModText)
        val dexMod: TextView? = view?.findViewById(R.id.dexModText3)
        val conMod: TextView? = view?.findViewById(R.id.conModText2)
        val intMod: TextView? = view?.findViewById(R.id.intModText2)
        val wisMod: TextView? = view?.findViewById(R.id.wisModText)
        val chaMod: TextView? = view?.findViewById(R.id.chaModText2)

        strText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (!s.isBlank() || !s.isEmpty()) {
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

