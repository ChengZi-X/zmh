package com.zmh.zz.zmh.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zmh.zz.zmh.R;

import com.zmh.zz.zmh.utlis.ToastUtils;


/**
 * Created by Administrator on 2017/8/1.
 * 修改密码
 */

public class Ac_Reset_Password extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    private EditText mEt_password, mEt_password1;
    private Button mBut_affirm;
    private TextView toolbartitle;
    private ImageView toolbarback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_reste_password);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("修改密码");
        toolbarback = (ImageView) findViewById(R.id.title_back);
        mEt_password = (EditText) findViewById(R.id.et_password);
        mEt_password1 = (EditText) findViewById(R.id.et_password1);
        mBut_affirm = (Button) findViewById(R.id.but_affirm);
        mEt_password.addTextChangedListener(this);
        mEt_password1.addTextChangedListener(this);
        mBut_affirm.setOnClickListener(this);
        toolbarback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_affirm:
                SharedPreferences spout = Ac_Reset_Password.this.getSharedPreferences("userInfo", 0);
                SharedPreferences.Editor ed = spout.edit();
                ed.clear();//清除账号的缓存
                ed.commit();
                ToastUtils.showToast(Ac_Reset_Password.this, "密码修改成功,去登录");
                Intent intent = new Intent(Ac_Reset_Password.this, Ac_Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                break;
            case R.id.title_back:
                finish();
                break;
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (mEt_password.getText().toString().length() > 0 && mEt_password1.getText().toString().length() > 0) {
            mBut_affirm.setEnabled(true);
        } else {
            mBut_affirm.setEnabled(false);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

}