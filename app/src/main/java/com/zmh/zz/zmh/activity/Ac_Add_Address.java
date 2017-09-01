package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.ChangeAddressPopwindow;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.utlis.CheckBoxSample;
import com.zmh.zz.zmh.utlis.ToastUtils;

/**
 * Created by Administrator on 2017/8/3.
 * 添加新的快件地址
 */

public class Ac_Add_Address extends AppCompatActivity implements View.OnClickListener {
    private TextView mChoose_address, toolbartitle, mTitle_save;
    private RelativeLayout mTitle_back;
    private CheckBoxSample mCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_add_address);
        mTitle_back = (RelativeLayout) findViewById(R.id.title_back);
        mTitle_back.setOnClickListener(this);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("新增快件地址");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        mTitle_save = (TextView) findViewById(R.id.title_save);
        mTitle_save.setText("保存");
        TextPaint tp1 = mTitle_save.getPaint();
        tp1.setFakeBoldText(true);
        mTitle_save.setOnClickListener(this);
        mChoose_address = (TextView) findViewById(R.id.choose_address);
        mChoose_address.setOnClickListener(this);
        mCheck = (CheckBoxSample) findViewById(R.id.check);
        mCheck.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_save:
                ToastUtils.showToast(Ac_Add_Address.this, "保存");
                break;
            case R.id.choose_address:
                ChangeAddressPopwindow mChangeAddressPopwindow = new ChangeAddressPopwindow(Ac_Add_Address.this);
                mChangeAddressPopwindow.setAddress("河南", "郑州", "金水区");
                mChangeAddressPopwindow.showAtLocation(mChoose_address, Gravity.BOTTOM, 0, 0);
                mChangeAddressPopwindow
                        .setAddresskListener(new ChangeAddressPopwindow.OnAddressCListener() {
                            @Override
                            public void onClick(String province, String city, String area) {
                                mChoose_address.setText(province + " " + city + " " + area);
                            }
                        });
                break;
            case R.id.check:
                mCheck.toggle();
                ToastUtils.showToast(Ac_Add_Address.this, mCheck.isChecked()+"");
                break;
        }
    }
}
