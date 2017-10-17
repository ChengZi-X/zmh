package com.zmh.zz.zmh.selectbankcard;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.activity.TopUp;

import java.util.List;

/**
 * 选择银行卡_账户充值
 */
public class MyAdapterTopUp extends BaseAdapter {
    private List<BankcardBean> mBankcards;
    private TopUp mContext;

    public MyAdapterTopUp(List<BankcardBean> mBankcards, TopUp context) {
        this.mBankcards = mBankcards;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mBankcards.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final MyViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_bankcard_select, null);
            holder = new MyViewHolder();
            holder.mIvLogo = (ImageView) convertView.findViewById(R.id.iv_item_bankcard_select_logo);
            holder.mTextView = (TextView) convertView.findViewById(R.id.tv_item_bankcard_select_bankcard);
            holder.mIvChecked = (ImageView) convertView.findViewById(R.id.iv_bankcard_select_selected);
            holder.mRelativeLayout = (RelativeLayout) convertView.findViewById(R.id.rl_item_bankcard_select);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        BankcardBean bankcardBean = mBankcards.get(position);
        holder.mTextView.setText(bankcardBean.getBankName() + "(" + bankcardBean.getCardId() + ")");
        holder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBankcards.get(position).setChecked(true);
                mContext.onBankcardSelectedListener(position);
                //  循环遍历List集合，除了选中的设置为true，其他全设置为false
                for (int i = 0; i < mBankcards.size(); i++) {
                    if (i == position) {
                        continue;
                    } else {
                        mBankcards.get(i).setChecked(false);
                    }
                }
            }
        });
        if (mBankcards.get(position).isChecked()) {
            //  被选中则显示对勾
            holder.mIvChecked.setVisibility(View.VISIBLE);
        } else {
            //  未被选中则隐藏对勾
            holder.mIvChecked.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    static class MyViewHolder {
        RelativeLayout mRelativeLayout;
        ImageView mIvLogo;
        TextView mTextView;
        ImageView mIvChecked;
    }

    @Override
    public Object getItem(int position) {
        return mBankcards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}

