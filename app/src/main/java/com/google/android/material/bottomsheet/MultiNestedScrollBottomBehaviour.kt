package com.google.android.material.bottomsheet

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import java.lang.ref.WeakReference

/**
 * Created by chandra-1765$ on 19/02/20$.
 */
class MultiNestedScrollBottomBehaviour<V: View> : CustomBottomSheetBehavior<V> {

    constructor() : super() {

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

    }

    override fun findScrollingChild(view: View): View? {
        if (ViewCompat.isNestedScrollingEnabled(view)) {
            return view
        }

        if (view is ViewPager2) {
            val currentViewPagerChild = getScrollableView(view)?:return null
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

    private fun getScrollableView(viewPager: ViewPager2): View? {
        val currentItem: Int = viewPager.currentItem
        val childView = ((viewPager.getChildAt(0) as RecyclerView).layoutManager?.getChildAt(currentItem) as? ViewGroup)?.getChildAt(0) as? ViewGroup ?: return null
        for (i in 0 until childView.childCount) {
            val child = childView.getChildAt(i)
            if (child is RecyclerView) {
                return child
            }
            /*final FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) child.getLayoutParams();
            if (!layoutParams.isDecor && currentItem == layoutParams.position) {
                return child;
            }*/
        }
        return null
    }

    fun updateScrollingChild() {
        val scrollingChild = findScrollingChild(viewRef?.get()?:return)
        nestedScrollingChildRef = WeakReference(scrollingChild)
    }

    companion object {
        /**
         * A utility function to get the [MultiNestedScrollBottomBehaviour] associated with the `view`.
         *
         * @param view The [View] with [MultiNestedScrollBottomBehaviour].
         * @return The [MultiNestedScrollBottomBehaviour] associated with the `view`.
         */
        fun <V : View> from(view: V): MultiNestedScrollBottomBehaviour<V> {
            val params = view.layoutParams
            require(params is CoordinatorLayout.LayoutParams) { "The view is not a child of CoordinatorLayout" }
            val behavior =
                params.behavior
            require(behavior is MultiNestedScrollBottomBehaviour) { "The view is not associated with ViewPagerBottomSheetBehavior" }
            return behavior as MultiNestedScrollBottomBehaviour<V>
        }
    }
}