package com.zmh.zz.zmh;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.zmh.zz.zmh.activity.PersonalInformation;
import com.zmh.zz.zmh.mainfragment.FragmentBusiness;
import com.zmh.zz.zmh.mainfragment.FragmentFund;
import com.zmh.zz.zmh.mainfragment.FragmentHomepage;
import com.zmh.zz.zmh.mainfragment.FragmentMy;
import com.zmh.zz.zmh.utils.ToastUtils;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {
    private static final int CAMERA_OK = 200;
    private UpdateManager updateManager;
    private BottomNavigationBar mBottomNavigationBar;
    private FragmentHomepage mFragmentHomepage;
    private FragmentBusiness mFragmentBusiness;
    private FragmentFund mFragmentFund;
    private FragmentMy mFragmentMy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //先判断拍照和读取SD卡的权限是否已经申请过权限;
        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //先判断有没有权限 ，没有就在这里进行权限的申请
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_OK);
        }
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
//      mBottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
//      mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
//      mBottomNavigationBar.setBarBackgroundColor(R.color.blue);//设置导航栏的背景颜色
//      mBottomNavigationBar.setInActiveColor(R.color.white);//没有图标的颜色
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.icon_one, R.string.tab_Homepage).setActiveColorResource(R.color.green))
                .addItem(new BottomNavigationItem(R.drawable.icon_two, R.string.tab_Business).setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.icon_three, R.string.tab_Fund).setActiveColorResource(R.color.lime))
                .addItem(new BottomNavigationItem(R.drawable.icon_four, R.string.tab_My).setActiveColorResource(R.color.babyblue))
                .setFirstSelectedPosition(0)
                .initialise();
        mBottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();
//        updateManager = new UpdateManager(MainActivity.this);
//        //检查更新
//        updateManager.checkUpdate();
    }

    /**
     * 默认为第一个显示
     */
    private void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        mFragmentHomepage = new FragmentHomepage();
        transaction.replace(R.id.ll_content, mFragmentHomepage).commit();
    }

    @Override
    public void onTabSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                mFragmentHomepage = new FragmentHomepage();
                transaction.replace(R.id.ll_content, mFragmentHomepage);
                break;
            case 1:
                mFragmentBusiness = new FragmentBusiness();
                transaction.replace(R.id.ll_content, mFragmentBusiness);
                break;
            case 2:
                mFragmentFund = new FragmentFund();
                transaction.replace(R.id.ll_content, mFragmentFund);
                break;
            case 3:
                mFragmentMy = new FragmentMy();
                transaction.replace(R.id.ll_content, mFragmentMy);
                break;
            default:
                mFragmentHomepage = new FragmentHomepage();
                transaction.replace(R.id.ll_content, mFragmentHomepage);
                break;
        }
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {
    }

    @Override
    public void onTabReselected(int position) {
    }

    //二维码扫描需要的权限
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case CAMERA_OK:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    //这里是拒绝给APP摄像头权限，给个提示什么的说明一下就可以。
                    ToastUtils.showToast(MainActivity.this, "权限被禁止,将无法打开相机");
                }
                break;
        }
    }

    /**
     * 退出程序
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("提示！")
                        .setMessage("确定退出程序？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                System.exit(0);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();
                break;
        }
        return false;
    }
}
