package com.zmh.zz.zmh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.adapter.AddressAdapter;
import com.zmh.zz.zmh.modelinfo.AddressInfo;
import com.zmh.zz.zmh.utlis.CustomSwipeListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 * 快件地址列表
 */

public class NewExpressAddress extends BaseActivity implements View.OnClickListener {
    private Button mBut_new_address;
    private CustomSwipeListView mLv_address;
    private AddressAdapter addressAdapter;
    private List<AddressInfo> addressList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("快件地址");
        FindViewById();
        InitData();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_new_express_address;
    }

    private void FindViewById() {
        mBut_new_address = (Button) findViewById(R.id.but_new_address);
        //字体加粗
        TextPaint tp = mBut_new_address.getPaint();
        tp.setFakeBoldText(true);
        mBut_new_address.setOnClickListener(this);
    }

    private void InitData() {
        mLv_address = (CustomSwipeListView) findViewById(R.id.lv_address);
        addressList = new ArrayList<>();
        addressAdapter = new AddressAdapter(addressList, NewExpressAddress.this);
        mLv_address.setAdapter(addressAdapter);
        addressAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_new_address:
                startActivity(new Intent(NewExpressAddress.this, AddAddress.class));
                break;
        }
    }
}