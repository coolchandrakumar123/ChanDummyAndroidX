package com.aaba.chandummyandroidx.ui.bottommenu

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.aaba.chandummyandroidx.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.ViewPagerNestedScrollBottomSheetBehaviour
import kotlinx.android.synthetic.main.fragment_bottom_menu.*

class BottomMenuFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_menu, container, false)
    }

    private var customPeekHeight = 0
    private var enableHideOnSwipeDown = true
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        one_button.setCurrentItem(1)
        two_button.setCurrentItem(2)
        three_button.setCurrentItem(3)
        //menu_viewpager.isUserInputEnabled = false
        //(menu_viewpager.getChildAt(0) as RecyclerView).isNestedScrollingEnabled = false

        modalContainer.setOnClickListener {
            ViewPagerNestedScrollBottomSheetBehaviour.from(container).apply {
                state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
        ViewPagerNestedScrollBottomSheetBehaviour.from(container).apply {
            peekHeight = customPeekHeight
            isHideable = enableHideOnSwipeDown
            addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    onSlide(slideOffset)
                }

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    onStateChanged(newState)
                }
            })
        }
        //setContainerView()
        bottomSheetOpenAnimation(view)
        menu_viewpager.adapter = BottomMenuPageAdapter(childFragmentManager, lifecycle, isSubMenuEnable = true)
        menu_viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                ViewPagerNestedScrollBottomSheetBehaviour.from(container).updateScrollingChild()
            }
        })
    }

    private fun Button.setCurrentItem(position: Int) {
        setOnClickListener {
            menu_viewpager.setCurrentItem(position, true)
        }
    }

    var openState = BottomSheetBehavior.STATE_EXPANDED
    private fun bottomSheetOpenAnimation(view: View) {
        view.animate()
            .setDuration(10)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    container?.let {
                        ViewPagerNestedScrollBottomSheetBehaviour.from(it).apply {
                            state = openState
                        }
                    }
                    /*menu_viewpager.post {
                        CustomBottomSheetBehavior.from(container).updateScrollingChild()
                    }*/
                }
            }).start()
    }

    private fun onSlide(slideOffset: Float) {
        modalContainer?.alpha = slideOffset
    }

    private fun onStateChanged(newState: Int) {
        if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
            requireActivity().onBackPressed()
            //onCollapsed()
        } else if (newState == BottomSheetBehavior.STATE_EXPANDED) {
            modalContainer?.alpha = 1.0f
        }
    }
}