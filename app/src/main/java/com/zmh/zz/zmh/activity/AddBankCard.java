package com.zmh.zz.zmh.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.utlis.CheckBoxSample;
import com.zmh.zz.zmh.utlis.ToastUtils;
import com.zmh.zz.zmh.wheelview.PickerScrollView;
import com.zmh.zz.zmh.wheelview.PickersBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator
 * 添加银行卡
 */

public class AddBankCard extends AppCompatActivity implements View.OnClickListener {
    private TextView toolbartitle;
    private RelativeLayout mTitle_back;

    private Button mBut_save;
    private TextView mOpening_bank;
    private CheckBoxSample mCheck;
    private PickerScrollView pickerscrlllview; // 滚动选择器
    private List<PickersBean> mList; // 滚动选择器数据
    private String[] id;
    private String[] name;
    private TextView mPicker_yes, mPicker_no;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_add_bank_card);
        FindViewById();
    }

    private void FindViewById() {
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("添加银行卡");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        mTitle_back = (RelativeLayout) findViewById(R.id.title_back);
        mBut_save = (Button) findViewById(R.id.but_save);
        mOpening_bank = (TextView) findViewById(R.id.opening_bank);
        mCheck = (CheckBoxSample) findViewById(R.id.check);
        mTitle_back.setOnClickListener(this);
        mCheck.setOnClickListener(this);
        mBut_save.setOnClickListener(this);
        mOpening_bank.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mList = new ArrayList<>();
        id = new String[]{"1", "2", "3", "4", "5", "6"};
        name = new String[]{"中国银行", "中国农业银行", "中国招商银行", "中国工商银行", "中国建设银行", "中国民生银行"};
        for (int i = 0; i < name.length; i++) {
            mList.add(new PickersBean(name[i], id[i]));
        }
        // 设置数据，默认选择第一条
        pickerscrlllview.setData(mList);
        pickerscrlllview.setSelected(0);
    }

    // 滚动选择器选中事件
    PickerScrollView.onSelectListener pickerListener = new PickerScrollView.onSelectListener() {
        @Override
        public void onSelect(PickersBean pickers) {
            mOpening_bank.setText(pickers.getShowConetnt());
            if (!mOpening_bank.getText().toString().equals("请选择")) {
                mOpening_bank.setTextColor(getResources().getColor(R.color.absolute_black));//通过获得资源文件进行设置。
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_save:
                ToastUtils.showToast(AddBankCard.this, "保存");
                break;
            case R.id.check:
                mCheck.toggle();
                ToastUtils.showToast(AddBankCard.this, mCheck.isChecked() + "");
                break;
            case R.id.title_back:
                finish();
                break;
            case R.id.opening_bank:
                showPopWindow();
                initData();
                break;
            case R.id.picker_yes://确定
                popupWindow.dismiss();
                break;
            case R.id.picker_no://取消
                popupWindow.dismiss();
                break;
        }
    }

    private void showPopWindow() {
        //  适配PopupWindow布局文件
        View popView = View.inflate(this, R.layout.layout_picker_pop, null);
        //  创建PopupWindow
        popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        //  设置SelectPicPopupWindow弹出窗体的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //  点击外部区域消失
        popupWindow.setOutsideTouchable(true);
        //  设置PopupWindow在ll_main下显示
        popupWindow.showAtLocation(findViewById(R.id.ll_main), Gravity.BOTTOM | Gravity.LEFT, 0, 0);
        //  PopupWindow控件ID
        pickerscrlllview = (PickerScrollView) popView.findViewById(R.id.pickerscrollview);
        mPicker_yes = (TextView) popView.findViewById(R.id.picker_yes);
        mPicker_no = (TextView) popView.findViewById(R.id.picker_no);
        pickerscrlllview.setOnSelectListener(pickerListener);
        mPicker_yes.setOnClickListener(this);
        mPicker_no.setOnClickListener(this);
    }

}
