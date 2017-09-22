package com.zmh.zz.zmh.adapter;

import android.content.Context;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.modelinfo.IntegralDetailInfo;

import java.util.List;

/**
 * Created by Administrator
 */

public class IntegralDetailAdapter extends BaseAdapter {

    private List<IntegralDetailInfo> list;
    private Context mContext;

    public IntegralDetailAdapter(List<IntegralDetailInfo> list, Context context) {
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
            convertView = View.inflate(mContext, R.layout.ac_item_integral_detail, null);
            holder.mTv_integral = (TextView) convertView.findViewById(R.id.tv_integral);
            holder.mTv_integral.setText("+" + 10);
            TextPaint tp = holder.mTv_integral.getPaint();
            tp.setFakeBoldText(true);
        }
        return convertView;
    }

    class ViewHolder {
        private TextView mTv_integral;
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
