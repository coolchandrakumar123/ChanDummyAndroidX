package com.google.android.material.bottomsheet

import android.content.Context
import android.util.AttributeSet
import android.util.Log
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
            Log.d("ChanLog", "scrollingEnabled: ${view}");
            return view
        }

        if (view is ViewPager2) {
            Log.d("ChanLog", "currentItem: ${view.currentItem}, item: ${view.getChildAt(view.currentItem)}");
            val currentViewPagerChild = view.getChildAt(view.currentItem)?:return null
            val scrollingChild = findScrollingChild(currentViewPagerChild)
            if (scrollingChild != null) {
                return scrollingChild
            }
        } else if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val scrollingChild = findScrollingChild(view.getChildAt(i))
                if (scrollingChild != null) {
                    return scrollingChild
                }
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