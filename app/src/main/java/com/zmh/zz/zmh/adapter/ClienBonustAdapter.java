package com.zmh.zz.zmh.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.modelinfo.BonusDetailInfo;
import com.zmh.zz.zmh.modelinfo.ClientBonusInfo;

import java.util.List;

/**
 * Created by Administrator
 */

public class ClienBonustAdapter extends RecyclerView.Adapter<ClienBonustAdapter.MyViewHolder> {
    private final int TYPE_HEAD = 1;
    private final int TYPE_NOMAL = 2;
    private int currentType;
    private Context mContext;
    private List<ClientBonusInfo> list;

    public ClienBonustAdapter(List<ClientBonusInfo> list, Context context) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.ac_item_client_bonus, parent, false);
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

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

}


//public class ClienBonustAdapter extends BaseAdapter {
//    private List<ClientBonusInfo> list;
//    private Context mContext;
//
//    public ClienBonustAdapter(List<ClientBonusInfo> list, Context context) {
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
//            convertView = View.inflate(mContext, R.layout.ac_item_client_bonus, null);
//
//
//        }
//        return convertView;
//    }
//
//    class ViewHolder {
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
//}
