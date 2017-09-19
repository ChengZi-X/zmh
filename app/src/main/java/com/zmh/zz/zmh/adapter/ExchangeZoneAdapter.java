package com.zmh.zz.zmh.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.modelinfo.ExchangeZoneInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/9/18.
 */

public class ExchangeZoneAdapter extends BaseAdapter {
    private List<ExchangeZoneInfo> list;
    private Context mContext;

    public ExchangeZoneAdapter(List<ExchangeZoneInfo> list, Context context) {
        this.list = list;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = View.inflate(mContext, R.layout.ac_item_exchange_zone, null);
        }
        return view;
    }

    class ViewHolder {
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
