package com.zmh.zz.zmh.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.adapter.BonusDetailAdapter;
import com.zmh.zz.zmh.modelinfo.BonusDetailInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 * 奖金明细
 */

public class BonusDetail extends AppCompatActivity implements View.OnClickListener {
    private TextView toolbartitle;
    private RelativeLayout mTitle_back, mRll_title;
    private ListView mLv_bonus_detail;
    private BonusDetailAdapter bonusDetailAdapter;
    private List<BonusDetailInfo> bonusDetailList;
    private TextView mTv_all, mTv_expend, mTv_income;

    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_bonus_detail);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("全部");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        mTitle_back = (RelativeLayout) findViewById(R.id.title_back);
        mRll_title = (RelativeLayout) findViewById(R.id.rll_title);
        mRll_title.setOnClickListener(this);
        mTitle_back.setOnClickListener(this);
        InitData();
    }

    private void InitData() {
        mLv_bonus_detail = (ListView) findViewById(R.id.lv_bonus_detail);
        bonusDetailList = new ArrayList<>();
        bonusDetailAdapter = new BonusDetailAdapter(bonusDetailList, BonusDetail.this);
        mLv_bonus_detail.setAdapter(bonusDetailAdapter);
        bonusDetailAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.rll_title:
                showPopWindow();
                break;
            case R.id.all:
                toolbartitle.setText("全部");
                popupWindow.dismiss();
                break;
            case R.id.expend:
                toolbartitle.setText("支出");
                popupWindow.dismiss();
                break;
            case R.id.income:
                toolbartitle.setText("收入");
                popupWindow.dismiss();
                break;
        }
    }


    private void showPopWindow() {
        //  适配PopupWindow布局文件
        View popView = View.inflate(this, R.layout.layout_select_detail_pop, null);
        //  创建PopupWindow
        popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //  设置SelectPicPopupWindow弹出窗体的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //  点击外部区域消失
        popupWindow.setOutsideTouchable(true);
        //  设置PopupWindow在ll_view下显示
        popupWindow.showAsDropDown(findViewById(R.id.ll_view), 0, 0);
        setBackgroundAlpha(0.5f);//设置屏幕透明度
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // popupWindow隐藏时恢复屏幕正常透明度
                setBackgroundAlpha(1.0f);
            }
        });
        //  PopupWindow控件ID
        mTv_all = (TextView) popView.findViewById(R.id.all);
        mTv_expend = (TextView) popView.findViewById(R.id.expend);
        mTv_income = (TextView) popView.findViewById(R.id.income);
        mTv_all.setOnClickListener(this);
        mTv_expend.setOnClickListener(this);
        mTv_income.setOnClickListener(this);
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 屏幕透明度0.0-1.0 1表示完全不透明
     */
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        this.getWindow().setAttributes(lp);
    }
}
