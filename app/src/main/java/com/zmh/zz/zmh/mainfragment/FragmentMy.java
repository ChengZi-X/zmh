package com.zmh.zz.zmh.mainfragment;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.zmh.zz.zmh.BaseFragment;
import com.zmh.zz.zmh.LoadingDialog.ShapeLoadingDialog;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.activity.AddBankCard;
import com.zmh.zz.zmh.activity.ClientMesh;
import com.zmh.zz.zmh.activity.Feedback;
import com.zmh.zz.zmh.activity.MyClient;
import com.zmh.zz.zmh.activity.Notice;
import com.zmh.zz.zmh.activity.PersonalInformation;
import com.zmh.zz.zmh.activity.RealNameAuthenticationFalse;
import com.zmh.zz.zmh.activity.RealNameAuthenticationTrue;
import com.zmh.zz.zmh.activity.Setting;
import com.zmh.zz.zmh.httpurls.HttpURLs;
import com.zmh.zz.zmh.integralfragment.TabIntegral;
import com.zmh.zz.zmh.modeljson.UserJson;
import com.zmh.zz.zmh.utils.CircleImageView2Util;
import com.zmh.zz.zmh.utils.MyStringCallBack;
import com.zmh.zz.zmh.utils.OkHttpUtil;
import com.zmh.zz.zmh.utils.ServiceDialogUtil;
import com.zmh.zz.zmh.utils.SharedPreferencesUtil;
import com.zmh.zz.zmh.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * 我的
 */
public class FragmentMy extends BaseFragment implements View.OnClickListener {
    private RelativeLayout Rl_But_setting;
    private RelativeLayout Rl_Real_name_authentication, Rl_Bank_card, Rl_Service, Rl_Notice, Rl_My_client, Rl_Client_mesh, Rl_Feedback;
    private String tel = "18337152301";
    private CircleImageView2Util Im_Head_portrait;//头像
    private LinearLayout Ll_Personal_information, Ll_Me_integral;
    private TextView Tv_Userid, Tv_Usertype, Tv_VerifiedStatus, Tv_BankStatus;
    private int VerifiedStatus, BankStatus;
    private View view;
    private OkHttpUtil okHttp = new OkHttpUtil();

    @Override
    public View initView() {
        view = View.inflate(getActivity(), R.layout.fragment_my, null);
        setNotificationBar(R.color.f4);
        FindViewById();
        String AccountNum = (String) SharedPreferencesUtil.getParam(getActivity(), "accountNum", "");
        Tv_Userid.setText(AccountNum);
        return view;
    }

    private void FindViewById() {
        // 注册控件
        Rl_But_setting = (RelativeLayout) view.findViewById(R.id.rl_but_setting);
        Im_Head_portrait = (CircleImageView2Util) view.findViewById(R.id.im_head_portrait);
        Ll_Personal_information = (LinearLayout) view.findViewById(R.id.ll_personal_information);
        Ll_Me_integral = (LinearLayout) view.findViewById(R.id.ll_me_integral);
        Rl_Real_name_authentication = (RelativeLayout) view.findViewById(R.id.rl_real_name_authentication);
        Rl_Bank_card = (RelativeLayout) view.findViewById(R.id.rl_bank_card);
        Rl_Notice = (RelativeLayout) view.findViewById(R.id.rl_notice);
        Rl_Feedback = (RelativeLayout) view.findViewById(R.id.rl_feedback);
        Rl_Service = (RelativeLayout) view.findViewById(R.id.rl_service);
        Rl_My_client = (RelativeLayout) view.findViewById(R.id.rl_my_client);
        Rl_Client_mesh = (RelativeLayout) view.findViewById(R.id.rl_client_mesh);
        Rl_But_setting.setOnClickListener(this);
        Im_Head_portrait.setOnClickListener(this);
        Ll_Personal_information.setOnClickListener(this);
        Ll_Me_integral.setOnClickListener(this);
        Rl_Service.setOnClickListener(this);
        Rl_Notice.setOnClickListener(this);
        Rl_Feedback.setOnClickListener(this);
        Rl_My_client.setOnClickListener(this);
        Rl_Client_mesh.setOnClickListener(this);
        Tv_Userid = (TextView) view.findViewById(R.id.tv_userid);
        Tv_Usertype = (TextView) view.findViewById(R.id.tv_usertype);
        Tv_VerifiedStatus = (TextView) view.findViewById(R.id.tv_verifiedstatus);
        Tv_BankStatus = (TextView) view.findViewById(R.id.tv_bankstatus);
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
                if (VerifiedStatus == 4) {
                    startActivity(new Intent(getActivity(), RealNameAuthenticationFalse.class));
                } else if (VerifiedStatus == 2) {
                    startActivity(new Intent(getActivity(), RealNameAuthenticationTrue.class));
                } else if (VerifiedStatus == 1) {
                    ToastUtils.showToast(getActivity(), "正在审核请耐心等待!");
                } else if (VerifiedStatus == 3) {
                    startActivity(new Intent(getActivity(), RealNameAuthenticationFalse.class));
                }
                break;
            case R.id.rl_bank_card:
                startActivity(new Intent(getActivity(), AddBankCard.class));
                if (VerifiedStatus == 2) {
                    if (BankStatus == 4) {
                        startActivity(new Intent(getActivity(), AddBankCard.class));
                    } else if (BankStatus == 2) {
                        ToastUtils.showToast(getActivity(), "已经绑定成功了,无需在绑定!");
                    } else if (BankStatus == 1) {
                        ToastUtils.showToast(getActivity(), "正在审核请耐心等待!");
                    } else if (BankStatus == 3) {
                        startActivity(new Intent(getActivity(), AddBankCard.class));
                    }
                } else {
                    ToastUtils.showToast(getActivity(), "请先实名认证");
                }
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

    @Override
    public void onResume() {
        super.onResume();
        //用户信息
        UserInfo();
    }

    private void UserInfo() {
        final ShapeLoadingDialog shapeLoadingDialog = new ShapeLoadingDialog(getActivity());
        shapeLoadingDialog.setCancelable(false);
        shapeLoadingDialog.setLoadingText("加载中,请稍等...");
        shapeLoadingDialog.show();
        String Token = (String) SharedPreferencesUtil.getParam(getActivity(), "Token", "");
        String url = HttpURLs.TOUSERINFO;
        Map<String, String> params = new HashMap<>();
        params.put("token", Token);
        okHttp.postRequest(url, params, new MyStringCallBack() {
            @Override
            public void onResponse(String response, int id) {
                shapeLoadingDialog.dismiss();
                Log.e("sssss>>>", response);
                UserJson user = JSONObject.parseObject(response, UserJson.class);
                int code = user.getCode();
                switch (code) {
                    case 200:
                        shapeLoadingDialog.dismiss();
                        VerifiedStatus = user.getData().getVerifiedStatus();//实名认证
                        BankStatus = user.getData().getBankStatus();//银行卡认证
                        initData();
                        break;
                    case 400:
                        shapeLoadingDialog.dismiss();
                        ToastUtils.showToast(getActivity(), "系统异常");
                        break;
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                shapeLoadingDialog.dismiss();
                Toast.makeText(getActivity(), R.string.ConnectionError, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initData() {
        if (VerifiedStatus == 4) {
            Tv_VerifiedStatus.setText("未完善");
        }
        if (VerifiedStatus == 1) {
            Tv_VerifiedStatus.setText("待审核");
        }
        if (VerifiedStatus == 2) {
            Tv_VerifiedStatus.setText("已实名");
        }
        if (VerifiedStatus == 3) {
            Tv_VerifiedStatus.setText("审核失败");
        }
        if (BankStatus == 4) {
            Tv_BankStatus.setText("未绑定");
        }
        if (BankStatus == 1) {
            Tv_BankStatus.setText("待审核");
        }
        if (BankStatus == 2) {
            Tv_BankStatus.setText("已绑定");
        }
        if (BankStatus == 3) {
            Tv_BankStatus.setText("审核失败");
        }
        Rl_Real_name_authentication.setOnClickListener(this);
        Rl_Bank_card.setOnClickListener(this);
    }
}
