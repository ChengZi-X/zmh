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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//因为不是所有的系统都可以设置颜色的，在4.4以下就不可以。。
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorAccent));
        }

    }

    @Override
    public void onClick(View view) {

    }
}
