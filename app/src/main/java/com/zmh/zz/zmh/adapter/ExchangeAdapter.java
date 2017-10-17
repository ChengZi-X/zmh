package com.zmh.zz.zmh.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.activity.ExchangeData;
import com.zmh.zz.zmh.modelinfo.ExchangeInfo;

import java.util.List;

/**
 * Created by Administrator
 * 兑换专区
 */

public class ExchangeAdapter extends BaseAdapter {
    private List<ExchangeInfo> list;
    private Context mContext;

    public ExchangeAdapter(List<ExchangeInfo> list, Context context) {
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
            convertView = View.inflate(mContext, R.layout.ac_item_exchange, null);
            holder.mExchange = (LinearLayout) convertView.findViewById(R.id.exchange);
            holder.mExchange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext, ExchangeData.class));
                }
            });
        }
        return convertView;

    }

    class ViewHolder {
        private LinearLayout mExchange;
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
