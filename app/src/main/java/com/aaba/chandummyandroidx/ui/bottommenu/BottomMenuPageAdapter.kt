package com.aaba.chandummyandroidx.ui.bottommenu

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Created by chandra-1765$ on 18/02/20$.
 */
class BottomMenuPageAdapter(fm: FragmentManager, lifeCycle: Lifecycle) : FragmentStateAdapter(fm,lifeCycle) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = BottomPageFragment.getInstance("$position Fragment")
}