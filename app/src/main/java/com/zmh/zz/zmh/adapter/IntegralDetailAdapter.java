package com.zmh.zz.zmh.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.modelinfo.ExchangeRecordInfo;
import com.zmh.zz.zmh.modelinfo.IntegralDetailInfo;

import java.util.List;

/**
 * Created by Administrator
 */
public class IntegralDetailAdapter extends RecyclerView.Adapter<IntegralDetailAdapter.MyViewHolder> {
    private final int TYPE_HEAD = 1;
    private final int TYPE_NOMAL = 2;
    private int currentType;
    private Context mContext;
    private List<IntegralDetailInfo> list;

    public IntegralDetailAdapter(List<IntegralDetailInfo> list, Context context) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.ac_item_integral_detail, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mTv_integral.setText("+" + 10);
        TextPaint tp = holder.mTv_integral.getPaint();
        tp.setFakeBoldText(true);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "点击了第" + position + "条", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            currentType = TYPE_HEAD;
        } else {
            currentType = TYPE_NOMAL;
        }
        return currentType;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTv_integral;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTv_integral = (TextView) itemView.findViewById(R.id.tv_integral);
        }
    }

}
