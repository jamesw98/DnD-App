package com.example.dndapp

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.dndapp.R

class homescreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_homescreen, container, false)

        val diceButton: Button? = view?.findViewById(R.id.dice)
        val statsButton: Button? = view?.findViewById(R.id.stats)
        val spellButton: Button? = view?.findViewById(R.id.spell)
        val itemButton: Button? = view?.findViewById(R.id.item)


        // go to dice roller
        diceButton?.setOnClickListener() {
            view?.findNavController()?.navigate(R.id.action_homescreen_to_dice_roller)
        }

        // go to character stats
        statsButton?.setOnClickListener() {
            view?.findNavController()?.navigate(R.id.action_homescreen_to_stats)
        }

        itemButton?.setOnClickListener() {
            view?.findNavController()?.navigate(R.id.action_homescreen_to_item_search)
        }
        return view
    }
}

