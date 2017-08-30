package com.zmh.zz.zmh.splash;

/**
 * Created by Administrator on 2017/7/10.
 * 闪屏
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.login.Ac_Login;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private TextView mSkip;
    private MyCountDownTimer mCDT;
    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mSkip = (TextView) findViewById(R.id.tv_skip);
        mTimer = new Timer();
        mCDT = new MyCountDownTimer(4000, 1000);
        mCDT.start();
        mTimer.schedule(new MyTimerTask(), 2500);
        mSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimer.cancel();
                startActivity(new Intent(SplashActivity.this, Ac_Login.class));
                SplashActivity.this.finish();
            }
        });
    }

    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            startActivity(new Intent(SplashActivity.this, Ac_Login.class));
            SplashActivity.this.finish();
        }
    }

    class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            mSkip.setText("跳过(" + l / 1000 + "秒)...");
        }

        @Override
        public void onFinish() {
            //mSkip.setText("跳过(0秒)...");
        }
    }

    /**
     * 再按一次退出程序
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            finish();
            System.exit(0);
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}