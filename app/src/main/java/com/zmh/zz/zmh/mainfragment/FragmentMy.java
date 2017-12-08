package com.zmh.zz.zmh.mainfragment;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zmh.zz.zmh.BaseFragment;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.activity.AddBankCard;
import com.zmh.zz.zmh.activity.ClientMesh;
import com.zmh.zz.zmh.activity.Feedback;
import com.zmh.zz.zmh.activity.MyClient;
import com.zmh.zz.zmh.activity.Notice;
import com.zmh.zz.zmh.activity.PersonalInformation;
import com.zmh.zz.zmh.activity.RealNameAuthenticationTrue;
import com.zmh.zz.zmh.activity.Setting;
import com.zmh.zz.zmh.integralfragment.TabIntegral;
import com.zmh.zz.zmh.utils.CircleImageView2Util;
import com.zmh.zz.zmh.utils.ServiceDialogUtil;

/**
 * 我的
 */
public class FragmentMy extends BaseFragment implements View.OnClickListener {
    private RelativeLayout Rl_But_setting;
    private View view;
    private RelativeLayout Rl_Real_Name_Authentication, Rl_Bank_card, Rl_Service, Rl_Notice, Rl_My_client, Rl_Client_mesh, Rl_Feedback;
    private LinearLayout Ll_Me_integral;
    private String tel = "18337152301";
    private CircleImageView2Util Im_head_portrait;//头像
    private LinearLayout Ll_Personal_information;

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
        Rl_But_setting = (RelativeLayout) view.findViewById(R.id.rl_but_setting);
        Im_head_portrait = (CircleImageView2Util) view.findViewById(R.id.im_head_portrait);
        Ll_Personal_information = (LinearLayout) view.findViewById(R.id.ll_personal_information);
        Ll_Me_integral = (LinearLayout) view.findViewById(R.id.ll_me_integral);
        Rl_Real_Name_Authentication = (RelativeLayout) view.findViewById(R.id.rl_real_name_authentication);
        Rl_Bank_card = (RelativeLayout) view.findViewById(R.id.rl_bank_card);
        Rl_Notice = (RelativeLayout) view.findViewById(R.id.rl_notice);
        Rl_Feedback = (RelativeLayout) view.findViewById(R.id.rl_feedback);
        Rl_Service = (RelativeLayout) view.findViewById(R.id.rl_service);
        Rl_My_client = (RelativeLayout) view.findViewById(R.id.rl_my_client);
        Rl_Client_mesh = (RelativeLayout) view.findViewById(R.id.rl_client_mesh);
        Rl_But_setting.setOnClickListener(this);
        Im_head_portrait.setOnClickListener(this);
        Ll_Personal_information.setOnClickListener(this);
        Ll_Me_integral.setOnClickListener(this);
        Rl_Real_Name_Authentication.setOnClickListener(this);
        Rl_Bank_card.setOnClickListener(this);
        Rl_Service.setOnClickListener(this);
        Rl_Notice.setOnClickListener(this);
        Rl_Feedback.setOnClickListener(this);
        Rl_My_client.setOnClickListener(this);
        Rl_Client_mesh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_but_setting:
                startActivity(new Intent(getActivity(), Setting.class));
                break;
            case R.id.ll_personal_information:
                startActivity(new Intent(getActivity(), PersonalInformation.class));
                break;
            case R.id.ll_me_integral:
                startActivity(new Intent(getActivity(), TabIntegral.class));
                break;
            case R.id.rl_real_name_authentication:
                startActivity(new Intent(getActivity(), RealNameAuthenticationTrue.class));
                break;
            case R.id.rl_bank_card:
                startActivity(new Intent(getActivity(), AddBankCard.class));
                break;
            case R.id.rl_notice:
                startActivity(new Intent(getActivity(), Notice.class));
                break;
            case R.id.rl_feedback:
                startActivity(new Intent(getActivity(), Feedback.class));
                break;
            case R.id.rl_my_client:
                startActivity(new Intent(getActivity(), MyClient.class));
                break;
            case R.id.rl_client_mesh:
                startActivity(new Intent(getActivity(), ClientMesh.class));
                break;
//            case R.id.im_head_portrait:
//                startActivity(new Intent(getActivity(), Ac_Im_Head_Portrait.class));
//                break;
            case R.id.rl_service:
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
