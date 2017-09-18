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
import com.zmh.zz.zmh.selectbankcard.MyAdapter_Top_Up;
import com.zmh.zz.zmh.selectbankcard.OnBankcardSlected;
import com.zmh.zz.zmh.utlis.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 * 账户充值
 */

public class Ac_Top_Up extends BaseActivity implements View.OnClickListener, OnBankcardSlected {
    private RelativeLayout mRelativeLayout, mIvBack;
    private PopupWindow popupWindow;
    private ListView mListView;
    private List<BankcardBean> mList;
    private TextView mTextView, mTvTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("账户充值");
        setRtTitle("限额说明");
        setRightBtnVisible(true);
        FindViewById();
        setData();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_top_up;
    }

    private void FindViewById() {
        mRelativeLayout = (RelativeLayout) findViewById(R.id.rl_recharge_bankcard);
        mTextView = (TextView) findViewById(R.id.tv_recharge_bankcard);
        mRelativeLayout.setOnClickListener(this);
    }

    //右键点击
    @Override
    protected void onClickRight() {
        ToastUtils.showToast(Ac_Top_Up.this, "限额说明");
    }

    private void setData() {
        mList = new ArrayList<>();
        BankcardBean bankcard1 = new BankcardBean();
        BankcardBean bankcard2 = new BankcardBean();
        BankcardBean bankcard3 = new BankcardBean();
        BankcardBean bankcard4 = new BankcardBean();
        BankcardBean bankcard5 = new BankcardBean();
        bankcard1.setBankName("中国农业银行");
        bankcard1.setCardId("8281");
        bankcard2.setBankName("中国工商银行");
        bankcard2.setCardId("8282");
        bankcard3.setBankName("中国银行");
        bankcard3.setCardId("8283");
        bankcard4.setBankName("中国建设银行");
        bankcard4.setCardId("8284");
        bankcard5.setBankName("中国交通银行");
        bankcard5.setCardId("8285");
        mList.add(bankcard1);
        mList.add(bankcard2);
        mList.add(bankcard3);
        mList.add(bankcard4);
        mList.add(bankcard5);
        mTextView.setText(mList.get(0).getBankName() + "(" + mList.get(0).getCardId() + ")");
        /**
         *  实体类中加入了一个boolean属性，当实体类中这个属性为true时表示在Activity中选中
         *  当点开PopupWindow时该属性也可以作为银行卡被勾选的标志
         *  这里默认选中第一个
         */
        mList.get(0).setChecked(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_recharge_bankcard:
                showPopWindow();
                break;
            case R.id.iv_back:
                popupWindow.dismiss();
                break;
        }
    }

    private void showPopWindow() {
        //  适配PopupWindow布局文件
        View popView = View.inflate(this, R.layout.bankcard_select, null);
        //  创建PopupWindow
        popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        //设置SelectPicPopupWindow弹出窗体的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //点击外部区域消失
        popupWindow.setOutsideTouchable(true);
        //  设置PopupWindow在ll_main下显示
        popupWindow.showAtLocation(findViewById(R.id.ll_main), Gravity.BOTTOM | Gravity.LEFT, 0, 0);
        //  PopupWindow返回键
        mIvBack = (RelativeLayout) popView.findViewById(R.id.iv_back);
        mIvBack.setOnClickListener(this);
        //  PupupWindow标题
        mTvTitle = (TextView) popView.findViewById(R.id.tv_title);
        mTvTitle.setText("选择付款银行卡");
        //  银行卡列表的ListView
        mListView = (ListView) popView.findViewById(R.id.lv_bankcard);
        MyAdapter_Top_Up adapter = new MyAdapter_Top_Up(mList, Ac_Top_Up.this);
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
