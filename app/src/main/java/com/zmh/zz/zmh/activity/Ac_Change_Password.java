package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.utlis.ToastUtils;

/**
 * Created by Administrator on 2017/8/4.
 * 账号与安全_密码修改
 */

public class Ac_Change_Password extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    private EditText mOld_password, mNew_password, mConfirm_new_password;
    private Button mBut_affirm;
    private TextView toolbartitle;
    private RelativeLayout mTitle_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_change_password);
        mOld_password = (EditText) findViewById(R.id.old_password);
        mNew_password = (EditText) findViewById(R.id.new_password);
        mConfirm_new_password = (EditText) findViewById(R.id.confirm_new_password);
        mBut_affirm = (Button) findViewById(R.id.but_affirm);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("修改密码");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        mOld_password.addTextChangedListener(this);
        mNew_password.addTextChangedListener(this);
        mConfirm_new_password.addTextChangedListener(this);
        mBut_affirm.setOnClickListener(this);
        mTitle_back = (RelativeLayout) findViewById(R.id.title_back);
        mTitle_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_affirm:
                ToastUtils.showToast(Ac_Change_Password.this, "密码修改成功");
                break;
            case R.id.title_back:
                finish();
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
