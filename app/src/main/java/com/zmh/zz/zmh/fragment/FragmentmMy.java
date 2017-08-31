package com.zmh.zz.zmh.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zmh.zz.zmh.BaseFragment;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.activity.Ac_Notice;
import com.zmh.zz.zmh.activity.Ac_Personal_Information;
import com.zmh.zz.zmh.activity.Ac_Real_Name_Authentication_true;
import com.zmh.zz.zmh.activity.Ac_Setting;
import com.zmh.zz.zmh.utlis.CircleImageView2;
import com.zmh.zz.zmh.utlis.CommomDialog;

/**
 * 我的
 */
public class FragmentmMy extends BaseFragment implements View.OnClickListener {
    private RelativeLayout mBut_setting;
    private View view;
    private RelativeLayout mReal_Name_Authentication, mBank_card, mService, mNotice;
    private LinearLayout mMe_integral;
    private String tel = 1833715230 + "";
    private String tel1 = 1 + "";
    private CircleImageView2 mIm_head_portrait;//头像
    private LinearLayout mPersonal_information;

    @Override
    public View initView() {
        view = View.inflate(getActivity(), R.layout.my, null);
        // 注册控件
        mBut_setting = (RelativeLayout) view.findViewById(R.id.but_setting);
        mIm_head_portrait = (CircleImageView2) view.findViewById(R.id.im_head_portrait);
        mPersonal_information = (LinearLayout) view.findViewById(R.id.personal_information);
        mMe_integral = (LinearLayout) view.findViewById(R.id.me_integral);
        mReal_Name_Authentication = (RelativeLayout) view.findViewById(R.id.real_name_authentication);
        mBank_card = (RelativeLayout) view.findViewById(R.id.bank_card);
        mNotice = (RelativeLayout) view.findViewById(R.id.notice);
        mService = (RelativeLayout) view.findViewById(R.id.service);
        mBut_setting.setOnClickListener(this);
        mIm_head_portrait.setOnClickListener(this);
        mPersonal_information.setOnClickListener(this);
        mMe_integral.setOnClickListener(this);
        mReal_Name_Authentication.setOnClickListener(this);
        mBank_card.setOnClickListener(this);
        mService.setOnClickListener(this);
        mNotice.setOnClickListener(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//因为不是所有的系统都可以设置颜色的，在4.4以下就不可以。。
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.babyblue));
        }
        initData();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_setting:
                startActivity(new Intent(getActivity(), Ac_Setting.class));
                break;
            case R.id.personal_information:
                startActivity(new Intent(getActivity(), Ac_Personal_Information.class));
                break;
//            case R.id.me_integral:
//                startActivity(new Intent(getActivity(), Ac_Me_Integral.class));
//                break;
            case R.id.real_name_authentication:
                startActivity(new Intent(getActivity(), Ac_Real_Name_Authentication_true.class));
                break;
//            case R.id.bank_card:
//                startActivity(new Intent(getActivity(), Ac_Bank_Card.class));
//                break;
            case R.id.notice:
                startActivity(new Intent(getActivity(), Ac_Notice.class));
                break;
//            case R.id.im_head_portrait:
//                startActivity(new Intent(getActivity(), Ac_Im_Head_Portrait.class));
//                break;
            case R.id.service:
                //弹出提示框
                new CommomDialog(getActivity(), R.style.dialog, tel + tel1, new CommomDialog.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            Uri data = Uri.parse("tel:" + tel + tel1);
                            intent.setData(data);
                            startActivity(intent);
                            dialog.dismiss();
                        }
                    }
                }).setTitle("是否拨打客服热线?").show();
                break;
        }

    }

    public void initData() {

    }
}
