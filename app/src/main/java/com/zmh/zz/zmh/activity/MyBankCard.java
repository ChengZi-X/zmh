package com.zmh.zz.zmh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.adapter.MyBankCardAdapter;
import com.zmh.zz.zmh.modelinfo.MyBankCardInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 * 我的银行卡列表显示
 */

public class MyBankCard extends BaseActivity implements View.OnClickListener {
    private RelativeLayout mAdd_bank_card;
    private ListView mLv_bank_card;
    private MyBankCardAdapter myBankCardAdapter;
    private List<MyBankCardInfo> bankCardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("我的银行卡");
        FindViewById();
        InitData();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_my_bank_card;
    }

    private void FindViewById() {
        mAdd_bank_card = (RelativeLayout) findViewById(R.id.add_bank_card);
        mAdd_bank_card.setOnClickListener(this);

    }

    private void InitData() {
        mLv_bank_card = (ListView) findViewById(R.id.lv_bank_card);
        bankCardList = new ArrayList<>();
        myBankCardAdapter = new MyBankCardAdapter(bankCardList, MyBankCard.this);
        mLv_bank_card.setAdapter(myBankCardAdapter);
        myBankCardAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_bank_card:
                startActivity(new Intent(MyBankCard.this, AddBankCard.class));
                break;
        }
    }
}