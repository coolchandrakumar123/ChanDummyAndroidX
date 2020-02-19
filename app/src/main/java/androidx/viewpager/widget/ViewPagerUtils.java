package androidx.viewpager.widget;

import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager2.widget.ViewPager2;

public class ViewPagerUtils {

    public static View getCurrentView(ViewPager2 viewPager) {
        final int currentItem = viewPager.getCurrentItem();
        return viewPager.getChildAt(0);
        /*for (int i = 0; i < viewPager.getChildCount(); i++) {
            final View child = viewPager.getChildAt(i);
            final ViewPager.LayoutParams layoutParams = (ViewPager.LayoutParams) child.getLayoutParams();
            if (!layoutParams.isDecor && currentItem == layoutParams.position) {
                return child;
            }
        }
        return null;*/
    }

}
