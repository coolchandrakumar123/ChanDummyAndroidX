package com.aaba.chandummyandroidx.ui.bottommenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aaba.chandummyandroidx.R
import kotlinx.android.synthetic.main.fragment_nested_scroll_page.*

class NestedScrollPageFragment : Fragment() {

    companion object {
        const val TITLE = "TITLE"
        fun getInstance(title: String) = NestedScrollPageFragment().apply {
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
        return inflater.inflate(R.layout.fragment_nested_scroll_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*nested_scroll_view.setOnTouchListener { v, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    v.parent.requestDisallowInterceptTouchEvent(nested_scroll_view.scrollY > 10)
                }
                MotionEvent.ACTION_UP -> {
                    v.parent.requestDisallowInterceptTouchEvent(false)
                }
            }
            v.onTouchEvent(event)
            true
        }*/
    }
}