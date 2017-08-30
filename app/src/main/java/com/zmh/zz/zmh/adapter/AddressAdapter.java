package com.zmh.zz.zmh.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.modelinfo.AddressInfo;
import com.zmh.zz.zmh.utlis.CustomSwipeListView;
import com.zmh.zz.zmh.utlis.SwipeItemView;
import com.zmh.zz.zmh.utlis.ToastUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/8/11.
 */

public class AddressAdapter extends BaseAdapter {
    private List<AddressInfo> list;
    private Context mContext;
    private SwipeItemView mLastSlideViewWithStatusOn;

    public AddressAdapter(List<AddressInfo> list, Context context) {
        this.list = list;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;
        SwipeItemView slideView = (SwipeItemView) convertView;
        if (slideView == null) {
            View itemView = View.inflate(mContext, R.layout.ac_item_address, null);
            slideView = new SwipeItemView(mContext);
            slideView.setContentView(itemView);
            holder = new ViewHolder(slideView);
            slideView.setOnSlideListener(new SwipeItemView.OnSlideListener() {
                @Override
                public void onSlide(View view, int status) {
                    if (mLastSlideViewWithStatusOn != null
                            && mLastSlideViewWithStatusOn != view) {
                        mLastSlideViewWithStatusOn.shrink();
                    }
                    if (status == SLIDE_STATUS_ON) {
                        mLastSlideViewWithStatusOn = (SwipeItemView) view;
                    }
                }
            });
            slideView.setTag(holder);
        } else {
            holder = (ViewHolder) slideView.getTag();
        }
        if (CustomSwipeListView.mFocusedItemView != null) {
            CustomSwipeListView.mFocusedItemView.shrink();
        }
        holder.deleteHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
                dialog.setMessage("确定要删除此收货人？");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ToastUtils.showToast(mContext, "点击删除" + position + "");
                    }
                });
                dialog.setNegativeButton("取消", null).show();
                notifyDataSetChanged();
            }
        });
        return slideView;
    }

    class ViewHolder {
        public ViewGroup deleteHolder;

        ViewHolder(View view) {
            deleteHolder = (ViewGroup) view.findViewById(R.id.holder);
        }
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
