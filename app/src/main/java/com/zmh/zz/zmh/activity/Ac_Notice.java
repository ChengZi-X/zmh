package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zmh.zz.zmh.R;


/**
 * Created by vimi8 on 2017/4/18.
 */

public class Ac_Notice extends AppCompatActivity implements View.OnClickListener {
    private TextView toolbartitle;
    private ImageView toolbarback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_notice);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("公告");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        toolbarback = (ImageView) findViewById(R.id.title_back);
        toolbarback.setOnClickListener(this);
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