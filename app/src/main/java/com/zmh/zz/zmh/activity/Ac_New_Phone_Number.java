package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.utlis.ToastUtils;

/**
 * Created by Administrator
 * 账号与安全_改绑新手机
 */

public class Ac_New_Phone_Number extends BaseActivity implements View.OnClickListener, TextWatcher {
    private EditText mPhone_Number, mVerification_Code;
    private Button mBut_binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("改绑手机号");
        FindViewById();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_new_phone_number;
    }

    private void FindViewById() {
        mPhone_Number = (EditText) findViewById(R.id.phone_number);
        mVerification_Code = (EditText) findViewById(R.id.verification_code);
        mBut_binding = (Button) findViewById(R.id.but_binding);
        mPhone_Number.addTextChangedListener(this);
        mVerification_Code.addTextChangedListener(this);
        mBut_binding.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_binding:
                ToastUtils.showToast(Ac_New_Phone_Number.this, "重新绑定成功");
                break;
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (mPhone_Number.getText().toString().length() > 0 && mVerification_Code.getText().toString().length() > 0) {
            mBut_binding.setEnabled(true);
        } else {
            mBut_binding.setEnabled(false);
        }

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }


}
