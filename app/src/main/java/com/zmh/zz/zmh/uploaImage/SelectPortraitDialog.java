package com.zmh.zz.zmh.uploaImage;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.zmh.zz.zmh.R;

public class SelectPortraitDialog extends Dialog implements View.OnClickListener {

    private Context mContext;
    private ClickListenerInterface clickListenerInterface;

    public interface ClickListenerInterface {
        void photograph();

        void photo();
    }

    public SelectPortraitDialog(Context context) {
        super(context, R.style.dialog);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_im_head_portrait_dialog);
        init();
    }

    public void init() {
        RelativeLayout tvPhotograph = (RelativeLayout) findViewById(R.id.photograph);
        RelativeLayout tvPhoto = (RelativeLayout) findViewById(R.id.photo);
        tvPhotograph.setOnClickListener(this);
        tvPhoto.setOnClickListener(this);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = mContext.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.8
        dialogWindow.setAttributes(lp);
    }

    public void setClicklistener(ClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.photograph:
                clickListenerInterface.photograph();
                break;
            case R.id.photo:
                clickListenerInterface.photo();
                break;
        }

    }
}