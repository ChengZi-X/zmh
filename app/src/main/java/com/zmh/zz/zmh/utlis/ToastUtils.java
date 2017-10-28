package com.zmh.zz.zmh.utlis;

import android.content.Context;
import android.widget.Toast;

/**
 * 弹吐司
 */

public class ToastUtils {
    private static Toast toast;

    public static void showToast(Context context, String content) {
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

}
