package com.zmh.zz.zmh.viewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.zmh.zz.zmh.R;


/**
 * ViewPager that add items lazily in the two following situation:
 * <ul>
 * <li>its adapter inherits from {@link ViewPagerAdapter}</li>
 * <li>its adapter inherits from {@link FragmentPagerAdapter} and Fragment implements {@link FragmentPagerAdapter.Laziable} </li>
 * </ul>
 */
public class ViewPagerMain extends ViewPager {
    private boolean isPagingEnabled = true;

    private static final float DEFAULT_OFFSET = 0.5f;

    private PagerAdapter mLazyPagerAdapter;
    private float mInitLazyItemOffset = DEFAULT_OFFSET;

    public ViewPagerMain(Context context) {
        super(context);
    }

    public ViewPagerMain(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerMain);
        setInitLazyItemOffset(a.getFloat(R.styleable.ViewPagerMain_init_item_offset, DEFAULT_OFFSET));
        a.recycle();
    }

    //禁止ViewPager滑动
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return false;
    }
////////////////////////////////////////////////////////////////////

    /**
     * change the initLazyItemOffset
     *
     * @param initLazyItemOffset set mInitLazyItemOffset if {@code 0 < initLazyItemOffset <= 1}
     */
    public void setInitLazyItemOffset(float initLazyItemOffset) {
        if (initLazyItemOffset > 0 && initLazyItemOffset <= 1) {
            mInitLazyItemOffset = initLazyItemOffset;
        }
    }

    @Override
    public void setAdapter(android.support.v4.view.PagerAdapter adapter) {
        super.setAdapter(adapter);
        mLazyPagerAdapter = adapter != null && adapter instanceof PagerAdapter ? (PagerAdapter) adapter : null;
    }

    @Override
    protected void onPageScrolled(int position, float offset, int offsetPixels) {
        if (mLazyPagerAdapter != null) {
            if (getCurrentItem() == position) {
                int lazyPosition = position + 1;
                if (offset >= mInitLazyItemOffset && mLazyPagerAdapter.isLazyItem(lazyPosition)) {
                    mLazyPagerAdapter.startUpdate(this);
                    mLazyPagerAdapter.addLazyItem(this, lazyPosition);
                    mLazyPagerAdapter.finishUpdate(this);
                }
            } else if (getCurrentItem() > position) {
                int lazyPosition = position;
                if (1 - offset >= mInitLazyItemOffset && mLazyPagerAdapter.isLazyItem(lazyPosition)) {
                    mLazyPagerAdapter.startUpdate(this);
                    mLazyPagerAdapter.addLazyItem(this, lazyPosition);
                    mLazyPagerAdapter.finishUpdate(this);
                }
            }
        }
        super.onPageScrolled(position, offset, offsetPixels);
    }
}
