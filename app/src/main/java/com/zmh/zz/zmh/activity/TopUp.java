package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.utlis.ToastUtils;

/**
 * Created by Administrator
 * 账户充值
 */

public class TopUp extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private RadioGroup Rg_Pay;
    private RadioButton Rb_Alipay, Rb_Wechat;
    private Button But_Next;
    private String mPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("账户充值");
        FindViewById();

    }

    @Override
    protected int getContentView() {
        return R.layout.ac_top_up;
    }

    private void FindViewById() {
        Rg_Pay = (RadioGroup) findViewById(R.id.rg_pay);
        Rb_Alipay = (RadioButton) findViewById(R.id.rb_alipay);
        Rb_Wechat = (RadioButton) findViewById(R.id.rb_wechat);
        But_Next = (Button) findViewById(R.id.but_next);
        Rg_Pay.setOnCheckedChangeListener(this);
        But_Next.setOnClickListener(this);
        Rb_Wechat.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (Rb_Alipay.getId() == checkedId) {
            mPay = "1";
        }
        if (Rb_Wechat.getId() == checkedId) {
            mPay = "2";
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_next:
                if (mPay.equals("1")) {
                    ToastUtils.showToast(TopUp.this, "支付宝");
                } else if (mPay.equals("2")) {
                    ToastUtils.showToast(TopUp.this, "微信");
                }
                break;
        }
    }
}