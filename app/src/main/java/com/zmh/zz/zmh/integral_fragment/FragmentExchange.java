package com.zmh.zz.zmh.integral_fragment;

import android.view.View;
import android.widget.ListView;

import com.zmh.zz.zmh.BaseFragment;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.adapter.ExchangeAdapter;
import com.zmh.zz.zmh.modelinfo.ExchangeInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 兑换
 */
public class FragmentExchange extends BaseFragment {
    private View view;
    private ListView mLv_exchange;
    private ExchangeAdapter exchangeAdapter;
    private List<ExchangeInfo> exchangeList;
    @Override
    public View initView() {
        view = View.inflate(getActivity(), R.layout.fragment_exchange, null);
        LsitViewData();
        return view;
    }

    private void LsitViewData() {
        mLv_exchange = (ListView) view.findViewById(R.id.lv_exchange);
        exchangeList = new ArrayList<>();
        exchangeAdapter = new ExchangeAdapter(exchangeList, getActivity());
        mLv_exchange.setAdapter(exchangeAdapter);
        exchangeAdapter.notifyDataSetChanged();

    }
}
