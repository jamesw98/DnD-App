package com.example.dndapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.Observer
import androidx.lifecycle.ViewModelProviders

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"




class skills : Fragment() {

    var newCharacter = Character()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_skills, container, false)
        val model = activity?.let { ViewModelProviders.of(it).get(PlayerModel::class.java) }


        //Not sure if all these initializations are needed but kids are starving in Africa so these stay for now
        //Strength Skill
        val athletics: TextView? = view?.findViewById(R.id.athletics)

        //Dexterity Skills
        val acrobatics: TextView? = view?.findViewById(R.id.acrobatics)
        val sleightOfHand: TextView? = view?.findViewById(R.id.sleightOfHand)
        val stealth: TextView? = view?.findViewById(R.id.stealth)


        //Intelligence Skills
        val arcana: TextView? = view?.findViewById(R.id.arcana)
        val history: TextView? = view?.findViewById(R.id.history)
        val investigation: TextView? = view?.findViewById(R.id.investigation)
        val nature: TextView? = view?.findViewById(R.id.nature)
        val religion: TextView? = view?.findViewById(R.id.religion)


        //Wisdom Skills
        val animalHandling: TextView? = view?.findViewById(R.id.animalHandling)
        val insight: TextView? = view?.findViewById(R.id.insight)
        val medicine: TextView? = view?.findViewById(R.id.medicine)
        val perception: TextView? = view?.findViewById(R.id.perception)
        val survival: TextView? = view?.findViewById(R.id.survival)

        //Charisma Skills
        val deception: TextView? = view?.findViewById(R.id.deception)
        val intimidation: TextView? = view?.findViewById(R.id.intimidation)
        val performance: TextView? = view?.findViewById(R.id.performance)
        val persuasion: TextView? = view?.findViewById(R.id.persuasion)


        model?.getCharacter()?.observe(viewLifecycleOwner, androidx.lifecycle.Observer { character ->
            v?.findViewById<TextView>(R.id.athletics)?.text = ("Athletics:" + character.athle.toString())
            v?.findViewById<TextView>(R.id.acrobatics)?.text = ("Acrobatics:" + character.acro.toString())
            v?.findViewById<TextView>(R.id.sleightOfHand)?.text = ("Sleight of Hand:" + character.sleight.toString())
            v?.findViewById<TextView>(R.id.stealth)?.text = ("Stealth:" + character.stealth.toString())
            v?.findViewById<TextView>(R.id.arcana)?.text = ("Arcana:" + character.arcana.toString())
            v?.findViewById<TextView>(R.id.history)?.text = ("History:" + character.hist.toString())
            v?.findViewById<TextView>(R.id.investigation)?.text = ("Investigation:" + character.invest.toString())
            v?.findViewById<TextView>(R.id.nature)?.text = ("Nature:" + character.nature.toString())
            v?.findViewById<TextView>(R.id.religion)?.text = ("Religion:" + character.religion.toString())
            v?.findViewById<TextView>(R.id.animalHandling)?.text = ("Animal Handling:" + character.animal.toString())
            v?.findViewById<TextView>(R.id.insight)?.text = ("Insight:" + character.insi.toString())
            v?.findViewById<TextView>(R.id.medicine)?.text = ("Medicine:" + character.medic.toString())
            v?.findViewById<TextView>(R.id.perception)?.text = ("Perception:" + character.percep.toString())
            v?.findViewById<TextView>(R.id.survival)?.text = ("Survival:" + character.survi.toString())
            v?.findViewById<TextView>(R.id.deception)?.text = ("Deception:" + character.decep.toString())
            v?.findViewById<TextView>(R.id.intimidation)?.text = ("Intimidation:" + character.intim.toString())
            v?.findViewById<TextView>(R.id.performance)?.text = ("Performance:" + character.perform.toString())
            v?.findViewById<TextView>(R.id.persuasion)?.text = ("Persuasion:" + character.presau.toString())
        })
        return v

    }

}


