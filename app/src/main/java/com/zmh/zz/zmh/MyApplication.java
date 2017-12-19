package com.zmh.zz.zmh;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;

/**
 * Created by Sun-PC on 2017/12/18.
 */

public class MyApplication extends Application {
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onCreate() {
        super.onCreate();
        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }
}
