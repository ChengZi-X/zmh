package com.zmh.zz.zmh.utlis;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextPaint;
import android.widget.TextView;

import com.zmh.zz.zmh.R;

/**
 * Created by Administrator on 2017/9/27.
 */

public class IntegralSignDialog extends Dialog {
    private TextView mIntegral_one;
    private TextView mIntegral_two;
    private Context mContext;
    private String integral_one;
    private String integral_two;

    public IntegralSignDialog(Context context, String integral_one, String integral_two) {
        super(context, R.style.dialog);
        this.mContext = context;
        this.integral_one = integral_one;
        this.integral_two = integral_two;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_integral_sign_dialog);
        setCanceledOnTouchOutside(true);
        initView();
    }

    private void initView() {
        mIntegral_one = (TextView) findViewById(R.id.integral_one);
        mIntegral_two = (TextView) findViewById(R.id.integral_two);
        TextPaint tp = mIntegral_two.getPaint();
        tp.setFakeBoldText(true); //字体加粗
        mIntegral_one.setText(integral_one);
        mIntegral_two.setText(integral_two);
    }

}