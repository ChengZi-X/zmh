package com.zmh.zz.zmh.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.login.Ac_Login;
import com.zmh.zz.zmh.utlis.ClearAll;
import com.zmh.zz.zmh.utlis.ToastUtils;

/**
 * Created by Administrator on 2017/8/2.
 * 设置
 */

public class Ac_Setting extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout mPersonal_information, mAccount_security, mClear_cache, mIn_regard_to,mTitle_back;
    ClearAll clearAll;
    private String size;
    private TextView mCache, mLog_out, toolbartitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_setting);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("设置");
        TextPaint tp1 = toolbartitle.getPaint();
        tp1.setFakeBoldText(true);
        mLog_out = (TextView) findViewById(R.id.log_out);
        TextPaint tp2 = mLog_out.getPaint();
        tp2.setFakeBoldText(true);
        mTitle_back = (RelativeLayout) findViewById(R.id.title_back);
        mPersonal_information = (RelativeLayout) findViewById(R.id.personal_information);
        mAccount_security = (RelativeLayout) findViewById(R.id.account_security);
        mIn_regard_to = (RelativeLayout) findViewById(R.id.in_regard_to);
        mClear_cache = (RelativeLayout) findViewById(R.id.clear_cache);
        mCache = (TextView) findViewById(R.id.cache);
        mLog_out = (TextView) findViewById(R.id.log_out);
        mPersonal_information.setOnClickListener(this);
        mAccount_security.setOnClickListener(this);
        mIn_regard_to.setOnClickListener(this);
        mClear_cache.setOnClickListener(this);
        mLog_out.setOnClickListener(this);
        mTitle_back.setOnClickListener(this);
        Init();
    }

    private void Init() {
        try {
            size = ClearAll.getTotalCacheSize(Ac_Setting.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mCache.setText(size);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personal_information:
                startActivity(new Intent(Ac_Setting.this, Ac_Personal_Information.class));
                break;
            case R.id.account_security:
                startActivity(new Intent(Ac_Setting.this, Ac_Account_Security.class));
                break;
            case R.id.in_regard_to:
                startActivity(new Intent(Ac_Setting.this, Ac_In_Regard_To.class));
                break;
            case R.id.clear_cache:
                clear();//清除缓存
                break;
            case R.id.log_out:
                Log_Out();//退出登录
                break;
            case R.id.title_back:
                finish();
                break;
        }
    }

    private void Log_Out() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(Ac_Setting.this);
        dialog.setTitle("提示");
        dialog.setMessage("是否要切换账号吗？");
        dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {// 确定按钮的响应事件
                SharedPreferences spout = Ac_Setting.this.getSharedPreferences("userInfo", 0);
                SharedPreferences.Editor ed = spout.edit();
                ed.clear();//清除账号的缓存
                ed.commit();
                Intent intent = new Intent(Ac_Setting.this, Ac_Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
        dialog.setNegativeButton("否", null).show();
    }

    private void clear() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(Ac_Setting.this);
        dialog.setTitle("提示");
        dialog.setMessage("是否清除缓存？");
        dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                clearAll.clearAllCache(Ac_Setting.this);
                ToastUtils.showToast(Ac_Setting.this, "清除完成");
                mCache.setText("0KB");
            }
        });
        dialog.setNegativeButton("否", null).show();
    }
}
