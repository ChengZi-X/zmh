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
 * Created by Administrator on 2017/8/2.
 * 个人信息
 */

public class Ac_Personal_Information extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout mExpress_address, mIm_head_portrait,mTitle_back;
    private TextView toolbartitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_personal_information);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("个人信息");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        mTitle_back = (RelativeLayout) findViewById(R.id.title_back);
        mIm_head_portrait = (RelativeLayout) findViewById(R.id.im_head_portrait);
        mExpress_address = (RelativeLayout) findViewById(R.id.express_address);
        mIm_head_portrait.setOnClickListener(this);
        mExpress_address.setOnClickListener(this);
        mTitle_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_head_portrait:
                startActivity(new Intent(Ac_Personal_Information.this, Ac_Im_Head_Portrait.class));
                break;
            case R.id.express_address:
                startActivity(new Intent(Ac_Personal_Information.this, Ac_New_Express_Address.class));
                break;
            case R.id.title_back:
                finish();
                break;
        }

    }
}
