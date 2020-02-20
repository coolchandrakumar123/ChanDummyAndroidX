package com.aaba.chandummyandroidx.ui.bottommenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.aaba.chandummyandroidx.R
import kotlinx.android.synthetic.main.fragment_bottom_menu.*

class ViewPagerPageFragment : Fragment() {

    companion object {
        const val TITLE = "TITLE"
        fun getInstance(title: String) = ViewPagerPageFragment().apply {
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
        return inflater.inflate(R.layout.fragment_viewpager_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menu_viewpager.adapter = BottomMenuPageAdapter(childFragmentManager, lifecycle, count = 3)
        one_button.setCurrentItem(0)
        two_button.setCurrentItem(1)
        ViewPagerNestedScrollPageListener.setupViewPager(menu_viewpager)
    }

    private fun Button.setCurrentItem(position: Int) {
        setOnClickListener {
            menu_viewpager.setCurrentItem(position, true)
        }
    }
}