package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.utlis.CheckBoxSample;
import com.zmh.zz.zmh.utlis.ToastUtils;
import com.zmh.zz.zmh.wheelview.PickerScrollView;
import com.zmh.zz.zmh.wheelview.Pickers;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator
 * 添加银行
 */

public class Ac_Add_Bank_Card extends AppCompatActivity implements View.OnClickListener {
    private Button mBut_save;
    private TextView mOpening_bank, toolbartitle;
    private CheckBoxSample mCheck;
    private RelativeLayout mTitle_back;

    private PickerScrollView pickerscrlllview; // 滚动选择器
    private List<Pickers> list; // 滚动选择器数据
    private String[] id;
    private String[] name;
    private TextView mPicker_yes, mPicker_no; // 确定按钮
    private RelativeLayout picker_rel; // 选择器布局

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_add_bank_card);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("添加银行卡");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        mBut_save = (Button) findViewById(R.id.but_save);
        mOpening_bank = (TextView) findViewById(R.id.opening_bank);
        mBut_save.setOnClickListener(this);
        picker_rel = (RelativeLayout) findViewById(R.id.picker_rel);
        pickerscrlllview = (PickerScrollView) findViewById(R.id.pickerscrollview);
        mPicker_yes = (TextView) findViewById(R.id.picker_yes);
        mPicker_no = (TextView) findViewById(R.id.picker_no);
        mCheck = (CheckBoxSample) findViewById(R.id.check);
        mCheck.setOnClickListener(this);
        mTitle_back = (RelativeLayout) findViewById(R.id.title_back);
        mTitle_back.setOnClickListener(this);
        initLinstener();
        initData();
    }

    /**
     * 设置监听事件
     */
    private void initLinstener() {
        pickerscrlllview.setOnSelectListener(pickerListener);
        mOpening_bank.setOnClickListener(onClickListener);
        mPicker_yes.setOnClickListener(onClickListener);
        mPicker_no.setOnClickListener(onClickListener);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        list = new ArrayList<Pickers>();
        id = new String[]{"1", "2", "3", "4", "5", "6"};
        name = new String[]{"中国银行", "中国农业银行", "中国招商银行", "中国工商银行", "中国建设银行", "中国民生银行"};
        for (int i = 0; i < name.length; i++) {
            list.add(new Pickers(name[i], id[i]));
        }
        // 设置数据，默认选择第一条
        pickerscrlllview.setData(list);
        pickerscrlllview.setSelected(0);
    }

    // 滚动选择器选中事件
    PickerScrollView.onSelectListener pickerListener = new PickerScrollView.onSelectListener() {
        @Override
        public void onSelect(Pickers pickers) {
            mOpening_bank.setText(pickers.getShowConetnt());
            if (!mOpening_bank.getText().toString().equals("请选择")) {
                mOpening_bank.setTextColor(getResources().getColor(R.color.absolute_black));//通过获得资源文件进行设置。
            }
        }
    };
    // 点击监听事件
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mOpening_bank) {
                picker_rel.setVisibility(View.VISIBLE);
            } else if (view == mPicker_yes) {
                picker_rel.setVisibility(View.GONE);
            }
            if (view == mPicker_no) {
                picker_rel.setVisibility(View.GONE);
            }
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_save:
                ToastUtils.showToast(Ac_Add_Bank_Card.this, "保存");
                break;
            case R.id.check:
                mCheck.toggle();
                ToastUtils.showToast(Ac_Add_Bank_Card.this, mCheck.isChecked() + "");
                break;
            case R.id.title_back:
                finish();
                break;
        }
    }
}
