package com.zmh.zz.zmh.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.modelinfo.TradingRecordInfo;

import java.util.List;

/**
 * Created by Administrator on
 */

public class TradingRecordAdapter extends BaseAdapter {
    private List<TradingRecordInfo> list;
    private Context mContext;

    public TradingRecordAdapter(List<TradingRecordInfo> list, Context context) {
        this.list = list;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.ac_item_trading_record, null);
            holder.mTv_sum = (TextView) convertView.findViewById(R.id.tv_sum);
            holder.mTv_sum.setText("总资产余额:" + "\r\r" + "32000.00");
        }
        return convertView;
    }

    class ViewHolder {
        private TextView mTv_sum;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
