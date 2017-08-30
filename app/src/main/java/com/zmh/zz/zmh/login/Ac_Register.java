package com.zmh.zz.zmh.login;

import android.content.Intent;
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


/**
 * Created by Administrator on 2017/7/31.
 * 注册
 */

public class Ac_Register extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    private Button mBut_next;
    private EditText mEt_Phone, mEt_smscode;
    private TextView toolbartitle, mLog;
    private ImageView toolbarback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_register);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("注册");
        toolbarback = (ImageView) findViewById(R.id.title_back);
        mBut_next = (Button) findViewById(R.id.but_next);
        mEt_Phone = (EditText) findViewById(R.id.et_phone);
        mEt_smscode = (EditText) findViewById(R.id.et_smscode);
        mLog = (TextView) findViewById(R.id.log);
        mEt_Phone.addTextChangedListener(this);
        toolbarback.setOnClickListener(this);
        mBut_next.setOnClickListener(this);
        mLog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_next:
                startActivity(new Intent(Ac_Register.this, Ac_Register_Password.class));
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

    @Override
    public void afterTextChanged(Editable editable) {
        if (mEt_Phone.getText().toString().length() > 0) {
            mBut_next.setEnabled(true);
        } else {
            mBut_next.setEnabled(false);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }
}