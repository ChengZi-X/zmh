package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.ChangeAddressPopwindow;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.utlis.CheckBoxSample;
import com.zmh.zz.zmh.utlis.ToastUtils;

/**
 * Created by Administrator
 * 添加新的快件地址
 */

public class AddAddress extends BaseActivity implements View.OnClickListener {
    private TextView mChoose_address;
    private CheckBoxSample mCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("新增快件地址");
        setRtTitle("保存");
        setRightBtnVisible(true);
        FindViewById();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_add_address;//任意非空布局
    }

    private void FindViewById() {
        mChoose_address = (TextView) findViewById(R.id.choose_address);
        mChoose_address.setOnClickListener(this);
        mCheck = (CheckBoxSample) findViewById(R.id.check);
        mCheck.setOnClickListener(this);
    }

    //右键点击
    @Override
    protected void onClickRight() {
        ToastUtils.showToast(AddAddress.this, "保存");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.choose_address:
                ChangeAddressPopwindow mChangeAddressPopwindow = new ChangeAddressPopwindow(AddAddress.this);
                mChangeAddressPopwindow.setAddress("河南", "郑州", "金水区");
                mChangeAddressPopwindow.showAtLocation(mChoose_address, Gravity.BOTTOM, 0, 0);
                mChangeAddressPopwindow
                        .setAddresskListener(new ChangeAddressPopwindow.OnAddressCListener() {
                            @Override
                            public void onClick(String province, String city, String area) {
                                mChoose_address.setText(province + " " + city + " " + area);
                                if (!mChoose_address.getText().toString().equals("省份 城市 县城")) {
                                    mChoose_address.setTextColor(getResources().getColor(R.color.absolute_black));//通过获得资源文件进行设置。
                                }
                            }
                        });
                break;
            case R.id.check:
                mCheck.toggle();
                ToastUtils.showToast(AddAddress.this, mCheck.isChecked() + "");
                break;
        }
    }
}
