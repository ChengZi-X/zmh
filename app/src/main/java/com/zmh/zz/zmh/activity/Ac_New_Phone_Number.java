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
 * 账号与安全_改绑新手机
 */

public class Ac_New_Phone_Number extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    private EditText mPhone_Number, mVerification_Code;
    private Button mBut_binding;
    private TextView toolbartitle;
    private RelativeLayout mTitle_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_new_phone_number);
        mPhone_Number = (EditText) findViewById(R.id.phone_number);
        mVerification_Code = (EditText) findViewById(R.id.verification_code);
        mBut_binding = (Button) findViewById(R.id.but_binding);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("改绑手机号");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        mPhone_Number.addTextChangedListener(this);
        mVerification_Code.addTextChangedListener(this);
        mBut_binding.setOnClickListener(this);
        mTitle_back = (RelativeLayout) findViewById(R.id.title_back);
        mTitle_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_binding:
                ToastUtils.showToast(Ac_New_Phone_Number.this, "重新绑定成功");
                break;
            case R.id.title_back:
                finish();
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
