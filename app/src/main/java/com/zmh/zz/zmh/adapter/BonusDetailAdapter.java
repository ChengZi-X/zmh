package com.zmh.zz.zmh.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.modelinfo.BonusDetailInfo;

import java.util.List;

/**
 * Created by Administrator
 */


public class BonusDetailAdapter extends RecyclerView.Adapter<BonusDetailAdapter.MyViewHolder> {
    private Context mContext;
    private List<BonusDetailInfo> list;
    private OnItemClickListener mClickListener;

    public BonusDetailAdapter(List<BonusDetailInfo> list, Context context) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.ac_item_bonus_detail, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view, mClickListener);
        return myViewHolder;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mTv_sum.setText("余额:" + "\r\r" + "32000.00");
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTv_sum;
        private OnItemClickListener mListener;// 声明自定义的接口

        public MyViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            mTv_sum = (TextView) itemView.findViewById(R.id.tv_sum);
            mListener = listener;
            // 为ItemView添加点击事件
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onItemClick(view, getPosition());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }
}