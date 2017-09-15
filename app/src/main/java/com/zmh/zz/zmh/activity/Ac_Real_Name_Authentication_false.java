package com.zmh.zz.zmh.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.ChangeAddressPopwindow;
import com.zmh.zz.zmh.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * Created by Administrator
 * 实名认证_false
 */

public class Ac_Real_Name_Authentication_false extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout mSex, mCertificate, mTitle_back;
    private TextView mMen_and_women, mChoose_address, toolbartitle, mTitle_submit;
    private EditText mDate;
    int which = 0;
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
        setContentView(R.layout.ac_real_name_authentication_false);
        mTitle_back = (RelativeLayout) findViewById(R.id.title_back);
        mTitle_back.setOnClickListener(this);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("实名认证");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        mTitle_submit = (TextView) findViewById(R.id.title_submit);
        mTitle_submit.setText("提交");
        TextPaint tp1 = mTitle_submit.getPaint();
        tp1.setFakeBoldText(true);
        mTitle_submit.setOnClickListener(this);
        mSex = (RelativeLayout) findViewById(R.id.sex);
        mCertificate = (RelativeLayout) findViewById(R.id.validity_of_a_certificate);
        mMen_and_women = (TextView) findViewById(R.id.men_and_women);
        mDate = (EditText) findViewById(R.id.date);
        mChoose_address = (TextView) findViewById(R.id.choose_address);
        mSex.setOnClickListener(this);
        mCertificate.setOnClickListener(this);
        mChoose_address.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_submit:
                AlertDialog.Builder dialog = new AlertDialog.Builder(Ac_Real_Name_Authentication_false.this);
                dialog.setTitle("提示");
                dialog.setMessage("\r\r\r\r\r\r\r\r您的资料已提交,请耐心等待,我们将在1-2个工作日人审核完成。");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {// 确定按钮的响应事件
                        dialog.dismiss();
                    }
                });
                dialog.setCancelable(false);
                dialog.show();
                break;
            case R.id.sex:
                AlertDialog.Builder dialog1 = new AlertDialog.Builder(Ac_Real_Name_Authentication_false.this);
                dialog1.setTitle("性别");
                if (mMen_and_women.getText().toString().equals("男")) {
                    which = 0;
                } else if (mMen_and_women.getText().toString().equals("女")) {
                    which = 1;
                }
                dialog1.setSingleChoiceItems(new String[]{"男", "女"}, which, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            mMen_and_women.setText("男");
                        } else if (which == 1) {
                            mMen_and_women.setText("女");
                        }
                        if (!mMen_and_women.getText().toString().equals("请选择")) {
                            mMen_and_women.setTextColor(getResources().getColor(R.color.absolute_black));//通过获得资源文件进行设置。
                        }
                        dialog.dismiss();
                    }
                }).show();
                break;
            case R.id.validity_of_a_certificate:
                DatePickerDialog dateDlg = new DatePickerDialog(Ac_Real_Name_Authentication_false.this, date,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH));
                dateDlg.show();
                break;
            case R.id.choose_address:
                ChangeAddressPopwindow mChangeAddressPopwindow = new ChangeAddressPopwindow(Ac_Real_Name_Authentication_false.this);
                mChangeAddressPopwindow.setAddress("河南", "郑州", "金水区");
                mChangeAddressPopwindow.showAtLocation(mChoose_address, Gravity.BOTTOM, 0, 0);
                mChangeAddressPopwindow
                        .setAddresskListener(new ChangeAddressPopwindow.OnAddressCListener() {
                            @Override
                            public void onClick(String province, String city, String area) {
                                mChoose_address.setText(province + " " + city + " " + area);
                                if (!mChoose_address.getText().toString().equals("请选择")) {
                                    mChoose_address.setTextColor(getResources().getColor(R.color.absolute_black));//通过获得资源文件进行设置。
                                }
                            }
                        });
                break;
            case R.id.title_back:
                finish();
                break;
        }
    }
}
