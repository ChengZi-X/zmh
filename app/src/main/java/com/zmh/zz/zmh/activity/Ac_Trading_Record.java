package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.adapter.TradingRecordAdapter;
import com.zmh.zz.zmh.modelinfo.TradingRecordInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on
 * 交易记录
 */

public class Ac_Trading_Record extends AppCompatActivity implements View.OnClickListener {
    private TextView toolbartitle;
    private RelativeLayout mTitle_back;
    private ListView mLv_trading_record;
    private TradingRecordAdapter tradingRecordAdapter;
    private List<TradingRecordInfo> tradingrecordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_trading_record);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("交易记录");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        mTitle_back = (RelativeLayout) findViewById(R.id.title_back);
        mTitle_back.setOnClickListener(this);
        InitData();
    }

    private void InitData() {
        mLv_trading_record = (ListView) findViewById(R.id.lv_trading_record);
        tradingrecordList = new ArrayList<>();
        tradingRecordAdapter = new TradingRecordAdapter(tradingrecordList, Ac_Trading_Record.this);
        mLv_trading_record.setAdapter(tradingRecordAdapter);
        tradingRecordAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
        }
    }

}
