package com.zmh.zz.zmh.login;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.zmh.zz.zmh.utlis.CheckoutUtil;
import com.zmh.zz.zmh.utlis.MD5Util;
import com.zmh.zz.zmh.utlis.MyStringCallBack;
import com.zmh.zz.zmh.utlis.OkHttpUtil;
import com.zmh.zz.zmh.utlis.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;


/**
 * Created by Administrator
 * 重置密码
 */

public class ResetPassword extends AppCompatActivity implements View.OnClickListener {
    private EditText Et_Password, Et_Password1;
    private TextView Tv_TitleName;
    private RelativeLayout Rl_TitleBack;
    private Button But_Affirm;
    private OkHttpUtil okHttp = new OkHttpUtil();
    private String mPhoneValue, mPasswordValue, mPassword1Value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransparentTitleBar();
        setContentView(R.layout.ac_reset_password);
        Tv_TitleName = (TextView) findViewById(R.id.tv_title_name);
        Tv_TitleName.setText("重置密码");
        TextPaint tp = Tv_TitleName.getPaint();
        tp.setFakeBoldText(true);
        Rl_TitleBack = (RelativeLayout) findViewById(R.id.rl_title_back);
        Et_Password = (EditText) findViewById(R.id.et_password);
        Et_Password1 = (EditText) findViewById(R.id.et_password1);
        But_Affirm = (Button) findViewById(R.id.but_affirm);
        Et_Password.setFilters(new InputFilter[]{CheckoutUtil.filter});
        Et_Password1.setFilters(new InputFilter[]{CheckoutUtil.filter});
        Rl_TitleBack.setOnClickListener(this);
        But_Affirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mPasswordValue = Et_Password.getText().toString();
        mPassword1Value = Et_Password1.getText().toString();
        switch (v.getId()) {
            case R.id.but_affirm:
                if (mPasswordValue.equals("")) {
                    ToastUtils.showToast(ResetPassword.this, "密码不能为空");
                } else if (mPasswordValue.length() < 6) {
                    ToastUtils.showToast(ResetPassword.this, "密码不能少于六位数");
                } else if (!CheckoutUtil.isLetterDigit(mPasswordValue)) {
                    ToastUtils.showToast(ResetPassword.this, "至少包含大小写字母及数字中的两种");
                } else if (!mPasswordValue.equals(mPassword1Value)) {
                    ToastUtils.showToast(ResetPassword.this, "两次输入的密码不一致");
                } else {
                    Affirm();//确定修改
                }
                break;
            case R.id.rl_title_back:
                finish();
                break;
        }
    }

    private void Affirm() {
        final ShapeLoadingDialog shapeLoadingDialog = new ShapeLoadingDialog(ResetPassword.this);
        shapeLoadingDialog.setCancelable(false);
        shapeLoadingDialog.setLoadingText("重置中,请稍等...");
        shapeLoadingDialog.show();
        String url = HttpURLs.FORGET;
        mPhoneValue = getIntent().getStringExtra("mPhoneValue");
        mPasswordValue = Et_Password.getText().toString();
        Map<String, String> params = new HashMap<>();
        params.put("loginname", mPhoneValue);
        params.put("passdword", MD5Util.MD5(mPasswordValue, 16));
        okHttp.postRequest(url, params, new MyStringCallBack() {
            @Override
            public void onResponse(String response, int id) {
                Log.e("sssss>>>", response);
                LoginJson login = JSONObject.parseObject(response, LoginJson.class);
                String code = login.getCode();
                String dosc = login.getDesc();
                switch (code) {
                    case "200":
                        shapeLoadingDialog.dismiss();
                        ToastUtils.showToast(ResetPassword.this, "密码重置成功,请登录");
                        Intent intent = new Intent(ResetPassword.this, Login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                        startActivity(intent);
                        finish();
                        break;
                    case "400":
                        shapeLoadingDialog.dismiss();
                        ToastUtils.showToast(ResetPassword.this, dosc);
                        break;
                    case "500":
                        shapeLoadingDialog.dismiss();
                        ToastUtils.showToast(ResetPassword.this, dosc);
                        break;
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                shapeLoadingDialog.dismiss();
                Toast.makeText(ResetPassword.this, R.string.ConnectionError, Toast.LENGTH_SHORT).show();
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