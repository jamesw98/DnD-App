package com.example.dndapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class load_character : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_load_character, container, false)

        val model = activity?.run { ViewModelProviders.of(this).get(PlayerModel::class.java) }
            ?: throw Exception("Invalid Activity")

        val recyclerView: RecyclerView? = view?.findViewById(R.id.charaterRV)
        recyclerView?.layoutManager = LinearLayoutManager(activity?.applicationContext)

        var adapter = CharacterListAdapter()

        model.callAPICharacters()

        model?.liveDataAdapter.observe(viewLifecycleOwner, androidx.lifecycle.Observer {characters ->
            for (i in characters){
                adapter.addCharacter(i)
            }
        })

        recyclerView?.adapter = adapter

        return view
    }

    inner class CharacterListAdapter():
        RecyclerView.Adapter<CharacterListAdapter.CharacterHolder>(){
        private var characters = ArrayList<Character>()

        internal fun getCharacters(characters: ArrayList<Character>) {
            this.characters = characters
            notifyDataSetChanged()
        }

        fun clear(){
            characters.clear()
            notifyDataSetChanged()
        }

        fun addCharacter(newChar: Character){
            this.characters.add(newChar)
            notifyDataSetChanged()
        }

        override fun getItemCount(): Int {
            return characters.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_view, parent, false)
            return CharacterHolder(v)
        }

        override fun onBindViewHolder(holder: CharacterHolder, position: Int) {

            holder.view.findViewById<TextView>(R.id.name).text = "Name: " + characters.get(position).name
            holder.view.findViewById<TextView>(R.id.level).text = "Level: " + characters.get(position).level
            holder.view.findViewById<TextView>(R.id.ac).text = "Armor Class: " + characters.get(position).ac.toString()

            holder.itemView.setOnClickListener(){
                var currentChar = characters.get(position)
                var bundle = Bundle()

                view?.findNavController()?.navigate(R.id.action_load_character_to_stats, bundleOf(
                    "str" to currentChar.strength,
                    "dex" to currentChar.dexterity,
                    "con" to currentChar.constitution,
                    "int" to currentChar.intelligence,
                    "wis" to currentChar.wisdom,
                    "cha" to currentChar.charisma,
                    "ac" to currentChar.ac,
                    "level" to currentChar.level,
                    "name" to currentChar.name,
                    "maxHP" to currentChar.maxHP,
                    "hp" to currentChar.currentHP
                ))
            }
        }

        inner class CharacterHolder(val view: View): RecyclerView.ViewHolder(view), View.OnClickListener{
            override fun onClick(view: View?){
                if (view != null) {
                }
            }
        }
    }
}
