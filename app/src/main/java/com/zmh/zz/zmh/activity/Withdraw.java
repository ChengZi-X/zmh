package com.zmh.zz.zmh.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.selectbankcard.BankcardBean;
import com.zmh.zz.zmh.selectbankcard.MyAdapterWitdraw;
import com.zmh.zz.zmh.selectbankcard.OnBankcardSlected;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 * 账户提现
 */

public class Withdraw extends BaseActivity implements View.OnClickListener, OnBankcardSlected {
    private RelativeLayout mRelativeLayout, mIvBack;
    private PopupWindow popupWindow;
    private ListView mListView;
    private List<BankcardBean> mList;
    private TextView mTextView, mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("账户提现");
        FindViewById();
        setData();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_withdraw;
    }

    private void FindViewById() {
        mRelativeLayout = (RelativeLayout) findViewById(R.id.rl_recharge_bankcard);
        mTextView = (TextView) findViewById(R.id.tv_bankcard);
        mRelativeLayout.setOnClickListener(this);
    }

    private void setData() {
        mList = new ArrayList<>();
        BankcardBean bankcard1 = new BankcardBean();
        bankcard1.setBankName("中国建设银行");
        bankcard1.setCardId("8281");
        mList.add(bankcard1);
        mTextView.setText(mList.get(0).getBankName() + "(" + mList.get(0).getCardId() + ")");
        mList.get(0).setChecked(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_recharge_bankcard:
                showPopWindow();
                break;
            case R.id.pop_back:
                popupWindow.dismiss();
                break;
        }
    }

    private void showPopWindow() {
        //  适配PopupWindow布局文件
        View popView = View.inflate(this, R.layout.layout_bankcard_select_pop, null);
        //  创建PopupWindow
        popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        //  设置SelectPicPopupWindow弹出窗体的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //  点击外部区域消失
        popupWindow.setOutsideTouchable(true);
        //  设置PopupWindow在ll_main下显示
        popupWindow.showAtLocation(findViewById(R.id.ll_main), Gravity.BOTTOM | Gravity.LEFT, 0, 0);
        //  PopupWindow返回键
        mIvBack = (RelativeLayout) popView.findViewById(R.id.pop_back);
        mIvBack.setOnClickListener(this);
        //  PupupWindow标题
        mTvTitle = (TextView) popView.findViewById(R.id.pop_title);
        mTvTitle.setText("选择提现银行卡");
        //  银行卡列表的ListView
        mListView = (ListView) popView.findViewById(R.id.lv_bankcard);
        MyAdapterWitdraw adapter = new MyAdapterWitdraw(mList, Withdraw.this);
        mListView.setAdapter(adapter);
    }

    /**
     * PopupWindow中选择银行卡的回调方法
     *
     * @param position 点击item的位置
     */
    @Override
    public void onBankcardSelectedListener(int position) {
        //  在Activity中设置选中的银行卡信息
        mTextView.setText(mList.get(position).getBankName() + "(" + mList.get(position).getCardId() + ")");
        //  取消PopupWindow
        popupWindow.dismiss();
    }


}
