package com.zmh.zz.zmh.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.modelinfo.MyBankCardInfo;

import java.util.List;

/**
 * Created by Administrator
 */

public class MyBankCardAdapter extends BaseAdapter {
    private List<MyBankCardInfo> list;
    private Context mContext;

    public MyBankCardAdapter(List<MyBankCardInfo> list, Context context) {
        this.list = list;
        this.mContext = context;
    }


    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.ac_item_my_bankcard, null);
//            holder.mTv_mouledtype = (TextView) convertView.findViewById(R.id.tv_list_item_mouled);
//            holder.mTv_mouledtype.setText("");
        }
        return convertView;
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
