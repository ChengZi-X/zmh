package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.adapter.ExchangeRecordAdapter;
import com.zmh.zz.zmh.modelinfo.ExchangeRecordInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 * 兑换记录
 */

public class ExchangerRecord extends BaseActivity {
    private RecyclerView mRv_exchange_record;
    private ExchangeRecordAdapter exchangeRecordAdapter;
    private List<ExchangeRecordInfo> exchangeRecordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("兑换记录");
        initData();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_exchange_record;
    }

    private void initData() {
        mRv_exchange_record = (RecyclerView) findViewById(R.id.rv_exchange_record);
        exchangeRecordList = new ArrayList<>();
        exchangeRecordAdapter = new ExchangeRecordAdapter(exchangeRecordList, ExchangerRecord.this);
        mRv_exchange_record.setLayoutManager(new LinearLayoutManager(this));
        mRv_exchange_record.setAdapter(exchangeRecordAdapter);
        exchangeRecordAdapter.notifyDataSetChanged();
    }
}
