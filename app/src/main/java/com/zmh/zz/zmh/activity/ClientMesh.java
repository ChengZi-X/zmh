package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.view.View;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;

/**
 * Created by Administrator
 * 客户网络
 */

public class ClientMesh extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("客户网络");
        FindViewById();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_client_mesh;
    }

    private void FindViewById() {
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
}
