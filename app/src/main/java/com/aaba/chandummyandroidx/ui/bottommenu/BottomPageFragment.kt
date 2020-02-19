package com.aaba.chandummyandroidx.ui.bottommenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aaba.chandummyandroidx.R
import kotlinx.android.synthetic.main.fragment_bottom_page.*

class BottomPageFragment : Fragment() {

    companion object {
        const val TITLE = "TITLE"
        fun getInstance(title: String) = BottomPageFragment().apply {
            arguments = Bundle().also {
                it.putString(TITLE, title)
            }
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_page, container, false)
    }

    private lateinit var bottomRecyclerAdapter: BottomRecyclerAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title.text =  arguments?.getString(TITLE)?:"Default"
        bottomRecyclerAdapter = BottomRecyclerAdapter()
        recycler_view.apply {
            layoutManager = LinearLayoutManager(requireContext()).apply {
                isSmoothScrollbarEnabled = false
            }
            isNestedScrollingEnabled = false
            adapter = bottomRecyclerAdapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val data: ArrayList<String> = arrayListOf()
        for(i in 0..50) {
            data.add("$i Value")
        }
        bottomRecyclerAdapter.setList(data)
    }
}