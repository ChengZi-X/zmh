package com.zmh.zz.zmh.mainfragment;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zmh.zz.zmh.BaseFragment;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.activity.ClientMesh;
import com.zmh.zz.zmh.activity.Feedback;
import com.zmh.zz.zmh.activity.MyBankCard;
import com.zmh.zz.zmh.activity.MyClient;
import com.zmh.zz.zmh.activity.Notice;
import com.zmh.zz.zmh.activity.PersonalInformation;
import com.zmh.zz.zmh.activity.RealNameAuthenticationTrue;
import com.zmh.zz.zmh.activity.Setting;
import com.zmh.zz.zmh.integralfragment.TabIntegral;
import com.zmh.zz.zmh.utlis.CircleImageView2Util;
import com.zmh.zz.zmh.utlis.ServiceDialogUtil;

/**
 * 我的
 */
public class FragmentmMy extends BaseFragment implements View.OnClickListener {
    private RelativeLayout mBut_setting;
    private View view;
    private RelativeLayout mReal_Name_Authentication, mBank_card, mService, mNotice, mMy_client, mClient_mesh, mFeedback;
    private LinearLayout mMe_integral;
    private String tel = "18337152301";
    private CircleImageView2Util mIm_head_portrait;//头像
    private LinearLayout mPersonal_information;

    @Override
    public View initView() {
        view = View.inflate(getActivity(), R.layout.fragment_my, null);
        setNotificationBar(R.color.f4);
        FindViewById();
        initData();
        return view;
    }

    private void FindViewById() {
        // 注册控件
        mBut_setting = (RelativeLayout) view.findViewById(R.id.but_setting);
        mIm_head_portrait = (CircleImageView2Util) view.findViewById(R.id.im_head_portrait);
        mPersonal_information = (LinearLayout) view.findViewById(R.id.personal_information);
        mMe_integral = (LinearLayout) view.findViewById(R.id.me_integral);
        mReal_Name_Authentication = (RelativeLayout) view.findViewById(R.id.real_name_authentication);
        mBank_card = (RelativeLayout) view.findViewById(R.id.bank_card);
        mNotice = (RelativeLayout) view.findViewById(R.id.notice);
        mFeedback = (RelativeLayout) view.findViewById(R.id.feedback);
        mService = (RelativeLayout) view.findViewById(R.id.service);
        mMy_client = (RelativeLayout) view.findViewById(R.id.my_client);
        mClient_mesh = (RelativeLayout) view.findViewById(R.id.client_mesh);
        mBut_setting.setOnClickListener(this);
        mIm_head_portrait.setOnClickListener(this);
        mPersonal_information.setOnClickListener(this);
        mMe_integral.setOnClickListener(this);
        mReal_Name_Authentication.setOnClickListener(this);
        mBank_card.setOnClickListener(this);
        mService.setOnClickListener(this);
        mNotice.setOnClickListener(this);
        mFeedback.setOnClickListener(this);
        mMy_client.setOnClickListener(this);
        mClient_mesh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_setting:
                startActivity(new Intent(getActivity(), Setting.class));
                break;
            case R.id.personal_information:
                startActivity(new Intent(getActivity(), PersonalInformation.class));
                break;
            case R.id.me_integral:
                startActivity(new Intent(getActivity(), TabIntegral.class));
                break;
            case R.id.real_name_authentication:
                startActivity(new Intent(getActivity(), RealNameAuthenticationTrue.class));
                break;
            case R.id.bank_card:
                startActivity(new Intent(getActivity(), MyBankCard.class));
                break;
            case R.id.notice:
                startActivity(new Intent(getActivity(), Notice.class));
                break;
            case R.id.feedback:
                startActivity(new Intent(getActivity(), Feedback.class));
                break;
            case R.id.my_client:
                startActivity(new Intent(getActivity(), MyClient.class));
                break;
            case R.id.client_mesh:
                startActivity(new Intent(getActivity(), ClientMesh.class));
                break;
//            case R.id.im_head_portrait:
//                startActivity(new Intent(getActivity(), Ac_Im_Head_Portrait.class));
//                break;
            case R.id.service:
                //弹出提示框
                new ServiceDialogUtil(getActivity(), tel, new ServiceDialogUtil.OnCloseListener() {
                    @Override
                    public void onClick(Dialog dialog, boolean confirm) {
                        if (confirm) {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            Uri data = Uri.parse("tel:" + tel);
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
