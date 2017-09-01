package com.zmh.zz.zmh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.R;


/**
 * Created by Administrator on 2017/8/4.
 * 账号与安全
 */

public class Ac_Account_Security extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout mPhone_number, mMailbox, mChange_password;
    private TextView toolbartitle;
    private RelativeLayout mTitle_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_account_security);
        mPhone_number = (RelativeLayout) findViewById(R.id.phone_number);
        mMailbox = (RelativeLayout) findViewById(R.id.mailbox);
        mChange_password = (RelativeLayout) findViewById(R.id.change_password);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("账号与安全");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        mPhone_number.setOnClickListener(this);
        mMailbox.setOnClickListener(this);
        mChange_password.setOnClickListener(this);
        mTitle_back = (RelativeLayout) findViewById(R.id.title_back);
        mTitle_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.phone_number:
                startActivity(new Intent(Ac_Account_Security.this, Ac_Verify_Phone_Number_Phone.class));
                break;
            case R.id.mailbox:
                startActivity(new Intent(Ac_Account_Security.this, Ac_Verify_Phone_Number_Mailbox.class));
                break;
            case R.id.change_password:
                startActivity(new Intent(Ac_Account_Security.this, Ac_Change_Password.class));
                break;
            case R.id.title_back:
                finish();
                break;
        }
    }
}
