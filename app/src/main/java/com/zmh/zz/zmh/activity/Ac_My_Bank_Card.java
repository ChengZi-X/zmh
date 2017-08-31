package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.adapter.MyBankCardAdapter;
import com.zmh.zz.zmh.modelinfo.MyBankCardInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/10.
 * 我的银行卡列表显示
 */

public class Ac_My_Bank_Card extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout mAdd_bank_card;
    private ListView mLv_bank_card;
    private MyBankCardAdapter myBankCardAdapter;
    private List<MyBankCardInfo> bankCardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_my_bank_card);
        mAdd_bank_card = (RelativeLayout) findViewById(R.id.add_bank_card);
        mAdd_bank_card.setOnClickListener(this);
        InitData();
    }

    private void InitData() {
        mLv_bank_card = (ListView) findViewById(R.id.lv_bank_card);
        bankCardList = new ArrayList<>();
        myBankCardAdapter = new MyBankCardAdapter(bankCardList, Ac_My_Bank_Card.this);
        mLv_bank_card.setAdapter(myBankCardAdapter);
        myBankCardAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_bank_card:
//                Intent intent = new Intent(Ac_My_Bank_Card.this, Ac_Add_Bank_Card.class);
//                startActivity(intent);
                break;
        }
    }
}