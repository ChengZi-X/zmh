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
 * 注册
 */

public class Register extends AppCompatActivity implements View.OnClickListener {
    private EditText mEt_Phone, mEt_smscode;
    private TextView toolbartitle, mLog, mBut_next;
    private RelativeLayout toolbarback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransparentTitleBar();
        setContentView(R.layout.ac_register);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("注册");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        toolbarback = (RelativeLayout) findViewById(R.id.title_back);
        mBut_next = (TextView) findViewById(R.id.but_next);
        mEt_Phone = (EditText) findViewById(R.id.et_phone);
        mEt_smscode = (EditText) findViewById(R.id.et_smscode);
        mLog = (TextView) findViewById(R.id.log);
        TextPaint tp1 = mLog.getPaint();
        tp1.setFakeBoldText(true);
        toolbarback.setOnClickListener(this);
        mBut_next.setOnClickListener(this);
        mLog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_next:
                startActivity(new Intent(Register.this, RegisterPassword.class));
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