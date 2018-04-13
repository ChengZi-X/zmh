package com.zmh.zz.zmh.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.utils.CircleImageView2Util;
import com.zmh.zz.zmh.utils.SharedPreferencesUtil;


/**
 * Created by Administrator
 * 实名认证_true
 */

public class RealNameAuthenticationTrue extends BaseActivity implements View.OnClickListener {
    private RelativeLayout mEssential_information;
    private CircleImageView2Util mIm_head_portrait;
    private TextView Tv_Name, Tv_Id_Number;
    private String NameVale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("实名认证");
        setBottomViewVisible(false);
        setTitleBackgroundColor(Color.parseColor("#1B82D2"));
        setLeftbtnColor(R.mipmap.arrow_left_white);
        setLtTitleTvColor(R.color.white);
        FindViewById();
        initData();
    }


    @Override
    protected int getContentView() {
        return R.layout.ac_real_name_authentication_true;
    }

    private void FindViewById() {
        Tv_Name = (TextView) findViewById(R.id.tv_name);
        Tv_Id_Number = (TextView) findViewById(R.id.tv_id_number);
        mIm_head_portrait = (CircleImageView2Util) findViewById(R.id.im_head_portrait);
        mEssential_information = (RelativeLayout) findViewById(R.id.essential_information);
        mEssential_information.setOnClickListener(this);
    }

    private void initData() {
        NameVale = (String) SharedPreferencesUtil.getParam(RealNameAuthenticationTrue.this, "name", "");
        Tv_Name.setText(NameVale);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.essential_information:
                startActivity(new Intent(RealNameAuthenticationTrue.this, EssentialInformation.class));
                break;
        }
    }
}
