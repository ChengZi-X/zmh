package com.zmh.zz.zmh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.adapter.AddressAdapter;
import com.zmh.zz.zmh.modelinfo.AddressInfo;
import com.zmh.zz.zmh.utlis.CustomSwipeListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 * 快件地址列表
 */

public class Ac_New_Express_Address extends AppCompatActivity implements View.OnClickListener {
    private Button mBut_new_address;
    private CustomSwipeListView mLv_address;
    private AddressAdapter addressAdapter;
    private List<AddressInfo> addressList;
    private RelativeLayout mTitle_back;
    private TextView toolbartitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_new_express_address);
        mBut_new_address = (Button) findViewById(R.id.but_new_address);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("快件地址");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        TextPaint tp1 = mBut_new_address.getPaint();
        tp1.setFakeBoldText(true);
        mTitle_back = (RelativeLayout) findViewById(R.id.title_back);
        mBut_new_address.setOnClickListener(this);
        mTitle_back.setOnClickListener(this);
        InitData();
    }

    private void InitData() {
        mLv_address = (CustomSwipeListView) findViewById(R.id.lv_address);
        addressList = new ArrayList<>();
        addressAdapter = new AddressAdapter(addressList, Ac_New_Express_Address.this);
        mLv_address.setAdapter(addressAdapter);
        addressAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_new_address:
                startActivity(new Intent(Ac_New_Express_Address.this, Ac_Add_Address.class));
                break;
            case R.id.title_back:
                finish();
                break;
        }
    }
}