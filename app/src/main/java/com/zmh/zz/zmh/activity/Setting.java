package com.zmh.zz.zmh.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextPaint;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.login.Login;
import com.zmh.zz.zmh.utlis.ClearAll;
import com.zmh.zz.zmh.utlis.ToastUtils;

/**
 * Created by Administrator
 * 设置
 */

public class Setting extends BaseActivity implements View.OnClickListener {
    private RelativeLayout mPersonal_information, mAccount_security, mClear_cache, mIn_regard_to;
    ClearAll clearAll;
    private String size;
    private TextView mCache, mLog_out;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("设置");
        FindViewById();
        Init();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_setting;
    }

    private void FindViewById() {
        mLog_out = (TextView) findViewById(R.id.log_out);
        TextPaint tp = mLog_out.getPaint();
        tp.setFakeBoldText(true);
        mPersonal_information = (RelativeLayout) findViewById(R.id.personal_information);
        mAccount_security = (RelativeLayout) findViewById(R.id.account_security);
        mIn_regard_to = (RelativeLayout) findViewById(R.id.in_regard_to);
        mClear_cache = (RelativeLayout) findViewById(R.id.clear_cache);
        mCache = (TextView) findViewById(R.id.cache);
        mPersonal_information.setOnClickListener(this);
        mAccount_security.setOnClickListener(this);
        mIn_regard_to.setOnClickListener(this);
        mClear_cache.setOnClickListener(this);
        mLog_out.setOnClickListener(this);
    }

    private void Init() {
        try {
            size = ClearAll.getTotalCacheSize(Setting.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mCache.setText(size);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personal_information:
                startActivity(new Intent(Setting.this, PersonalInformation.class));
                break;
            case R.id.account_security:
                startActivity(new Intent(Setting.this, AccountSecurity.class));
                break;
            case R.id.in_regard_to:
                startActivity(new Intent(Setting.this, InRegardTo.class));
                break;
            case R.id.clear_cache:
                clear();//清除缓存
                break;
            case R.id.log_out:
                Log_Out();//退出登录
                break;
        }
    }

    private void Log_Out() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(Setting.this);
        dialog.setTitle("提示");
        dialog.setMessage("是否要切换账号吗？");
        dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {// 确定按钮的响应事件
                SharedPreferences spout = Setting.this.getSharedPreferences("userInfo", 0);
                SharedPreferences.Editor ed = spout.edit();
                ed.remove("PASSWORD").commit();//清除账号缓存
                Intent intent = new Intent(Setting.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
        dialog.setNegativeButton("否", null).show();
    }

    private void clear() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(Setting.this);
        dialog.setTitle("提示");
        dialog.setMessage("是否清除缓存？");
        dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                clearAll.clearAllCache(Setting.this);
                ToastUtils.showToast(Setting.this, "清除完成");
                mCache.setText("0KB");
            }
        });
        dialog.setNegativeButton("否", null).show();
    }
}
