package com.zmh.zz.zmh.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.modelinfo.ClientBonusInfo;

import java.util.List;

/**
 * Created by Administrator
 */

public class ClienBonustAdapter extends BaseAdapter {
    private List<ClientBonusInfo> list;
    private Context mContext;

    public ClienBonustAdapter(List<ClientBonusInfo> list, Context context) {
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
            convertView = View.inflate(mContext, R.layout.ac_item_client_bonus, null);


        }
        return convertView;
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
