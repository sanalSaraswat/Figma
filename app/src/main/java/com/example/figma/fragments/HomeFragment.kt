package com.example.figma.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.figma.R
import com.example.figma.activities.RecyclerViewActivity

class HomeFragment : Fragment() {

    private lateinit var button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        button = view.findViewById(R.id.button)

        button.setOnClickListener {
            Intent(context, RecyclerViewActivity::class.java).also {
                startActivity(it)
            }
        }

        return view

    }


}