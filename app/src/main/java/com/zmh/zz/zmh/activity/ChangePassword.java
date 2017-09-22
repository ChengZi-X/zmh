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
 * 账号与安全_密码修改
 */

public class ChangePassword extends BaseActivity implements View.OnClickListener, TextWatcher {
    private EditText mOld_password, mNew_password, mConfirm_new_password;
    private Button mBut_affirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("修改密码");
        FindViewById();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_change_password;//任意非空布局
    }

    private void FindViewById() {
        mOld_password = (EditText) findViewById(R.id.old_password);
        mNew_password = (EditText) findViewById(R.id.new_password);
        mConfirm_new_password = (EditText) findViewById(R.id.confirm_new_password);
        mBut_affirm = (Button) findViewById(R.id.but_affirm);
        mOld_password.addTextChangedListener(this);
        mNew_password.addTextChangedListener(this);
        mConfirm_new_password.addTextChangedListener(this);
        mBut_affirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_affirm:
                ToastUtils.showToast(ChangePassword.this, "密码修改成功");
                break;
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (mOld_password.getText().toString().length() > 0 && mNew_password.getText().toString().length() > 0 && mConfirm_new_password.getText().toString().length() > 0) {
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
