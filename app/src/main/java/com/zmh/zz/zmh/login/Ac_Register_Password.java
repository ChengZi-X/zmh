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
 * Created by Administrator on 2017/7/31.
 * 注册
 */

public class Ac_Register_Password extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    private EditText mEt_Password, mEt_Password1;
    private Button mBut_register;
    private TextView toolbartitle;
    private ImageView toolbarback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_registe_password);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("注册");
        toolbarback = (ImageView) findViewById(R.id.title_back);
        mEt_Password = (EditText) findViewById(R.id.et_password);
        mEt_Password1 = (EditText) findViewById(R.id.et_password1);
        mBut_register = (Button) findViewById(R.id.but_register);
        mEt_Password.addTextChangedListener(this);
        mEt_Password1.addTextChangedListener(this);
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

    @Override
    public void afterTextChanged(Editable editable) {
        if (mEt_Password.getText().toString().length() > 0 && mEt_Password1.getText().toString().length() > 0) {
            mBut_register.setEnabled(true);
        } else {
            mBut_register.setEnabled(false);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }


}
