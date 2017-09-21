package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.TextView;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;


/**
 * Created by Administrator
 * 关于
 */

public class InRegardTo extends BaseActivity implements View.OnClickListener {
    private TextView Tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("关于");
        FindViewById();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_in_regard_to;
    }

    private void FindViewById() {
        Tv = (TextView) findViewById(R.id.tv);
        TextPaint tp = Tv.getPaint();
        tp.setFakeBoldText(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
    }
}
