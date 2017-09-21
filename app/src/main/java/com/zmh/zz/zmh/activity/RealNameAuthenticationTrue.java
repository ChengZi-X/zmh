package com.zmh.zz.zmh.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;


/**
 * Created by Administrator
 * 实名认证_false
 */

public class RealNameAuthenticationTrue extends BaseActivity implements View.OnClickListener {
    private RelativeLayout mCertificate_photo, mEssential_information;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("实名认证");
        setBottomViewVisible(false);
        setTitleBackgroundColor(Color.parseColor("#1B82D2"));
        setLeftbtnColor(R.mipmap.arrow_left_white);
        setTitleTvColor(Color.parseColor("#FFFFFF"));
        FindViewById();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_real_name_authentication_true;
    }

    private void FindViewById() {
        mCertificate_photo = (RelativeLayout) findViewById(R.id.certificate_photo);
        mEssential_information = (RelativeLayout) findViewById(R.id.essential_information);
        mCertificate_photo.setOnClickListener(this);
        mEssential_information.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.certificate_photo:
                startActivity(new Intent(RealNameAuthenticationTrue.this, UploadingOfDocuments.class));
                break;
            case R.id.essential_information:
                startActivity(new Intent(RealNameAuthenticationTrue.this, EssentialInformation.class));
                break;
        }
    }
}
