package com.zmh.zz.zmh.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.R;


/**
 * Created by Administrator
 * 修改密码
 */

public class ForgetPassword extends AppCompatActivity implements View.OnClickListener {
    private EditText mEt_phone, mEt_smscode;
    private TextView toolbartitle, mBut_next, mLog;
    private RelativeLayout toolbarback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Transparent_title_bar();
        setContentView(R.layout.ac_forget_password);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("忘记密码");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        toolbarback = (RelativeLayout) findViewById(R.id.title_back);
        mBut_next = (TextView) findViewById(R.id.but_next);
        mEt_phone = (EditText) findViewById(R.id.et_phone);
        mLog = (TextView) findViewById(R.id.log);
        TextPaint tp1 = mLog.getPaint();
        tp1.setFakeBoldText(true);
        mEt_smscode = (EditText) findViewById(R.id.et_smscode);
        mBut_next.setOnClickListener(this);
        toolbarback.setOnClickListener(this);
        mLog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_next:
                startActivity(new Intent(ForgetPassword.this, ResetPassword.class));
                finish();
                break;
            case R.id.title_back:
                finish();
                break;
            case R.id.log:
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
