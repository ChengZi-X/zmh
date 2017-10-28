package com.zmh.zz.zmh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.utlis.CircleImageView1Util;

/**
 * Created by Administrator
 * 个人信息
 */

public class PersonalInformation extends BaseActivity implements View.OnClickListener {
    private RelativeLayout mExpress_address, mRl_head_portrait;
    private CircleImageView1Util mIm_head_portrait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("个人信息");
        FindViewById();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_personal_information;//任意非空布局
    }


    private void FindViewById() {
        mIm_head_portrait = (CircleImageView1Util) findViewById(R.id.im_head_portrait);
        mRl_head_portrait = (RelativeLayout) findViewById(R.id.rl_head_portrait);
        mExpress_address = (RelativeLayout) findViewById(R.id.express_address);
        mRl_head_portrait.setOnClickListener(this);
        mExpress_address.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_head_portrait:
                startActivity(new Intent(PersonalInformation.this, ImHeadPortrait.class));
                break;
            case R.id.express_address:
                startActivity(new Intent(PersonalInformation.this, NewExpressAddress.class));
                break;
        }
    }
}
