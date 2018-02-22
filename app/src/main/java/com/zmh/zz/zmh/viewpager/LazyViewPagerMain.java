package com.zmh.zz.zmh.viewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.zmh.zz.zmh.R;

public class LazyViewPagerMain extends ViewPager {
    private static final float DEFAULT_OFFSET = 0.5f;
    private LazyPagerAdapter mLazyPagerAdapter;
    private float mInitLazyItemOffset = DEFAULT_OFFSET;

    public LazyViewPagerMain(Context context) {
        super(context);
    }

    public LazyViewPagerMain(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LazyViewPager);
        setInitLazyItemOffset(a.getFloat(R.styleable.LazyViewPager_init_lazy_item_offset, DEFAULT_OFFSET));
        a.recycle();
    }

    public void setInitLazyItemOffset(float initLazyItemOffset) {
        if (initLazyItemOffset > 0 && initLazyItemOffset <= 1) {
            mInitLazyItemOffset = initLazyItemOffset;
        }
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
        mLazyPagerAdapter = adapter != null && adapter instanceof LazyPagerAdapter ? (LazyPagerAdapter) adapter : null;
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

    /**
     * 禁止ViewPager滑动
     */
    public static boolean GO_TOUTH_CHILD = true;
    //拦截方法
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (GO_TOUTH_CHILD) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    //触摸方法 传给父控件
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (GO_TOUTH_CHILD) {
            return false;
        }
        return super.onTouchEvent(ev);
    }
}