package com.zmh.zz.zmh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.utlis.ToastUtils;

/**
 * Created by Administrator
 * 上传证件照
 */

public class UploadingOfDocuments extends BaseActivity implements View.OnClickListener {
    private RelativeLayout mFront, mVerso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("证件照片");
        setRtTitle("提交");
        setRightBtnVisible(true);
        FindViewById();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_uploading_of_documents;
    }

    private void FindViewById() {
        mFront = (RelativeLayout) findViewById(R.id.front);
        mVerso = (RelativeLayout) findViewById(R.id.verso);
        mFront.setOnClickListener(this);
        mVerso.setOnClickListener(this);
    }

    //右键点击
    @Override
    protected void onClickRight() {
        ToastUtils.showToast(UploadingOfDocuments.this, "提交");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.front:
                startActivity(new Intent(UploadingOfDocuments.this, ImHeadPortrait.class));
                break;
            case R.id.verso:
                startActivity(new Intent(UploadingOfDocuments.this, ImHeadPortrait.class));
                break;
        }

    }
}
