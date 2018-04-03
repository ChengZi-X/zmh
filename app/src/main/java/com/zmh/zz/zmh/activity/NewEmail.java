package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
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
 * 账号与安全_改绑新邮箱
 */

public class NewEmail extends BaseActivity implements View.OnClickListener {
    private Button But_binding;
    private EditText Et_Email;
    private OkHttpUtil okHttp = new OkHttpUtil();
    private String mEmailValue, UserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("改绑邮箱");
        FindViewById();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_new_email;
    }

    private void FindViewById() {
        Et_Email = (EditText) findViewById(R.id.et_email);
        But_binding = (Button) findViewById(R.id.but_binding);
        Et_Email.setFilters(new InputFilter[]{RegularUtil.filter});
        But_binding.setOnClickListener(this);
        Et_Email.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_binding:
                if (!RegularUtil.isQQEmail(mEmailValue)) {
                    ToastUtils.showToast(NewEmail.this, "QQ邮箱格式不正确");
                } else {
                    BinDing();//绑定
                }
                break;
        }
    }

    private void BinDing() {
        mEmailValue = Et_Email.getText().toString();
        UserName = (String) SharedPreferencesUtil.getParam(NewEmail.this, "UserName", "");
        final ShapeLoadingDialog shapeLoadingDialog = new ShapeLoadingDialog(NewEmail.this);
        shapeLoadingDialog.setCancelable(false);
        shapeLoadingDialog.setLoadingText("绑定中,请稍等...");
        shapeLoadingDialog.show();
        String url = HttpURLs.MODIFYEMAIL;
        Map<String, String> params = new HashMap<>();
        params.put("loginname", UserName);
        params.put("newEmail", mEmailValue);
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
                        ToastUtils.showToast(NewEmail.this, "重新绑定成功");
                        break;
                    case 400:
                        shapeLoadingDialog.dismiss();
                        ToastUtils.showToast(NewEmail.this, desc);
                        break;
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                shapeLoadingDialog.dismiss();
                Toast.makeText(NewEmail.this, R.string.ConnectionError, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void afterTextChanged(Editable editable) {
        mEmailValue = Et_Email.getText().toString();
        if (mEmailValue.equals("")) {
            But_binding.setEnabled(false);
        } else {
            But_binding.setEnabled(true);
        }
    }
}
