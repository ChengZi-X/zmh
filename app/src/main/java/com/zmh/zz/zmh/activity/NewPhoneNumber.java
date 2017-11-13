package com.zmh.zz.zmh.activity;

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
import com.zmh.zz.zmh.modeljson.LoginJson;
import com.zmh.zz.zmh.utlis.RegularUtil;
import com.zmh.zz.zmh.utlis.MyStringCallBack;
import com.zmh.zz.zmh.utlis.OkHttpUtil;
import com.zmh.zz.zmh.utlis.SharedPreferencesUtils;
import com.zmh.zz.zmh.utlis.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by Administrator
 * 账号与安全_改绑新手机
 */

public class NewPhoneNumber extends BaseActivity implements View.OnClickListener, TextWatcher {
    private EditText Et_NewPhone, Et_Code;
    private Button But_Binding, But_GetCode;
    private OkHttpUtil okHttp = new OkHttpUtil();
    private String UserName, mNewPhoneValue, mCodeValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("改绑手机号");
        FindViewById();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_new_phone_number;
    }

    private void FindViewById() {
        Et_NewPhone = (EditText) findViewById(R.id.et_new_phone);
        But_GetCode = (Button) findViewById(R.id.but_get_code);
        Et_Code = (EditText) findViewById(R.id.et_code);
        But_Binding = (Button) findViewById(R.id.but_binding);
        Et_NewPhone.setFilters(new InputFilter[]{RegularUtil.filter});
        Et_Code.setFilters(new InputFilter[]{RegularUtil.filter});
        Et_NewPhone.addTextChangedListener(this);
        Et_Code.addTextChangedListener(this);
        But_GetCode.setOnClickListener(this);
        But_Binding.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        mNewPhoneValue = Et_NewPhone.getText().toString();
        mCodeValue = Et_Code.getText().toString();
        switch (v.getId()) {
            case R.id.but_get_code:
                if (!RegularUtil.isMobileNO(mNewPhoneValue)) {
                    ToastUtils.showToast(NewPhoneNumber.this, "手机号格式不正确");
                } else {
                    GetCode();//获取验证码
                }
                break;
            case R.id.but_binding:
                if (!RegularUtil.isMobileNO(mNewPhoneValue)) {
                    ToastUtils.showToast(NewPhoneNumber.this, "手机号格式不正确");
                } else {
                    BinDing();//改绑手机号
                }
                break;
        }
    }

    private void GetCode() {
        UserName = (String) SharedPreferencesUtils.getParam(NewPhoneNumber.this, "UserName", "");
        final ShapeLoadingDialog shapeLoadingDialog = new ShapeLoadingDialog(NewPhoneNumber.this);
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
                String dosc = login.getDesc();
                switch (code) {
                    case 200:
                        shapeLoadingDialog.dismiss();
                        break;
                    case 400:
                        shapeLoadingDialog.dismiss();
                        ToastUtils.showToast(NewPhoneNumber.this, dosc);
                        break;
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                shapeLoadingDialog.dismiss();
                Toast.makeText(NewPhoneNumber.this, R.string.ConnectionError, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void BinDing() {
        mNewPhoneValue = Et_NewPhone.getText().toString();
        mCodeValue = Et_Code.getText().toString();
        UserName = (String) SharedPreferencesUtils.getParam(NewPhoneNumber.this, "UserName", "");
        final ShapeLoadingDialog shapeLoadingDialog = new ShapeLoadingDialog(NewPhoneNumber.this);
        shapeLoadingDialog.setCancelable(false);
        shapeLoadingDialog.setLoadingText("绑定中,请稍等...");
        shapeLoadingDialog.show();
        String url = HttpURLs.MODIFYNEWMOBILE;
        Map<String, String> params = new HashMap<>();
        params.put("loginname", UserName);
        params.put("newMobile", mNewPhoneValue);
        params.put("code", mCodeValue);
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
                        ToastUtils.showToast(NewPhoneNumber.this, "重新绑定成功");
                        break;
                    case 400:
                        shapeLoadingDialog.dismiss();
                        ToastUtils.showToast(NewPhoneNumber.this, dosc);
                        break;
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                shapeLoadingDialog.dismiss();
                Toast.makeText(NewPhoneNumber.this, R.string.ConnectionError, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void afterTextChanged(Editable editable) {
        mNewPhoneValue = Et_NewPhone.getText().toString();
        mCodeValue = Et_Code.getText().toString();
        if (mNewPhoneValue.equals("") || mCodeValue.equals("")) {
            But_Binding.setEnabled(false);
        } else {
            But_Binding.setEnabled(true);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

}
