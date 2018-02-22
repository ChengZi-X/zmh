package com.zmh.zz.zmh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.paypassword.PayPasswordActivity;

/**
 * Created by Administrator
 * 账户提现
 */

public class AccountOut extends BaseActivity implements View.OnClickListener {
    private TextView Tv_Bankcard;
    private Button But_Withdraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("账户提现");
        FindViewById();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_account_out;
    }

    private void FindViewById() {
        Tv_Bankcard = (TextView) findViewById(R.id.tv_bankcard);
        But_Withdraw = (Button) findViewById(R.id.but_withdraw);
        But_Withdraw.setOnClickListener(this);
        Tv_Bankcard.setText("中国银行(0086)");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_withdraw:
                Intent intent = new Intent(AccountOut.this, PayPasswordActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.keyboard_show, R.anim.keyboard_hide);
                //ToastUtils.showToast(AccountOut.this, "确认提现");
                break;
        }
    }
}
