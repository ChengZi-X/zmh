package com.zmh.zz.zmh.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.utlis.CircleImageView2Util;


/**
 * Created by Administrator
 * 实名认证_true
 */

public class RealNameAuthenticationTrue extends BaseActivity implements View.OnClickListener {
    private RelativeLayout mCertificate_photo, mEssential_information;
    private CircleImageView2Util mIm_head_portrait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("实名认证");
        setBottomViewVisible(false);
        setTitleBackgroundColor(Color.parseColor("#1B82D2"));
        setLeftbtnColor(R.mipmap.arrow_left_white);
        setLtTitleTvColor(R.color.white);
        FindViewById();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_real_name_authentication_true;
    }

    private void FindViewById() {
        mIm_head_portrait = (CircleImageView2Util) findViewById(R.id.im_head_portrait);
        mCertificate_photo = (RelativeLayout) findViewById(R.id.certificate_photo);
        mEssential_information = (RelativeLayout) findViewById(R.id.essential_information);
        mCertificate_photo.setOnClickListener(this);
        mEssential_information.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.certificate_photo:
                startActivity(new Intent(RealNameAuthenticationTrue.this, UploadingOfDocuments.class));
                break;
            case R.id.essential_information:
                startActivity(new Intent(RealNameAuthenticationTrue.this, EssentialInformation.class));
                break;
        }
    }
}
