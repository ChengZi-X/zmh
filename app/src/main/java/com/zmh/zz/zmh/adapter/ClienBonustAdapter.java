package com.zmh.zz.zmh.adapter;

import android.content.Context;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.modelinfo.ClientBonusInfo;

import java.util.List;

/**
 * Created by Administrator
 * 客户奖金
 */
public class ClienBonustAdapter extends BaseRecyclerViewAdapter<ClientBonusInfo> {
    private BaseViewHolder.onItemCommonClickListener commonClickListener;
    private Context mContext;

    public ClienBonustAdapter(Context context, List<ClientBonusInfo> datas, BaseViewHolder.onItemCommonClickListener commonClickListener) {
        super(context, datas, R.layout.ac_item_client_bonus);
        this.commonClickListener = commonClickListener;
        this.mContext = context;
    }

    @Override
    protected void bindData(BaseViewHolder holder, ClientBonusInfo data) {
        //获取图片   不知道能不能使用
//        final String headimg = (data.getS());
//        String iconURL = "http://192.168.0.01/" + headimg;
//        Glide.with(mContext).load(iconURL).into((ImageView) holder.getView(R.id.im_ss));

        holder.setText(R.id.tv_sum, "+" + "3200.00");
        holder.setCommonClickListener(commonClickListener);
    }
}


