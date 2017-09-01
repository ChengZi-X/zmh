package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zmh.zz.zmh.R;


/**
 * Created by Administrator on 2017/8/7.
 * 关于
 */

public class Ac_In_Regard_To extends AppCompatActivity implements View.OnClickListener {
    private TextView Tv, toolbartitle;
    private RelativeLayout mTitle_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_in_regard_to);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("关于");
        TextPaint tp1 = toolbartitle.getPaint();
        tp1.setFakeBoldText(true);
        Tv = (TextView) findViewById(R.id.tv2);
        TextPaint tp2 = Tv.getPaint();
        tp2.setFakeBoldText(true);
        mTitle_back = (RelativeLayout) findViewById(R.id.title_back);
        mTitle_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
        }
    }
}
