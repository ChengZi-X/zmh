package com.zmh.zz.zmh;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/9/15.
 */

public abstract class BaseActivity extends AppCompatActivity implements TextWatcher {
    public ViewGroup contentView;
    private TextView titltTv, rightBtn;
    private View leftBtn, titlebar, bottom_view;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_white);
        int titlebarResId = getTitlebarResId();
        if (titlebarResId != 0) {
            LinearLayout view = (LinearLayout) findViewById(R.id.base_view);
            view.removeViewAt(0);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            ViewGroup titleView = (ViewGroup) View.inflate(this, titlebarResId, null);
            view.addView(titleView, 0, lp);
            view.setBackgroundDrawable(titleView.getBackground());
            titlebar = titleView;
        } else {
            bottom_view = findViewById(R.id.base_bottom_view);
            titlebar = findViewById(R.id.base_titlebar);
            leftBtn = findViewById(R.id.base_back_btn);
            back = (ImageView) findViewById(R.id.base_back);
            titltTv = (TextView) findViewById(R.id.base_title_tv);
            TextPaint tp = titltTv.getPaint();
            tp.setFakeBoldText(true); //字体加粗
            rightBtn = (TextView) findViewById(R.id.base_menu_btn);
            TextPaint tp1 = rightBtn.getPaint();
            tp1.setFakeBoldText(true);//字体加粗
            rightBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickRight();
                }
            });
            leftBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickLeft();
                }
            });
        }
        contentView = (ViewGroup) findViewById(R.id.base_contentview);
        contentView.addView(View.inflate(this, getContentView(), null));
        setRightBtnVisible(false);
    }

    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    /**
     * 隐藏软件盘方法的其中一种
     */
    public void hideSoftInput(View view) {
        if (view != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
        }
    }

    /**
     * 设置状态栏背景状态
     */
    public void setNotificationBar(int Color) {
        //因为不是所有的系统都可以设置颜色的，在4.4以下就不可以。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(Color));
        }
    }

    /**
     * 获取自定义标题栏
     * 如果子类复写并返回不等于0的布局文件，将会覆盖默认标题
     * 返回0 将会采用默认标题
     *
     * @return
     */
    protected int getTitlebarResId() {
        return 0;
    }

    /**
     * 点击左侧按钮
     */
    protected void onClickLeft() {
        finish();
    }

    /**
     * 点击右侧按钮
     * 默认什么都不做
     */
    protected void onClickRight() {
    }

    /**
     * 设置左侧按钮箭头颜色
     *
     * @param Color
     */
    public void setLeftbtnColor(int Color) {
        back.setImageDrawable(getResources().getDrawable((Color)));
    }

    /**
     * 设置左侧按钮显示与隐藏
     *
     * @param visible
     */
    public void setLeftBtnVisible(Boolean visible) {
        if (leftBtn != null) {
            if (visible) {
                leftBtn.setVisibility(View.VISIBLE);
            } else {
                leftBtn.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 设置右侧按钮显示与隐藏
     *
     * @param visible
     */
    public void setRightBtnVisible(Boolean visible) {
        if (rightBtn != null) {
            if (visible) {
                rightBtn.setVisibility(View.VISIBLE);
            } else {
                rightBtn.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 设置底部线显示与隐藏
     *
     * @param visible
     */
    public void setBottomViewVisible(Boolean visible) {
        if (bottom_view != null) {
            if (visible) {
                bottom_view.setVisibility(View.VISIBLE);
            } else {
                bottom_view.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 设置Title背景颜色
     *
     * @param Color
     */
    public void setTitleBackgroundColor(int Color) {
        titlebar.setBackgroundColor(Color);
    }

    /**
     * 设置左边标题
     *
     * @param title
     */
    public void setLtTitle(String title) {
        if (titltTv != null) {
            if (titltTv != null) {
                titltTv.setText(title);
            }
        }
    }

    /**
     * 设置左边标题颜色
     *
     * @param Color
     */
    public void setLtTitleTvColor(int Color) {
        if (titltTv != null) {
            if (titltTv != null) {
                titltTv.setTextColor(getResources().getColor(Color));
            }
        }
    }

    /**
     * 设置右边文字属性
     *
     * @param title
     */
    public void setRtTitle(String title) {
        if (rightBtn != null) {
            rightBtn.setText(title);
        }
    }

    /**
     * 设置右边文字颜色
     *
     * @param Color
     */
    public void setRtTitleTvColor(int Color) {
        if (rightBtn != null) {
            rightBtn.setTextColor(getResources().getColor(Color));
        }
    }

    public View getTitleBar() {

        return titlebar;
    }

    /**
     * 获取中间内容显示区
     *
     * @return
     */
    protected abstract int getContentView();
    @Override
    public void afterTextChanged(Editable s) {}
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

}