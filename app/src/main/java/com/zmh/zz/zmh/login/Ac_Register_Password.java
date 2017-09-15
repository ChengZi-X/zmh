package com.zmh.zz.zmh.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.utlis.ToastUtils;


/**
 * Created by Administrator
 * 注册
 */

public class Ac_Register_Password extends AppCompatActivity implements View.OnClickListener {
    private EditText mEt_Password, mEt_Password1, mEt_mailbox;
    private TextView toolbartitle, mBut_register;
    private RelativeLayout toolbarback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Transparent_title_bar();
        setContentView(R.layout.ac_registe_password);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("设置登录密码");
        toolbarback = (RelativeLayout) findViewById(R.id.title_back);
        mEt_Password = (EditText) findViewById(R.id.et_password);
        mEt_Password1 = (EditText) findViewById(R.id.et_password1);
        mEt_mailbox = (EditText) findViewById(R.id.et_mailbox);
        mBut_register = (TextView) findViewById(R.id.but_register);
        mBut_register.setOnClickListener(this);
        toolbarback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_register:
                SharedPreferences spout = Ac_Register_Password.this.getSharedPreferences("userInfo", 0);
                SharedPreferences.Editor ed = spout.edit();
                ed.clear();//清除账号的缓存
                ed.commit();
                ToastUtils.showToast(Ac_Register_Password.this, "注册成功,请登录");
                Intent intent = new Intent(Ac_Register_Password.this, Ac_Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                break;
            case R.id.title_back:
                finish();
                break;
        }
    }

    /**
     * 透明标题栏
     */
    private void Transparent_title_bar() {
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
