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
 * 账号与安全_改绑新邮箱
 */

public class Ac_New_Mailbox extends BaseActivity implements View.OnClickListener, TextWatcher {
    private Button mBut_binding;
    private EditText mMailbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("改绑邮箱");
        FindViewById();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_new_mailbox;
    }

    private void FindViewById() {
        mBut_binding = (Button) findViewById(R.id.but_binding);
        mMailbox = (EditText) findViewById(R.id.mailbox);
        mBut_binding.setOnClickListener(this);
        mMailbox.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_binding:
                ToastUtils.showToast(Ac_New_Mailbox.this, "重新绑定成功");
                break;
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (mMailbox.getText().toString().length() > 0) {
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
