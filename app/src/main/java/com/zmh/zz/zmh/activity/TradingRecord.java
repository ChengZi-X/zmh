package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.adapter.TradingRecordAdapter;
import com.zmh.zz.zmh.modelinfo.TradingRecordInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 * 交易记录
 */

public class TradingRecord extends BaseActivity {
    private ListView mLv_trading_record;
    private TradingRecordAdapter tradingRecordAdapter;
    private List<TradingRecordInfo> tradingrecordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("交易记录");
        FindViewById();
        InitData();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_trading_record;
    }

    private void FindViewById() {
    }

    private void InitData() {
        mLv_trading_record = (ListView) findViewById(R.id.lv_trading_record);
        tradingrecordList = new ArrayList<>();
        tradingRecordAdapter = new TradingRecordAdapter(tradingrecordList, TradingRecord.this);
        mLv_trading_record.setAdapter(tradingRecordAdapter);
        tradingRecordAdapter.notifyDataSetChanged();
    }


}
