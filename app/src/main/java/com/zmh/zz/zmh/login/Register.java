package com.zmh.zz.zmh.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.zmh.zz.zmh.LoadingDialog.ShapeLoadingDialog;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.httpurls.HttpURLs;
import com.zmh.zz.zmh.modeljson.LoginJson;
import com.zmh.zz.zmh.utlis.RegularUtil;
import com.zmh.zz.zmh.utlis.MyStringCallBack;
import com.zmh.zz.zmh.utlis.OkHttpUtil;
import com.zmh.zz.zmh.utlis.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;


/**
 * Created by Administrator
 * 注册
 */

public class Register extends AppCompatActivity implements View.OnClickListener {
    private EditText Et_Phone, Et_Code;
    private TextView Tv_TitleName;
    private RelativeLayout Rl_TitleBack;
    private Button But_Next, But_Login, But_GetCode;
    private OkHttpUtil okHttp = new OkHttpUtil();
    private String mPhoneValue, mCodeValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransparentTitleBar();
        setContentView(R.layout.ac_register);
        Tv_TitleName = (TextView) findViewById(R.id.tv_title_name);
        Tv_TitleName.setText("注册");
        TextPaint tp = Tv_TitleName.getPaint();
        tp.setFakeBoldText(true);
        Rl_TitleBack = (RelativeLayout) findViewById(R.id.rl_title_back);
        Et_Phone = (EditText) findViewById(R.id.et_phone);
        Et_Code = (EditText) findViewById(R.id.et_code);
        But_GetCode = (Button) findViewById(R.id.but_get_code);
        But_Next = (Button) findViewById(R.id.but_next);
        But_Login = (Button) findViewById(R.id.but_login);
        TextPaint tp1 = But_Login.getPaint();
        tp1.setFakeBoldText(true);
        Et_Phone.setFilters(new InputFilter[]{RegularUtil.filter});
        Et_Code.setFilters(new InputFilter[]{RegularUtil.filter});
        Rl_TitleBack.setOnClickListener(this);
        But_GetCode.setOnClickListener(this);
        But_Next.setOnClickListener(this);
        But_Login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mPhoneValue = Et_Phone.getText().toString();
        mCodeValue = Et_Code.getText().toString();
        switch (v.getId()) {
            case R.id.but_get_code:
                if (mPhoneValue.equals("")) {
                    ToastUtils.showToast(Register.this, "手机号不能为空");
                } else if (!RegularUtil.isMobileNO(mPhoneValue)) {
                    ToastUtils.showToast(Register.this, "手机号格式不正确");
                } else {
                    GetCode();//获取验证码
                }
                break;
            case R.id.but_next:
                if (mPhoneValue.equals("")) {
                    ToastUtils.showToast(Register.this, "手机号不能为空");
                } else if (!RegularUtil.isMobileNO(mPhoneValue)) {
                    ToastUtils.showToast(Register.this, "手机号格式不正确");
                } else if (mCodeValue.equals("")) {
                    ToastUtils.showToast(Register.this, "验证码不能为空");
                } else {
                    Next();//下一步
                }
                break;
            case R.id.rl_title_back:
                finish();
                break;
            case R.id.but_login:
                finish();
                break;
        }
    }

    private void GetCode() {
        mPhoneValue = Et_Phone.getText().toString();
        final ShapeLoadingDialog shapeLoadingDialog = new ShapeLoadingDialog(Register.this);
        shapeLoadingDialog.setCancelable(false);
        shapeLoadingDialog.setLoadingText("获取中,请稍等...");
        shapeLoadingDialog.show();
        String url = HttpURLs.GETCODE;
        Map<String, String> params = new HashMap<>();
        params.put("mobile", mPhoneValue);
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
                        ToastUtils.showToast(Register.this, dosc);
                        break;
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                shapeLoadingDialog.dismiss();
                Toast.makeText(Register.this, R.string.ConnectionError, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Next() {
        mPhoneValue = Et_Phone.getText().toString();
        mCodeValue = Et_Code.getText().toString();
        final ShapeLoadingDialog shapeLoadingDialog = new ShapeLoadingDialog(Register.this);
        shapeLoadingDialog.setCancelable(false);
        shapeLoadingDialog.setLoadingText("校验中,请稍等...");
        shapeLoadingDialog.show();
        String url = HttpURLs.VEFTCODE;
        Map<String, String> params = new HashMap<>();
        params.put("mobile", mPhoneValue);
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
                        Intent intent = new Intent(new Intent(Register.this, RegisterPassword.class));
                        intent.putExtra("mPhoneValue", mPhoneValue);
                        startActivity(intent);
                        finish();
                        break;
                    case 400:
                        shapeLoadingDialog.dismiss();
                        ToastUtils.showToast(Register.this, dosc);
                        break;
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                shapeLoadingDialog.dismiss();
                Toast.makeText(Register.this, R.string.ConnectionError, Toast.LENGTH_SHORT).show();
            }
        });
    }
    /**
     * 透明标题栏
     */
    private void TransparentTitleBar() {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

}