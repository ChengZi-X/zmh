package com.zmh.zz.zmh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;


/**
 * Created by Administrator
 * 账号与安全
 */

public class AccountSecurity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout mPhone_number, mMailbox, mChange_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("账号与安全");
        FindViewById();
    }
    @Override
    protected int getContentView() {
        return R.layout.ac_account_security;//任意非空布局
    }

    private void FindViewById() {
        mPhone_number = (RelativeLayout) findViewById(R.id.phone_number);
        mMailbox = (RelativeLayout) findViewById(R.id.mailbox);
        mChange_password = (RelativeLayout) findViewById(R.id.change_password);
        mPhone_number.setOnClickListener(this);
        mMailbox.setOnClickListener(this);
        mChange_password.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.phone_number:
                startActivity(new Intent(AccountSecurity.this, VerifyPhoneNumberPhone.class));
                break;
            case R.id.mailbox:
                startActivity(new Intent(AccountSecurity.this, VerifyPhoneNumberMailbox.class));
                break;
            case R.id.change_password:
                startActivity(new Intent(AccountSecurity.this, ChangePassword.class));
                break;
        }
    }
}
