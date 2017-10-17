package com.zmh.zz.zmh.activity;

import android.os.Bundle;
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
    private ListView mLv_exchange_record;
    private ExchangeRecordAdapter exchangeRecordAdapter;
    private List<ExchangeRecordInfo> exchangeRecordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("兑换记录");
        ListViewInitData();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_exchange_record;
    }

    private void ListViewInitData() {
        mLv_exchange_record = (ListView) findViewById(R.id.lv_exchange_record);
        exchangeRecordList = new ArrayList<>();
        exchangeRecordAdapter = new ExchangeRecordAdapter(exchangeRecordList, ExchangerRecord.this);
        mLv_exchange_record.setAdapter(exchangeRecordAdapter);
        exchangeRecordAdapter.notifyDataSetChanged();
    }
}
