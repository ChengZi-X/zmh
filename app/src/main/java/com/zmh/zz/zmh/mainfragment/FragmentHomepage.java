package com.zmh.zz.zmh.mainfragment;

import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zmh.zz.zmh.BaseFragment;
import com.zmh.zz.zmh.R;

/**
 * 主页
 */
public class FragmentHomepage extends BaseFragment {
    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_homepage, null);
        setNotificationBar(R.color.babyblue);
        initData();
        return view;
    }

    public void initData() {
    }
}
