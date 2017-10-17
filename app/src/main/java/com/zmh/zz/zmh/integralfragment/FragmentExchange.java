package com.zmh.zz.zmh.integralfragment;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.zmh.zz.zmh.BaseFragment;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.activity.ExchangerRecord;
import com.zmh.zz.zmh.adapter.ExchangeAdapter;
import com.zmh.zz.zmh.modelinfo.ExchangeInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 兑换专区
 */
public class FragmentExchange extends BaseFragment implements View.OnClickListener {
    private View view;
    private ListView mLv_exchange;
    private ExchangeAdapter exchangeAdapter;
    private List<ExchangeInfo> exchangeList;
    private TextView mExchange_record;

    @Override
    public View initView() {
        view = View.inflate(getActivity(), R.layout.fragment_exchange, null);
        FindViewById();
        LsitViewData();
        return view;
    }

    private void FindViewById() {
        mExchange_record = (TextView) view.findViewById(R.id.exchange_record);
        mExchange_record.setOnClickListener(this);
    }

    private void LsitViewData() {
        mLv_exchange = (ListView) view.findViewById(R.id.lv_exchange);
        exchangeList = new ArrayList<>();
        exchangeAdapter = new ExchangeAdapter(exchangeList, getActivity());
        mLv_exchange.setAdapter(exchangeAdapter);
        exchangeAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exchange_record:
                startActivity(new Intent(getActivity(), ExchangerRecord.class));
                break;
        }

    }
}
