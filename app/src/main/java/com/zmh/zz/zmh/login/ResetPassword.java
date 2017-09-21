package com.zmh.zz.zmh.login;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.zmh.zz.zmh.utlis.ToastUtils;


/**
 * Created by Administrator
 * 修改密码
 */

public class ResetPassword extends AppCompatActivity implements View.OnClickListener {
    private EditText mEt_password, mEt_password1;
    private TextView toolbartitle, mBut_affirm;
    private RelativeLayout toolbarback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Transparent_title_bar();
        setContentView(R.layout.ac_reste_password);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("修改密码");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        toolbarback = (RelativeLayout) findViewById(R.id.title_back);
        mEt_password = (EditText) findViewById(R.id.et_password);
        mEt_password1 = (EditText) findViewById(R.id.et_password1);
        mBut_affirm = (TextView) findViewById(R.id.but_affirm);
        mBut_affirm.setOnClickListener(this);
        toolbarback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_affirm:
                SharedPreferences spout = ResetPassword.this.getSharedPreferences("userInfo", 0);
                SharedPreferences.Editor ed = spout.edit();
                ed.clear();//清除账号的缓存
                ed.commit();
                ToastUtils.showToast(ResetPassword.this, "密码修改成功,去登录");
                Intent intent = new Intent(ResetPassword.this, Login.class);
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