package com.aaba.chandummyandroidx.ui.bottommenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.aaba.chandummyandroidx.R

class BottomMenuFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_bottom_menu, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        return root
    }
}