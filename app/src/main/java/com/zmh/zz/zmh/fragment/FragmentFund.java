package com.zmh.zz.zmh.fragment;

import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zmh.zz.zmh.BaseFragment;
import com.zmh.zz.zmh.R;

/**
 * 资金
 */
public class FragmentFund extends BaseFragment implements View.OnClickListener {
    private View view;

    @Override
    public View initView() {
        view = View.inflate(getActivity(), R.layout.fund, null);
        initData();
        return view;
    }

    public void initData() {
        //改变通知栏的颜色，4.4以下不可以。。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorAccent));
        }
    }

    @Override
    public void onClick(View view) {

    }
}
