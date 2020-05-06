package com.example.dndapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import okhttp3.OkHttpClient

class description() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_description, container, false)

        var desc: TextView? = view?.findViewById(R.id.description)

        desc?.text = this.arguments?.get("text").toString()

        return view
    }
}

