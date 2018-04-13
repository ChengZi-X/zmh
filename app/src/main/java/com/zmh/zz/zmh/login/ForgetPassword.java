package com.zmh.zz.zmh.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.zmh.zz.zmh.utils.RegularUtil;
import com.zmh.zz.zmh.utils.MyStringCallBack;
import com.zmh.zz.zmh.utils.OkHttpUtil;
import com.zmh.zz.zmh.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;


/**
 * Created by Administrator
 * 修改密码
 */

public class ForgetPassword extends AppCompatActivity implements View.OnClickListener {
    private EditText Et_Phone, Et_Code;
    private TextView Tv_TitleName;
    private RelativeLayout Rl_TitleBack;
    private Button But_GetCode, But_Next, But_Login;
    private OkHttpUtil okHttp = new OkHttpUtil();
    private String mPhoneValue, mCodeValue;
    private TimeCount time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransparentTitleBar();
        setContentView(R.layout.ac_forget_password);
        time = new TimeCount(60000, 1000);// 构造CountDownTimer对象
        Tv_TitleName = (TextView) findViewById(R.id.tv_title_name);
        Tv_TitleName.setText("忘记密码");
        TextPaint tp = Tv_TitleName.getPaint();
        tp.setFakeBoldText(true);
        Rl_TitleBack = (RelativeLayout) findViewById(R.id.rl_title_back);
        Et_Phone = (EditText) findViewById(R.id.et_phone);
        Et_Code = (EditText) findViewById(R.id.et_code);
        But_GetCode = (Button) findViewById(R.id.but_get_code);
        But_Next = (Button) findViewById(R.id.but_next);
        But_Login = (Button) findViewById(R.id.but_login);
        TextPaint tp1 = But_Login.getPaint();
        Et_Phone.setFilters(new InputFilter[]{RegularUtil.filter});
        Et_Code.setFilters(new InputFilter[]{RegularUtil.filter});
        tp1.setFakeBoldText(true);
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
                    ToastUtils.showToast(ForgetPassword.this, "手机号不能为空");
                    return;
                } else if (!RegularUtil.isMobileNO(mPhoneValue)) {
                    ToastUtils.showToast(ForgetPassword.this, "手机号格式不正确");
                    return;
                } else {
                    time.start();// 开始计时
                }
                GetCode();//获取验证码
                break;
            case R.id.but_next:
                if (mPhoneValue.equals("")) {
                    ToastUtils.showToast(ForgetPassword.this, "手机号不能为空");
                } else if (!RegularUtil.isMobileNO(mPhoneValue)) {
                    ToastUtils.showToast(ForgetPassword.this, "手机号格式不正确");
                } else if (mCodeValue.equals("")) {
                    ToastUtils.showToast(ForgetPassword.this, "验证码不能为空");
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
        final ShapeLoadingDialog shapeLoadingDialog = new ShapeLoadingDialog(ForgetPassword.this);
        shapeLoadingDialog.setCancelable(false);
        shapeLoadingDialog.setLoadingText("获取中,请稍等...");
        shapeLoadingDialog.show();
        mPhoneValue = Et_Phone.getText().toString();
        String url = HttpURLs.GETFCODE;
        Map<String, String> params = new HashMap<>();
        params.put("mobile", mPhoneValue);
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
                        ToastUtils.showToast(ForgetPassword.this, desc);
                        break;
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                shapeLoadingDialog.dismiss();
                Toast.makeText(ForgetPassword.this, R.string.ConnectionError, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Next() {
        final ShapeLoadingDialog shapeLoadingDialog = new ShapeLoadingDialog(ForgetPassword.this);
        shapeLoadingDialog.setCancelable(false);
        shapeLoadingDialog.setLoadingText("校验中,请稍等...");
        shapeLoadingDialog.show();
        mPhoneValue = Et_Phone.getText().toString();
        mCodeValue = Et_Code.getText().toString();
        String url = HttpURLs.FORGETCODE;
        Map<String, String> params = new HashMap<>();
        params.put("mobile", mPhoneValue);
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
                        Intent intent = new Intent(new Intent(ForgetPassword.this, ResetPassword.class));
                        intent.putExtra("mPhoneValue", mPhoneValue);
                        startActivity(intent);
                        finish();
                        break;
                    case 400:
                        shapeLoadingDialog.dismiss();
                        ToastUtils.showToast(ForgetPassword.this, desc);
                        break;
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                shapeLoadingDialog.dismiss();
                Toast.makeText(ForgetPassword.this, R.string.ConnectionError, Toast.LENGTH_SHORT).show();
            }
        });


    }

    /**
     * 透明标题栏
     */
    private void TransparentTitleBar() {
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

    /**
     * 定义一个倒计时的内部类
     */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发
            But_GetCode.setText("重新验证");
            But_GetCode.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            But_GetCode.setClickable(false);
            But_GetCode.setText(millisUntilFinished / 1000 + "秒");
        }
    }
}
