package com.zmh.zz.zmh.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.activity.ClientData;
import com.zmh.zz.zmh.modelinfo.IntegralDetailInfo;
import com.zmh.zz.zmh.modelinfo.MyClientInfo;

import java.util.List;

/**
 * Created by Administrator
 */


public class MyClientAdapter extends RecyclerView.Adapter<MyClientAdapter.MyViewHolder> {
    private Context mContext;
    private List<MyClientInfo> list;

    public MyClientAdapter(List<MyClientInfo> list, Context context) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.ac_item_my_clientinfo, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "点击了第" + position + "条", Toast.LENGTH_SHORT).show();
                mContext.startActivity(new Intent(mContext, ClientData.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}