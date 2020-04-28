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
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class spell_search() : Fragment() {

    private val client = OkHttpClient()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_spell_search, container, false)

        val name: TextView? = view?.findViewById(R.id.nameText)
        val desc: TextView? = view?.findViewById(R.id.descriptionText)
        val level: TextView? = view?.findViewById(R.id.levelText)

        val query: EditText? = view?.findViewById(R.id.search)

        val searchButton: Button? = view?.findViewById(R.id.searchButton)

        var stringToSearch: String? = null

        var json: JSONObject? = null

        searchButton?.setOnClickListener{
            val request = Request.Builder()
                .url("https://api.open5e.com/spells/" + stringToSearch)
                .build()

            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                }
                override fun onResponse(call: Call, response: Response) {
                    json = JSONObject(response.body()?.string())
                }
            })
            try {
                name?.setText(json?.get("name")?.toString())

                if (json?.get("desc")?.toString()?.length!! > 200){
                    desc?.setText(json?.get("desc")?.toString()?.substring(0, 200) + "...")
                }
                else{
                    desc?.setText(json?.get("desc")?.toString())
                }
                level?.setText(json?.get("level")?.toString())
            }
            catch (e: Exception) {
                name?.setText("Spell Not Found")
                desc?.setText("")
                level?.setText("")
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
                    stringToSearch = stringToSearch?.toLowerCase()
                }
            }
        })

        return view
    }
}