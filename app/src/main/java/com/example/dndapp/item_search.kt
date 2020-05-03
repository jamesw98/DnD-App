package com.example.dndapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class item_search() : Fragment() {

    private val client = OkHttpClient()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_search, container, false)

        val model = activity?.run{ ViewModelProviders.of(this).get(PlayerModel::class.java)}?: throw Exception("Invalid Activity")

        val name: TextView? = view?.findViewById(R.id.nameText)
        val desc: TextView? = view?.findViewById(R.id.descriptionText)
        val rarity: TextView? = view?.findViewById(R.id.rarityText)

        val query: EditText? = view?.findViewById(R.id.search)

        val searchButton: Button? = view?.findViewById(R.id.searchButton)

        var stringToSearch: String? = null

        var json: JSONObject? = null

        searchButton?.setOnClickListener{
            json = model.callAPI(stringToSearch, 's')

            try {
                name?.setText(json?.get("name")?.toString())

                if (json?.get("desc")?.toString()?.length!! > 200){
                    desc?.setText(json?.get("desc")?.toString()?.substring(0, 200) + "...")
                }
                else{
                    desc?.setText(json?.get("desc")?.toString())
                }
                rarity?.setText(json?.get("rarity")?.toString())
            }
            catch (e: Exception) {
                Log.d(">Exception", e.message.toString())
                name?.setText("Item Not Found")
                desc?.setText("")
                rarity?.setText("")
            }
        }

        query?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (!s.isBlank() || !s.isEmpty()) {
                    stringToSearch = s.toString()
                    stringToSearch = stringToSearch!!.replace(" ", "-")
                }
            }
        })

        return view
    }
}