package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;

import org.w3c.dom.Text;

/**
 * Created by Administrator
 * 意见反馈
 */

public class Feedback extends BaseActivity {
    private EditText mEt_content;
    private int num = 200;//限制的最大字数
    private TextView mTv_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("意见反馈");
        FindViewById();
        Init();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_feedback;
    }

    private void FindViewById() {
        mEt_content = (EditText) findViewById(R.id.et_content);
        mTv_num = (TextView) findViewById(R.id.tv_num);
    }

    //监听EditText输入的文字数
    private void Init() {
        mEt_content.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int selectionStart;
            private int selectionEnd;

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp = s;
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = s.length();
                mTv_num.setText(number + "");
                selectionStart = mEt_content.getSelectionStart();
                selectionEnd = mEt_content.getSelectionEnd();
                if (temp.length() > num) {
                    s.delete(selectionStart - 1, selectionEnd);
                    int tempSelection = selectionStart;
                    mEt_content.setText(s);
                    mEt_content.setSelection(tempSelection);//设置光标在最后
                }
            }
        });
    }

}
