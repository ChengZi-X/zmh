package com.zmh.zz.zmh.integralfragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
    private RecyclerView mRv_exchange;
    private ExchangeAdapter exchangeAdapter;
    private List<ExchangeInfo> exchangeList;
    private TextView mExchange_record;

    @Override
    public View initView() {
        view = View.inflate(getActivity(), R.layout.fragment_exchange, null);
        FindViewById();
        initData();
        return view;
    }

    private void FindViewById() {
        mExchange_record = (TextView) view.findViewById(R.id.exchange_record);
        mExchange_record.setOnClickListener(this);
    }

    private void initData() {
        exchangeList = new ArrayList<>();
        mRv_exchange = (RecyclerView) view.findViewById(R.id.rv_exchange);
        exchangeAdapter = new ExchangeAdapter(exchangeList, getActivity());
        mRv_exchange.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv_exchange.setAdapter(exchangeAdapter);
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
