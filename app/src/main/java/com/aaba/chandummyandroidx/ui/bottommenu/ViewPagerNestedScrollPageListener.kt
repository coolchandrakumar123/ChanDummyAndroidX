package com.aaba.chandummyandroidx.ui.bottommenu

import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.ViewPagerNestedScrollBottomSheetBehaviour


/**
 * Created by chandra-1765$ on 20/02/20$.
 */

class ViewPagerNestedScrollPageListener constructor(
    private val viewPager: ViewPager2,
    bottomSheetParent: View
) : ViewPager2.OnPageChangeCallback() {

    companion object {

        fun setupViewPager(viewPager: ViewPager2) {
            findBottomSheetParent(viewPager)?.let { bottomSheetParent ->
                viewPager.registerOnPageChangeCallback(ViewPagerNestedScrollPageListener(viewPager, bottomSheetParent))
            }
        }

        private fun findBottomSheetParent(view: View?): View? {
            var current = view
            while (current != null) {
                val params = current.layoutParams
                if (params is CoordinatorLayout.LayoutParams && params.behavior is ViewPagerNestedScrollBottomSheetBehaviour) {
                    return current
                }
                val parent = current.parent
                //current.parent as? View
                current =  if (parent == null || parent !is View) null else parent
            }
            return null
        }
    }

    private val behavior: ViewPagerNestedScrollBottomSheetBehaviour<View> = ViewPagerNestedScrollBottomSheetBehaviour.from(bottomSheetParent)
    override fun onPageSelected(position: Int) {
        viewPager.post { behavior.updateScrollingChild() }
    }

}