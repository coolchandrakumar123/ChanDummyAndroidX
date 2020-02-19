package com.google.android.material.bottomsheet

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.ViewPager2
import java.lang.ref.WeakReference


/**
 * Created by chandra-1765$ on 18/02/20$.
 */

class MultiScrollBottomSheetBehaviour<V: View> : BottomSheetBehavior<V> {

    constructor() : super() {

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

    }

    override fun findScrollingChild(view: View): View? {
        if (ViewCompat.isNestedScrollingEnabled(view)) {
            return view
        }

        if (view is ViewPager2) {
            val viewPager = view
            val currentViewPagerChild = viewPager.getChildAt(viewPager.currentItem)?:return null
            val scrollingChild = findScrollingChild(currentViewPagerChild)
            if (scrollingChild != null) {
                return scrollingChild
            }
        } else if (view is ViewGroup) {
            val group = view
            var i = 0
            val count = group.childCount
            while (i < count) {
                val scrollingChild = findScrollingChild(group.getChildAt(i))
                if (scrollingChild != null) {
                    return scrollingChild
                }
                i++
            }
        }
        return null
    }

    fun updateScrollingChild() {
        val scrollingChild = findScrollingChild(viewRef?.get()?:return)
        nestedScrollingChildRef = WeakReference(scrollingChild)
    }

    companion object {
        /**
         * A utility function to get the [MultiScrollBottomSheetBehaviour] associated with the `view`.
         *
         * @param view The [View] with [MultiScrollBottomSheetBehaviour].
         * @return The [MultiScrollBottomSheetBehaviour] associated with the `view`.
         */
        fun <V : View> from(view: V): MultiScrollBottomSheetBehaviour<V>? {
            val params = view.layoutParams
            require(params is CoordinatorLayout.LayoutParams) { "The view is not a child of CoordinatorLayout" }
            val behavior =
                params.behavior
            require(behavior is MultiScrollBottomSheetBehaviour) { "The view is not associated with ViewPagerBottomSheetBehavior" }
            return behavior as MultiScrollBottomSheetBehaviour<V>?
        }
    }
}