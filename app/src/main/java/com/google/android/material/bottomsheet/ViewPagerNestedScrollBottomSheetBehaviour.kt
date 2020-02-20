package com.google.android.material.bottomsheet

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.collection.LongSparseArray
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import java.lang.ref.WeakReference
import kotlin.reflect.full.superclasses


/**
 * Created by chandra-1765$ on 18/02/20$.
 * https://medium.com/@hanru.yeh/funny-solution-that-makes-bottomsheetdialog-support-viewpager-with-nestedscrollingchilds-bfdca72235c3
 * https://android.googlesource.com/platform/frameworks/support/+/2b53fdcd54f2760ed7957393f9e9f2140105c9ce/design/src/android/support/design/widget/BottomSheetBehavior.java#126
 * https://stackoverflow.com/questions/37715822/android-viewpager-with-recyclerview-works-incorrectly-inside-bottomsheet
 * https://github.com/laenger/ViewPagerBottomSheet/commit/277571585500b8c1ed4ed444a5bd250b981c47fc
 */

class ViewPagerNestedScrollBottomSheetBehaviour<V: View> : BottomSheetBehavior<V> {

    constructor() : super() {

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

    }

    override fun findScrollingChild(view: View): View? {
        if (ViewCompat.isNestedScrollingEnabled(view)) {
            return view
        }

        if (view is ViewPager2) {
            val currentView = getCurrentView(view)
            currentView?.let {
                val scrollingChild = findScrollingChild(currentView)
                if (scrollingChild != null) {
                    return scrollingChild
                }
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

    private fun getCurrentView(viewPager: ViewPager2): View? {
        val currentItem = viewPager.currentItem
        //return ((viewPager.getChildAt(0) as RecyclerView).layoutManager?.getChildAt(currentItem) as? ViewGroup)?.getChildAt(0) as? ViewGroup
        val fragment = (viewPager.adapter as? FragmentStateAdapter)?.getItem(currentItem)
        return fragment?.view
    }

    private fun FragmentStateAdapter.getItem(position: Int): Fragment? {
        return this::class.superclasses.find { it == FragmentStateAdapter::class }
            ?.java?.getDeclaredField("mFragments")
            ?.let { field ->
                field.isAccessible = true
                val mFragments = field.get(this) as LongSparseArray<Fragment>
                return@let mFragments[getItemId(position)]
            }
    }

    fun updateScrollingChild() {
        val scrollingChild = findScrollingChild(viewRef?.get()?:return)
        nestedScrollingChildRef = WeakReference(scrollingChild)
    }

    companion object {
        /**
         * A utility function to get the [ViewPagerNestedScrollBottomSheetBehaviour] associated with the `view`.
         *
         * @param view The [View] with [ViewPagerNestedScrollBottomSheetBehaviour].
         * @return The [ViewPagerNestedScrollBottomSheetBehaviour] associated with the `view`.
         */
        fun <V : View> from(view: V): ViewPagerNestedScrollBottomSheetBehaviour<V> {
            val params = view.layoutParams
            require(params is CoordinatorLayout.LayoutParams) { "The view is not a child of CoordinatorLayout" }
            val behavior =
                params.behavior
            require(behavior is ViewPagerNestedScrollBottomSheetBehaviour) { "The view is not associated with ViewPagerBottomSheetBehavior" }
            return behavior as ViewPagerNestedScrollBottomSheetBehaviour<V>
        }
    }
}