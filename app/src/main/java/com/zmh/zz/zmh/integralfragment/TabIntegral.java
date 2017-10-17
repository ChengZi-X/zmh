package com.zmh.zz.zmh.integralfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;
import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.activity.CheckCalendar;
import com.zmh.zz.zmh.activity.IntegralDetail;
import com.zmh.zz.zmh.viewpager.FragmentPagerAdapter;
import com.zmh.zz.zmh.viewpager.ViewPagerMain;

/**
 * 积分
 */
public class TabIntegral extends BaseActivity implements View.OnClickListener {
    public AdvancedPagerSlidingTabStrip mAPSTS;
    public ViewPagerMain mVP;
    private static final int VIEW_FIRST = 0;
    private static final int VIEW_SECOND = 1;
    private static final int VIEW_THREE = 2;
    private FragmentExchange mFirstFragment = null;
    private FragmentTask mSecondFragment = null;

    private RelativeLayout mSign_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("积分");
        setRtTitle("明细");
        setRightBtnVisible(true);
        FindViewById();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_tab_integral;
    }

    private void FindViewById() {
        mAPSTS = (AdvancedPagerSlidingTabStrip) findViewById(R.id.tabs);
        mVP = (ViewPagerMain) findViewById(R.id.vp_main);
        mVP.setOffscreenPageLimit(VIEW_THREE);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        mVP.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        adapter.notifyDataSetChanged();
        mAPSTS.setViewPager(mVP);
        mSign_number = (RelativeLayout) findViewById(R.id.sign_number);
        mSign_number.setOnClickListener(this);
    }
    //右键点击
    @Override
    protected void onClickRight() {
        startActivity(new Intent(TabIntegral.this, IntegralDetail.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_number:
                startActivity(new Intent(TabIntegral.this, CheckCalendar.class));
                break;
        }
    }

    public class FragmentAdapter extends FragmentPagerAdapter {
        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        protected Fragment getItem(ViewGroup container, int position) {
            if (position >= 0 && position < VIEW_THREE) {
                switch (position) {
                    case VIEW_FIRST:
                        if (null == mFirstFragment)
                            mFirstFragment = new FragmentExchange();
                        return mFirstFragment;
                    case VIEW_SECOND:
                        if (null == mSecondFragment)
                            mSecondFragment = new FragmentTask();
                        return mSecondFragment;
                    default:
                        break;
                }
            }
            return null;
        }
        @Override
        public int getCount() {
            return VIEW_THREE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position >= 0 && position < VIEW_THREE) {
                switch (position) {
                    case VIEW_FIRST:
                        return "     兑换     ";
                    case VIEW_SECOND:
                        return "     任务     ";
                    default:
                        break;
                }
            }
            return null;
        }


    }
}
