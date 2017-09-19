package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.adapter.ExchangeZoneAdapter;
import com.zmh.zz.zmh.modelinfo.ExchangeZoneInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 * 兑换记录
 */

public class Ac_Exchange_zone extends BaseActivity {
    private ListView mLv_exchange_history;
    private ExchangeZoneAdapter exchangeZoneAdapter;
    private List<ExchangeZoneInfo> exchangeZoneList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("兑换记录");
        ListViewInitData();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_exchange_zone;
    }

    private void ListViewInitData() {
        mLv_exchange_history = (ListView) findViewById(R.id.lv_exchange_history);
        exchangeZoneList = new ArrayList<>();
        exchangeZoneAdapter = new ExchangeZoneAdapter(exchangeZoneList, Ac_Exchange_zone.this);
        mLv_exchange_history.setAdapter(exchangeZoneAdapter);
        exchangeZoneAdapter.notifyDataSetChanged();
    }
}
