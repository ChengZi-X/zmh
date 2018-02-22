package com.zmh.zz.zmh.mainfragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Toast;

import com.github.shenyuanqing.zxingsimplify.zxing.Activity.CaptureActivity;
import com.zmh.zz.zmh.BaseFragment;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.activity.WebActivity;
import com.zmh.zz.zmh.utils.ToastUtils;

/**
 * 主页
 */
public class FragmentHomepage extends BaseFragment {
    private static final int REQUEST_SCAN = 0;
    private View view;
    private String scanResult;

    @Override
    public View initView() {
        view = View.inflate(getActivity(), R.layout.fragment_homepage, null);
        setNotificationBar(R.color.babyblue);
        init();
        return view;
    }

    private void init() {
        view.findViewById(R.id.ll_scan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRuntimeRight();
            }
        });
    }

    /**
     * 获得运行时权限
     */
    private void getRuntimeRight() {
        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ToastUtils.showToast(getActivity(), "请手动添加您的权限");
        } else {
            startActivityForResult(new Intent(getActivity(), CaptureActivity.class), REQUEST_SCAN);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SCAN && resultCode == getActivity().RESULT_OK) {
            Bundle bundle = data.getExtras();
            //将扫描出的信息显示出来
            scanResult = bundle.getString("barCode");
            initData();
        }
    }

    public void initData() {
        if (Patterns.WEB_URL.matcher(scanResult).matches() || URLUtil.isValidUrl(scanResult)) {
            Intent intent = new Intent(getActivity(), WebActivity.class);
            intent.putExtra("webUrl", scanResult);
            startActivity(intent);
            Log.e(">>>>", "是连接");
            Toast.makeText(getActivity(), scanResult, Toast.LENGTH_LONG).show();
        } else {
            Log.e(">>>>", "是文本");
            Toast.makeText(getActivity(), scanResult, Toast.LENGTH_LONG).show();
        }
    }
}
