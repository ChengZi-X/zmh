package com.zmh.zz.zmh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.LoadingDialog.ShapeLoadingDialog;
import com.zmh.zz.zmh.MainActivity;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.httpurls.HttpURLs;
import com.zmh.zz.zmh.modeljson.UserJson;
import com.zmh.zz.zmh.utils.MyStringCallBack;
import com.zmh.zz.zmh.utils.OkHttpUtil;
import com.zmh.zz.zmh.utils.RegularUtil;
import com.zmh.zz.zmh.utils.SharedPreferencesUtil;
import com.zmh.zz.zmh.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by Sun-PC on 2018/4/4  0004.
 * 绑定验证邮箱
 */

public class BindEmail extends BaseActivity implements View.OnClickListener {
    private Button But_GetCode, But_Confirm;
    private EditText Et_Email, Et_Code;
    private String mEmilValue, mCodeValue;
    private TimeCount time;
    private OkHttpUtil okHttp = new OkHttpUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("绑定邮箱");
        setLeftBtnVisible(false);
        time = new TimeCount(60000, 1000);// 构造CountDownTimer对象
        Et_Email = (EditText) findViewById(R.id.et_email);
        Et_Code = (EditText) findViewById(R.id.et_code);
        But_GetCode = (Button) findViewById(R.id.but_get_code);
        But_Confirm = (Button) findViewById(R.id.but_confirm);
        Et_Email.setFilters(new InputFilter[]{RegularUtil.filter});
        Et_Code.setFilters(new InputFilter[]{RegularUtil.filter});
        But_GetCode.setOnClickListener(this);
        But_Confirm.setOnClickListener(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_bind_email;
    }

    @Override
    public void onClick(View view) {
        mEmilValue = Et_Email.getText().toString();
        mCodeValue = Et_Code.getText().toString();
        switch (view.getId()) {
            case R.id.but_get_code:
                if (TextUtils.isEmpty(mEmilValue)) {
                    ToastUtils.showToast(BindEmail.this, "邮箱不能为空");
                    return;
                } else if (!RegularUtil.isQQEmail(mEmilValue)) {
                    ToastUtils.showToast(BindEmail.this, "邮箱格式不正确");
                    return;
                } else {
                    time.start();// 开始计时
                }
                GetCode();
                break;
            case R.id.but_confirm:
                if (TextUtils.isEmpty(mEmilValue)) {
                    ToastUtils.showToast(BindEmail.this, "邮箱不能为空");
                } else if (!RegularUtil.isQQEmail(mEmilValue)) {
                    ToastUtils.showToast(BindEmail.this, "邮箱格式不正确");
                } else if (TextUtils.isEmpty(mCodeValue)) {
                    ToastUtils.showToast(BindEmail.this, "验证码不能为空");
                } else {
                    Confirm();
                }
                break;
        }
    }

    private void GetCode() {
        final ShapeLoadingDialog shapeLoadingDialog = new ShapeLoadingDialog(BindEmail.this);
        shapeLoadingDialog.setCancelable(false);
        shapeLoadingDialog.setLoadingText("获取中,请稍等...");
        shapeLoadingDialog.show();
        mEmilValue = Et_Email.getText().toString();
        String url = HttpURLs.GETEMAILUPDATECODE;
        Map<String, String> params = new HashMap<>();
        params.put("toEmail", mEmilValue);
        okHttp.postRequest(url, params, new MyStringCallBack() {
            @Override
            public void onResponse(String response, int id) {
                shapeLoadingDialog.dismiss();
                Log.e("sssss>>>", response);
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                shapeLoadingDialog.dismiss();
                Toast.makeText(BindEmail.this, R.string.ConnectionError, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Confirm() {
        final ShapeLoadingDialog shapeLoadingDialog = new ShapeLoadingDialog(BindEmail.this);
        shapeLoadingDialog.setCancelable(false);
        shapeLoadingDialog.setLoadingText("绑定中,请稍等...");
        shapeLoadingDialog.show();
        mEmilValue = Et_Email.getText().toString();
        mCodeValue = Et_Code.getText().toString();
        String Token = (String) SharedPreferencesUtil.getParam(BindEmail.this, "Token", "");
        String url = HttpURLs.ADDEMIAL;
        Map<String, String> params = new HashMap<>();
        params.put("token", Token);
        params.put("newEmail", mEmilValue);
        params.put("toCode", mCodeValue);
        okHttp.postRequest(url, params, new MyStringCallBack() {
            @Override
            public void onResponse(String response, int id) {
                shapeLoadingDialog.dismiss();
                Log.e("sssss>>>", response);
                UserJson user = JSONObject.parseObject(response, UserJson.class);
                int code = user.getCode();
                String desc = user.getDesc();
                switch (code) {
                    case 200:
                        shapeLoadingDialog.dismiss();
                        startActivity(new Intent(BindEmail.this, MainActivity.class));
                        finish();
                        break;
                    case 400:
                        shapeLoadingDialog.dismiss();
                        ToastUtils.showToast(BindEmail.this, desc);
                        break;
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                shapeLoadingDialog.dismiss();
                Toast.makeText(BindEmail.this, R.string.ConnectionError, Toast.LENGTH_SHORT).show();
            }
        });
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
