package com.zmh.zz.zmh.mainfragment;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.BaseFragment;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.activity.Bonus;
import com.zmh.zz.zmh.activity.Sum;
import com.zmh.zz.zmh.activity.TradingRecord;

/**
 * 资金
 */
public class FragmentFund extends BaseFragment implements View.OnClickListener {
    private RelativeLayout mRemaining_sum, mBonus;
    private View view;
    private TextView mTrading_record;

    @Override
    public View initView() {
        view = View.inflate(getActivity(), R.layout.fragment_fund, null);
        setNotificationBar(R.color.f3);
        FindViewById();
        initData();
        return view;
    }

    private void FindViewById() {
        mTrading_record = (TextView) view.findViewById(R.id.trading_record);
        mRemaining_sum = (RelativeLayout) view.findViewById(R.id.remaining_sum);
        mBonus = (RelativeLayout) view.findViewById(R.id.bonus);
        mTrading_record.setOnClickListener(this);
        mRemaining_sum.setOnClickListener(this);
        mBonus.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.trading_record:
                startActivity(new Intent(getActivity(), TradingRecord.class));
                break;
            case R.id.remaining_sum:
                startActivity(new Intent(getActivity(), Sum.class));
                break;
            case R.id.bonus:
                startActivity(new Intent(getActivity(), Bonus.class));
                break;
        }
    }

    public void initData() {
    }
}