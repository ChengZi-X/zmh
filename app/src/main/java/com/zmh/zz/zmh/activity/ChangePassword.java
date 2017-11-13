package com.zmh.zz.zmh.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.LoadingDialog.ShapeLoadingDialog;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.httpurls.HttpURLs;
import com.zmh.zz.zmh.login.Login;
import com.zmh.zz.zmh.modeljson.LoginJson;
import com.zmh.zz.zmh.utlis.RegularUtil;
import com.zmh.zz.zmh.utlis.MD5Util;
import com.zmh.zz.zmh.utlis.MyStringCallBack;
import com.zmh.zz.zmh.utlis.OkHttpUtil;
import com.zmh.zz.zmh.utlis.SharedPreferencesUtils;
import com.zmh.zz.zmh.utlis.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by Administrator
 * 账号与安全_密码修改
 */

public class ChangePassword extends BaseActivity implements View.OnClickListener, TextWatcher {
    private EditText Et_OldPassword, Et_NewPassword, Et_ConfirmNewPassword;
    private Button But_affirm;
    private OkHttpUtil okHttp = new OkHttpUtil();
    private String mOldPasswordValue, mNewPasswordValue, mConfirmNewPasswordValue, UserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("修改密码");
        FindViewById();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_change_password;//任意非空布局
    }

    private void FindViewById() {
        Et_OldPassword = (EditText) findViewById(R.id.et_old_password);
        Et_NewPassword = (EditText) findViewById(R.id.et_new_password);
        Et_ConfirmNewPassword = (EditText) findViewById(R.id.et_confirm_new_password);
        But_affirm = (Button) findViewById(R.id.but_affirm);
        Et_OldPassword.setFilters(new InputFilter[]{RegularUtil.filter});
        Et_NewPassword.setFilters(new InputFilter[]{RegularUtil.filter});
        Et_ConfirmNewPassword.setFilters(new InputFilter[]{RegularUtil.filter});
        Et_OldPassword.addTextChangedListener(this);
        Et_NewPassword.addTextChangedListener(this);
        Et_ConfirmNewPassword.addTextChangedListener(this);
        But_affirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mOldPasswordValue = Et_OldPassword.getText().toString();
        mNewPasswordValue = Et_NewPassword.getText().toString();
        mConfirmNewPasswordValue = Et_ConfirmNewPassword.getText().toString();
        switch (v.getId()) {
            case R.id.but_affirm:
                if (mNewPasswordValue.length() < 6) {
                    ToastUtils.showToast(ChangePassword.this, "密码不能少于六位数");
                } else if (!RegularUtil.isLetterDigit(mNewPasswordValue)) {
                    ToastUtils.showToast(ChangePassword.this, "至少包含大小写字母及数字中的两种");
                } else if (!mNewPasswordValue.equals(mConfirmNewPasswordValue)) {
                    ToastUtils.showToast(ChangePassword.this, "两次密码不一样");
                } else if (mNewPasswordValue.equals(mOldPasswordValue)) {
                    ToastUtils.showToast(ChangePassword.this, "新密码不能和当前密码一样");
                } else {
                    Affirm();//确定修改密码
                }
                break;
        }
    }

    private void Affirm() {
        mOldPasswordValue = Et_OldPassword.getText().toString();
        mNewPasswordValue = Et_NewPassword.getText().toString();
        UserName = (String) SharedPreferencesUtils.getParam(ChangePassword.this, "UserName", "");
        final ShapeLoadingDialog shapeLoadingDialog = new ShapeLoadingDialog(ChangePassword.this);
        shapeLoadingDialog.setCancelable(false);
        shapeLoadingDialog.setLoadingText("修改中,请稍等...");
        shapeLoadingDialog.show();
        String url = HttpURLs.MODIFYPASSWORD;
        Map<String, String> params = new HashMap<>();
        params.put("loginname", UserName);
        params.put("oldPassdword", MD5Util.MD5(mOldPasswordValue, 32));
        params.put("password", MD5Util.MD5(mNewPasswordValue, 32));
        okHttp.postRequest(url, params, new MyStringCallBack() {
            @Override
            public void onResponse(String response, int id) {
                Log.e("sssss>>>", response);
                LoginJson login = JSONObject.parseObject(response, LoginJson.class);
                int code = login.getCode();
                String dosc = login.getDesc();
                switch (code) {
                    case 200:
                        shapeLoadingDialog.dismiss();
                        SharedPreferences spout = ChangePassword.this.getSharedPreferences("UserInfo", 0);
                        SharedPreferences.Editor ed = spout.edit();
                        ed.remove("PASSWORD").commit();//清除账号缓存
                        ToastUtils.showToast(ChangePassword.this, "密码修改成功,请重新登录");
                        Intent intent = new Intent(ChangePassword.this, Login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                        break;
                    case 400:
                        shapeLoadingDialog.dismiss();
                        ToastUtils.showToast(ChangePassword.this, dosc);
                        break;
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                shapeLoadingDialog.dismiss();
                Toast.makeText(ChangePassword.this, R.string.ConnectionError, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void afterTextChanged(Editable editable) {
        mOldPasswordValue = Et_OldPassword.getText().toString();
        mNewPasswordValue = Et_NewPassword.getText().toString();
        mConfirmNewPasswordValue = Et_ConfirmNewPassword.getText().toString();
        if (mOldPasswordValue.equals("") || mNewPasswordValue.equals("") || mConfirmNewPasswordValue.equals("")) {
            But_affirm.setEnabled(false);
        } else {
            But_affirm.setEnabled(true);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }
}
