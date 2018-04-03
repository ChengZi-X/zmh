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
    private RelativeLayout Rl_PhoneNumber, Rl_Email, Rl_ChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("账号与安全");
        FindViewById();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_account_security;
    }

    private void FindViewById() {
        Rl_PhoneNumber = (RelativeLayout) findViewById(R.id.rl_phonenumber);
        Rl_Email = (RelativeLayout) findViewById(R.id.rl_email);
        Rl_ChangePassword = (RelativeLayout) findViewById(R.id.rl_changepassword);
        Rl_PhoneNumber.setOnClickListener(this);
        Rl_Email.setOnClickListener(this);
        Rl_ChangePassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_phonenumber:
                startActivity(new Intent(AccountSecurity.this, VerifyPhoneNumberPhone.class));
                break;
            case R.id.rl_email:
                startActivity(new Intent(AccountSecurity.this, VerifyPhoneNumberEmail.class));
                break;
            case R.id.rl_changepassword:
                startActivity(new Intent(AccountSecurity.this, ChangePassword.class));
                break;
        }
    }
}
