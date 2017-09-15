package com.zmh.zz.zmh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.utlis.ToastUtils;

/**
 * Created by Administrator
 * 上传证件照
 */

public class Ac_Uploading_Of_Documents extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout mFront, mVerso,mTitle_back;
    private TextView toolbartitle, mTitle_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_uploading_of_documents);
        mTitle_back = (RelativeLayout) findViewById(R.id.title_back);
        mTitle_back.setOnClickListener(this);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("证件照片");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        mTitle_submit = (TextView) findViewById(R.id.title_submit);
        mTitle_submit.setText("提交");
        TextPaint tp1 = mTitle_submit.getPaint();
        tp1.setFakeBoldText(true);
        mTitle_submit.setOnClickListener(this);
        mFront = (RelativeLayout) findViewById(R.id.front);
        mVerso = (RelativeLayout) findViewById(R.id.verso);
        mFront.setOnClickListener(this);
        mVerso.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.front:
                startActivity(new Intent(Ac_Uploading_Of_Documents.this, Ac_Im_Head_Portrait.class));
                break;
            case R.id.verso:
                startActivity(new Intent(Ac_Uploading_Of_Documents.this, Ac_Im_Head_Portrait.class));
                break;
            case R.id.title_back:
                finish();
                break;
            case R.id.title_submit:
                ToastUtils.showToast(Ac_Uploading_Of_Documents.this, "提交");
                break;
        }

    }
}
