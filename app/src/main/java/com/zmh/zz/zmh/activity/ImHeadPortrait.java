package com.zmh.zz.zmh.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.utlis.ToastUtils;

/**
 * Created by Administrator
 * 选择拍照or相册
 */

public class ImHeadPortrait extends Activity implements View.OnClickListener {
    private RelativeLayout mPhotograph, mPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_im_head_portrait_dialog);
        mPhotograph = (RelativeLayout) findViewById(R.id.photograph);
        mPhoto = (RelativeLayout) findViewById(R.id.photo);
        mPhotograph.setOnClickListener(this);
        mPhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.photograph:
                ToastUtils.showToast(ImHeadPortrait.this, "拍照");
                break;
            case R.id.photo:
                ToastUtils.showToast(ImHeadPortrait.this, "相册");
                break;
        }
    }
}
