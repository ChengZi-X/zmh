package com.zmh.zz.zmh.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.activity.ClientData;
import com.zmh.zz.zmh.modelinfo.MyClientInfo;

import java.util.List;

/**
 * Created by Administrator
 */

public class MyClientAdapter extends BaseAdapter {
    private List<MyClientInfo> list;
    private Context mContext;

    public MyClientAdapter(List<MyClientInfo> list, Context context) {
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
            convertView = View.inflate(mContext, R.layout.ac_item_my_clientinfo, null);
            holder.mClient = (RelativeLayout) convertView.findViewById(R.id.client);
            holder.mClient.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext, ClientData.class));
                }
            });
        }
        return convertView;
    }

    class ViewHolder {
        private RelativeLayout mClient;
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
