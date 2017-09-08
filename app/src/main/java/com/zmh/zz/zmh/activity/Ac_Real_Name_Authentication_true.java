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
 * Created by Administrator on 2017/8/8.
 * 实名认证_false
 */

public class Ac_Real_Name_Authentication_true extends AppCompatActivity implements View.OnClickListener {
    private TextView toolbartitle;
    private RelativeLayout mTitle_back, mCertificate_photo, mEssential_information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_real_name_authentication_true);
        mTitle_back = (RelativeLayout) findViewById(R.id.title_back);
        mTitle_back.setOnClickListener(this);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("实名认证");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        mCertificate_photo = (RelativeLayout) findViewById(R.id.certificate_photo);
        mEssential_information = (RelativeLayout) findViewById(R.id.essential_information);
        mCertificate_photo.setOnClickListener(this);
        mEssential_information.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.certificate_photo:
                startActivity(new Intent(Ac_Real_Name_Authentication_true.this, Ac_Uploading_Of_Documents.class));
                break;
            case R.id.essential_information:
                startActivity(new Intent(Ac_Real_Name_Authentication_true.this, Ac_Essential_Information.class));
                break;
        }
    }
}
