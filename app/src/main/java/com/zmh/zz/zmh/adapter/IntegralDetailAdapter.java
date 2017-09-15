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
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = View.inflate(mContext, R.layout.ac_item_integral_detail, null);
            holder.mTv_integral = (TextView) view.findViewById(R.id.tv_integral);
            holder.mTv_integral.setText("+" + 10);
            TextPaint tp = holder.mTv_integral.getPaint();
            tp.setFakeBoldText(true);
        }
        return view;
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
