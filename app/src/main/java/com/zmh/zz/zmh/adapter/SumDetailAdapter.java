package com.zmh.zz.zmh.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.modelinfo.SumDetailInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8.
 */

public class SumDetailAdapter extends BaseAdapter {
    private List<SumDetailInfo> list;
    private Context mContext;

    public SumDetailAdapter(List<SumDetailInfo> list, Context context) {
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
            convertView = View.inflate(mContext, R.layout.ac_item_sum_detail, null);
            holder.mTv_sum = (TextView) convertView.findViewById(R.id.tv_sum);
            holder.mTv_sum.setText("余额:" + "\r\r" + "32000.00");
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
