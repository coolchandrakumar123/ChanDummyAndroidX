package com.aaba.chandummyandroidx.ui.bottommenu

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Created by chandra-1765$ on 18/02/20$.
 */
class BottomMenuPageAdapter(fm: FragmentManager, lifeCycle: Lifecycle, val count: Int = 5, val isSubMenuEnable: Boolean = false) : FragmentStateAdapter(fm,lifeCycle) {

    override fun getItemCount(): Int = count

    override fun createFragment(position: Int): Fragment {
        return if(isSubMenuEnable && position == 1) {
            ViewPagerPageFragment.getInstance("Test")
        } else {
            BottomPageFragment.getInstance("$position Fragment")
        }
    }
}