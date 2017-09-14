package com.zmh.zz.zmh.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.modelinfo.ExchangeInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = View.inflate(mContext, R.layout.ac_item_exchange, null);
//            holder.mTv_mouledtype = (TextView) convertView.findViewById(R.id.tv_list_item_mouled);
//            holder.mTv_mouledtype.setText("");
        }
        return view;
    }

    class ViewHolder {
        private TextView mTv_mouledtype;
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
