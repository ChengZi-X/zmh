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
 * Created by Administrator on 2017/8/1.
 * 修改密码
 */

public class Ac_Forget_Password extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    private Button mBut_next;
    private EditText mEt_phone;
    private TextView toolbartitle,mEt_smscode;
    private ImageView toolbarback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_forget_password);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("修改密码");
        toolbarback = (ImageView) findViewById(R.id.title_back);
        mBut_next = (Button) findViewById(R.id.but_next);
        mEt_phone = (EditText) findViewById(R.id.et_phone);
        mEt_smscode = (EditText) findViewById(R.id.et_smscode);
        mEt_phone.addTextChangedListener(this);
        mBut_next.setOnClickListener(this);
        toolbarback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_next:
                startActivity(new Intent(Ac_Forget_Password.this, Ac_Reset_Password.class));
                finish();
                break;
            case R.id.title_back:
                finish();
                break;
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (mEt_phone.getText().toString().length() > 0) {
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
