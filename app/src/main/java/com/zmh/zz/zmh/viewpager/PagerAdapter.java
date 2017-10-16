package com.zmh.zz.zmh.viewpager;

import android.util.SparseArray;
import android.view.ViewGroup;

/**
 * PagerAdapter that items added lazily
 * @param <T>
 */
public abstract class PagerAdapter<T> extends android.support.v4.view.PagerAdapter {

    protected SparseArray<T> mLazyItems = new SparseArray<T>();
    private T mCurrentItem;

    /**
     * add lazy item to container
     * @param container {@link ViewPagerMain}
     * @param position the position that the item added to
     * @return the item added
     */
    protected abstract T addLazyItem(ViewGroup container, int position);

    /**
     * get the lazy item
     * @param container {@link ViewPagerMain}
     * @param position the position of lazy item
     * @return the lazy item
     */
    protected abstract T getItem(ViewGroup container, int position);

    /**
     * whether the item is lazily or not
     * @param position the position of item
     * @return the item is lazily
     */
    public boolean isLazyItem(int position) {
        return mLazyItems.get(position) != null;
    }

    /**
     * get the current item
     * @return the current item
     */
    public T getCurrentItem() {
        return mCurrentItem;
    }

    /**
     * call {@link PagerAdapter#addLazyItem(ViewGroup, int)}
     * to prevent {@link ViewPagerMain#onPageScrolled(int, float, int)} not working when the offset of {@link ViewPagerMain} is too big
     */
    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mCurrentItem = addLazyItem(container, position);
    }

}
