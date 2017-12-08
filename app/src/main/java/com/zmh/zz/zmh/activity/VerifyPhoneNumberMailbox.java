package com.zmh.zz.zmh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.LoadingDialog.ShapeLoadingDialog;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.httpurls.HttpURLs;
import com.zmh.zz.zmh.modeljson.LoginJson;
import com.zmh.zz.zmh.utils.MyStringCallBack;
import com.zmh.zz.zmh.utils.OkHttpUtil;
import com.zmh.zz.zmh.utils.RegularUtil;
import com.zmh.zz.zmh.utils.SharedPreferencesUtil;
import com.zmh.zz.zmh.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;


/**
 * Created by Administrator
 * 账号与安全_手机号验证
 */

public class VerifyPhoneNumberMailbox extends BaseActivity implements View.OnClickListener {
    private EditText Et_Code;
    private Button But_GetCode, But_next;
    private OkHttpUtil okHttp = new OkHttpUtil();
    private TextView Tv_Phone;
    private String mCodeValue, UserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("验证身份");
        FindViewById();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_verify_phone_number_mailbox;
    }

    private void FindViewById() {
        UserName = (String) SharedPreferencesUtil.getParam(VerifyPhoneNumberMailbox.this, "UserName", "");
        String maskUserName = UserName.substring(0, 3) + "****" + UserName.substring(7, UserName.length());
        Tv_Phone = (TextView) findViewById(R.id.tv_phone);
        But_GetCode = (Button) findViewById(R.id.but_get_code);
        Et_Code = (EditText) findViewById(R.id.et_code);
        But_next = (Button) findViewById(R.id.but_next);
        Et_Code.setFilters(new InputFilter[]{RegularUtil.filter});
        Tv_Phone.setText(maskUserName);
        Et_Code.addTextChangedListener(this);
        But_GetCode.setOnClickListener(this);
        But_next.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_get_code:
                GetCode();//获取验证码
                break;
            case R.id.but_next:
                Next();//下一步
                break;
        }
    }

    private void GetCode() {
        UserName = (String) SharedPreferencesUtil.getParam(VerifyPhoneNumberMailbox.this, "UserName", "");
        final ShapeLoadingDialog shapeLoadingDialog = new ShapeLoadingDialog(VerifyPhoneNumberMailbox.this);
        shapeLoadingDialog.setCancelable(false);
        shapeLoadingDialog.setLoadingText("获取中,请稍等...");
        shapeLoadingDialog.show();
        String url = HttpURLs.GETFCODE;
        Map<String, String> params = new HashMap<>();
        params.put("mobile", UserName);
        okHttp.postRequest(url, params, new MyStringCallBack() {
            @Override
            public void onResponse(String response, int id) {
                Log.e("sssss>>>", response);
                LoginJson login = JSONObject.parseObject(response, LoginJson.class);
                int code = login.getCode();
                String desc = login.getDesc();
                switch (code) {
                    case 200:
                        shapeLoadingDialog.dismiss();
                        break;
                    case 400:
                        shapeLoadingDialog.dismiss();
                        ToastUtils.showToast(VerifyPhoneNumberMailbox.this, desc);
                        break;
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                shapeLoadingDialog.dismiss();
                Toast.makeText(VerifyPhoneNumberMailbox.this, R.string.ConnectionError, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Next() {
        mCodeValue = Et_Code.getText().toString();
        UserName = (String) SharedPreferencesUtil.getParam(VerifyPhoneNumberMailbox.this, "UserName", "");
        final ShapeLoadingDialog shapeLoadingDialog = new ShapeLoadingDialog(VerifyPhoneNumberMailbox.this);
        shapeLoadingDialog.setCancelable(false);
        shapeLoadingDialog.setLoadingText("验证中,请稍等...");
        shapeLoadingDialog.show();
        String url = HttpURLs.MODIFYEMAILCODE;
        Map<String, String> params = new HashMap<>();
        params.put("mobile", UserName);
        params.put("code", mCodeValue);
        okHttp.postRequest(url, params, new MyStringCallBack() {
            @Override
            public void onResponse(String response, int id) {
                Log.e("sssss>>>", response);
                LoginJson login = JSONObject.parseObject(response, LoginJson.class);
                int code = login.getCode();
                String desc = login.getDesc();
                switch (code) {
                    case 200:
                        shapeLoadingDialog.dismiss();
                        startActivity(new Intent(VerifyPhoneNumberMailbox.this, NewMailbox.class));
                        finish();
                        break;
                    case 400:
                        shapeLoadingDialog.dismiss();
                        ToastUtils.showToast(VerifyPhoneNumberMailbox.this, desc);
                        break;
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                shapeLoadingDialog.dismiss();
                Toast.makeText(VerifyPhoneNumberMailbox.this, R.string.ConnectionError, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void afterTextChanged(Editable editable) {
        mCodeValue = Et_Code.getText().toString();
        if (mCodeValue.equals("")) {
            But_next.setEnabled(false);
        } else {
            But_next.setEnabled(true);
        }
    }
}
