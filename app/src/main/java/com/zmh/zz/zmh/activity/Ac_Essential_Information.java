package com.zmh.zz.zmh.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.ChangeAddressPopwindow;
import com.zmh.zz.zmh.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * Created by Administrator
 * 基本信息
 */

public class Ac_Essential_Information extends BaseActivity implements View.OnClickListener {
    private RelativeLayout mCertificate;
    private TextView mChoose_address;
    private EditText mDate;
    DateFormat fmtDate = new java.text.SimpleDateFormat("yyyy-MM-dd");
    //获取一个日历对象
    Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);
    //当点击DatePickerDialog控件的设置按钮时，调用该方法
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            //修改日历控件的年，月，日
            //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            //将页面TextView的显示更新为最新时间
            mDate.setText(fmtDate.format(dateAndTime.getTime()));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("基本信息");
        setRtTitle("提交");
        setRightBtnVisible(true);
        FindViewById();
        InitData();
    }


    @Override
    protected int getContentView() {
        return R.layout.ac_essential_information;
    }

    private void FindViewById() {
        mDate = (EditText) findViewById(R.id.date);
        mCertificate = (RelativeLayout) findViewById(R.id.validity_of_a_certificate);
        mChoose_address = (TextView) findViewById(R.id.choose_address);
        mCertificate.setOnClickListener(this);
        mChoose_address.setOnClickListener(this);
    }

    private void InitData() {
    }

    //右键点击
    @Override
    protected void onClickRight() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(Ac_Essential_Information.this);
        dialog.setTitle("提示");
        dialog.setMessage("\r\r\r\r\r\r\r\r您的资料修改已提交,请耐心等待,我们将在1-2个工作日人审核完成。");
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {// 确定按钮的响应事件
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.choose_address:
                ChangeAddressPopwindow mChangeAddressPopwindow = new ChangeAddressPopwindow(Ac_Essential_Information.this);
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
            case R.id.validity_of_a_certificate:
                DatePickerDialog dateDlg = new DatePickerDialog(Ac_Essential_Information.this, date,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH));
                dateDlg.show();
                break;
        }
    }
}
