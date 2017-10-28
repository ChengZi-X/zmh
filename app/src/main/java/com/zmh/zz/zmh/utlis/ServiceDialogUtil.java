package com.zmh.zz.zmh.utlis;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zmh.zz.zmh.R;


/**
 * 在线客服自定义DiaLog
 */
public class ServiceDialogUtil extends Dialog implements View.OnClickListener {
    private TextView contentTxt;
    private TextView titleTxt;
    private TextView submitTxt;
    private TextView cancelTxt;

    private OnCloseListener listener;
    private Context mContext;
    private String content;
    private String title;

    public ServiceDialogUtil(Context context, String content, OnCloseListener listener) {
        super(context, R.style.dialog);
        this.mContext = context;
        this.content = content;
        this.listener = listener;
    }

    public ServiceDialogUtil setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_service_dialog);
        setCanceledOnTouchOutside(true);
        initView();
    }

    private void initView() {
        contentTxt = (TextView) findViewById(R.id.content);
        titleTxt = (TextView) findViewById(R.id.title);
        submitTxt = (TextView) findViewById(R.id.submit);
        cancelTxt = (TextView) findViewById(R.id.cancel);
        submitTxt.setOnClickListener(this);
        cancelTxt.setOnClickListener(this);
        contentTxt.setText(content);
        titleTxt.setText(title);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                if (listener != null) {
                    listener.onClick(this, false);
                }
                this.dismiss();
                break;
            case R.id.submit:
                if (listener != null) {
                    listener.onClick(this, true);
                }
                break;
        }
    }

    public interface OnCloseListener {
        void onClick(Dialog dialog, boolean confirm);
    }
}
