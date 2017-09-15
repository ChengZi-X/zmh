package com.zmh.zz.zmh.integral_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.activity.Ac_Check_Calendar;
import com.zmh.zz.zmh.activity.Ac_Integral_Detail;
import com.zmh.zz.zmh.lazyviewpager.LazyFragmentPagerAdapter;
import com.zmh.zz.zmh.lazyviewpager.LazyViewPager;

/**
 * 积分
 */
public class Ac_Tab_Integral extends AppCompatActivity implements View.OnClickListener {
    public AdvancedPagerSlidingTabStrip mAPSTS;
    public LazyViewPager mVP;
    private static final int VIEW_FIRST = 0;
    private static final int VIEW_SECOND = 1;
    private static final int VIEW_THREE = 2;
    private FragmentExchange mFirstFragment = null;
    private FragmentTask mSecondFragment = null;

    private RelativeLayout mSign_number;
    private TextView toolbartitle, mIntegral_detail;
    private RelativeLayout mTitle_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_tab_integral);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("账号与安全");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        mTitle_back = (RelativeLayout) findViewById(R.id.title_back);
        mIntegral_detail = (TextView) findViewById(R.id.integral_detail);
        mTitle_back.setOnClickListener(this);
        mIntegral_detail.setOnClickListener(this);

        mAPSTS = (AdvancedPagerSlidingTabStrip) findViewById(R.id.tabs);
        mVP = (LazyViewPager) findViewById(R.id.vp_main);
        mVP.setOffscreenPageLimit(VIEW_THREE);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        mVP.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        adapter.notifyDataSetChanged();
        mAPSTS.setViewPager(mVP);
        mSign_number = (RelativeLayout) findViewById(R.id.sign_number);
        mSign_number.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_number:
                startActivity(new Intent(Ac_Tab_Integral.this, Ac_Check_Calendar.class));
                break;
            case R.id.integral_detail:
                startActivity(new Intent(Ac_Tab_Integral.this, Ac_Integral_Detail.class));
                break;
            case R.id.title_back:
                finish();
                break;
        }
    }

    public class FragmentAdapter extends LazyFragmentPagerAdapter {
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
                        return "     积分     ";
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
