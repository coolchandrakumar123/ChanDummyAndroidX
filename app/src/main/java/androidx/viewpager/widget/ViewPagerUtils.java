package androidx.viewpager.widget;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

public class ViewPagerUtils {

    public static View getCurrentView(ViewPager2 viewPager) {
        final int currentItem = viewPager.getCurrentItem();
        ViewGroup childView = (ViewGroup) ((RecyclerView.LayoutManager)((RecyclerView)viewPager.getChildAt(0)).getLayoutManager()).getChildAt(currentItem);
        //return viewPager.getChildAt(0);
        for (int i = 0; i < childView.getChildCount(); i++) {
            final View child = childView.getChildAt(i);
            if(child instanceof RecyclerView) {
                return child;
            }
            /*final FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) child.getLayoutParams();
            if (!layoutParams.isDecor && currentItem == layoutParams.position) {
                return child;
            }*/
        }
        return null;
    }

}
