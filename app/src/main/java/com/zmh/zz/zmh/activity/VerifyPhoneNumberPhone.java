package com.zmh.zz.zmh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;

/**
 * Created by Administrator
 * 账号与安全_手机号验证
 */

public class VerifyPhoneNumberPhone extends BaseActivity implements View.OnClickListener, TextWatcher {
    private EditText mVerification_code;
    private Button mBut_next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("验证身份");
        FindViewById();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_verify_phone_number_phone;
    }

    private void FindViewById() {
        mVerification_code = (EditText) findViewById(R.id.verification_code);
        mBut_next = (Button) findViewById(R.id.but_next);
        mVerification_code.addTextChangedListener(this);
        mBut_next.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_next:
                startActivity(new Intent(VerifyPhoneNumberPhone.this, NewPhoneNumber.class));
                finish();
                break;
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (mVerification_code.getText().toString().length() > 0) {
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
