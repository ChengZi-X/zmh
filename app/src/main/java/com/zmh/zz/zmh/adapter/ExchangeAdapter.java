package com.zmh.zz.zmh.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.modelinfo.ExchangeInfo;

import java.util.List;

/**
 * Created by Administrator
 * 兑换专区
 */
public class ExchangeAdapter extends RecyclerView.Adapter<ExchangeAdapter.MyViewHolder> {
    private Context mContext;
    private List<ExchangeInfo> list;

    public ExchangeAdapter(List<ExchangeInfo> list, Context context) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.ac_item_exchange, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
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

    class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}


//public class ExchangeAdapter extends BaseAdapter {
//    private List<ExchangeInfo> list;
//    private Context mContext;
//
//    public ExchangeAdapter(List<ExchangeInfo> list, Context context) {
//        this.list = list;
//        this.mContext = context;
//    }
//
//    @Override
//    public int getCount() {
//        return 6;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup viewGroup) {
//        final ViewHolder holder;
//        if (convertView == null) {
//            holder = new ViewHolder();
//            convertView = View.inflate(mContext, R.layout.ac_item_exchange, null);
//            holder.mExchange = (LinearLayout) convertView.findViewById(R.id.exchange);
//            holder.mExchange.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    mContext.startActivity(new Intent(mContext, ExchangeData.class));
//                }
//            });
//        }
//        return convertView;
//
//    }
//
//    class ViewHolder {
//        private LinearLayout mExchange;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return list.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//}
