package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.utlis.ToastUtils;


/**
 * Created by Administrator on 2017/8/4.
 * 账号与安全_改绑新邮箱
 */

public class Ac_New_Mailbox extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    private Button mBut_binding;
    private EditText mMailbox;
    private TextView toolbartitle;
    private ImageView mTitle_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_new_mailbox);
        mBut_binding = (Button) findViewById(R.id.but_binding);
        mMailbox = (EditText) findViewById(R.id.mailbox);
        toolbartitle = (TextView) findViewById(R.id.title_tool);
        toolbartitle.setText("改绑邮箱");
        TextPaint tp = toolbartitle.getPaint();
        tp.setFakeBoldText(true);
        mBut_binding.setOnClickListener(this);
        mMailbox.addTextChangedListener(this);
        mTitle_back = (ImageView) findViewById(R.id.title_back);
        mTitle_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_binding:
                ToastUtils.showToast(Ac_New_Mailbox.this, "重新绑定成功");
                break;
            case R.id.title_back:
                finish();
                break;
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (mMailbox.getText().toString().length() > 0) {
            mBut_binding.setEnabled(true);
        } else {
            mBut_binding.setEnabled(false);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }


}
