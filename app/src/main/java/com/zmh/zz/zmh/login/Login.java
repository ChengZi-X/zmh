package com.zmh.zz.zmh.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.TextPaint;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.zmh.zz.zmh.LoadingDialog.ShapeLoadingDialog;
import com.zmh.zz.zmh.MainActivity;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.httpurls.HttpURLs;
import com.zmh.zz.zmh.modeljson.LoginJson;
import com.zmh.zz.zmh.utils.RegularUtil;
import com.zmh.zz.zmh.utils.MD5Util;
import com.zmh.zz.zmh.utils.MyStringCallBack;
import com.zmh.zz.zmh.utils.OkHttpUtil;
import com.zmh.zz.zmh.utils.SharedPreferencesUtil;
import com.zmh.zz.zmh.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;


/**
 * Created by Administrator
 * 登录
 */
public class Login extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences sp;
    private OkHttpUtil okHttp = new OkHttpUtil();
    private String mUserNameValue, mPasswordValue;
    private TextView Tv_TitleName;
    private EditText Et_UserName, Et_Password;// 账号,密码
    private Button But_Login, But_ForgetPassword, But_Register;//登录,忘记密码,注册

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransparentTitleBar();
        setContentView(R.layout.ac_login);
        Tv_TitleName = (TextView) findViewById(R.id.tv_title_name);
        Tv_TitleName.setText("登录");
        TextPaint tp = Tv_TitleName.getPaint();
        tp.setFakeBoldText(true);
        Et_UserName = (EditText) findViewById(R.id.et_username);
        Et_Password = (EditText) findViewById(R.id.et_password);
        But_Login = (Button) findViewById(R.id.but_login);
        But_ForgetPassword = (Button) findViewById(R.id.but_forget_password);
        But_Register = (Button) findViewById(R.id.but_register);
        Et_UserName.setFilters(new InputFilter[]{RegularUtil.filter});
        Et_Password.setFilters(new InputFilter[]{RegularUtil.filter});
        But_Login.setOnClickListener(this);
        But_ForgetPassword.setOnClickListener(this);
        But_Register.setOnClickListener(this);
        //第二次无需输入账号和密码直接登录
        sp = getSharedPreferences("UserInfo", 0);
        String name = sp.getString("USERNAME", "");
        String pass = sp.getString("PASSWORD", "");
        Et_UserName.setText(name);
        Et_Password.setText(pass);
        if (!name.equals("") && !pass.equals("")) {
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        mUserNameValue = Et_UserName.getText().toString();
        mPasswordValue = Et_Password.getText().toString();
        switch (v.getId()) {
            case R.id.but_login:
                startActivity(new Intent(Login.this, MainActivity.class));
                finish();
//                if (mUserNameValue.equals("")) {
//                    ToastUtils.showToast(Login.this, "手机号不能为空");
//                } else if (!RegularUtil.isMobileNO(mUserNameValue)) {
//                    ToastUtils.showToast(Login.this, "手机号格式不正确");
//                } else if (mPasswordValue.equals("")) {
//                    ToastUtils.showToast(Login.this, "密码不能为空");
//                } else {
//                    LOGIN();// 登录
//                }
                break;
            case R.id.but_forget_password:
                startActivity(new Intent(Login.this, ForgetPassword.class));//忘记密码
                break;
            case R.id.but_register:
                startActivity(new Intent(Login.this, Register.class));//注册
                break;
        }
    }

    /**
     * 登录
     */
    private void LOGIN() {
        mUserNameValue = Et_UserName.getText().toString();
        mPasswordValue = Et_Password.getText().toString();
        final ShapeLoadingDialog shapeLoadingDialog = new ShapeLoadingDialog(Login.this);
        shapeLoadingDialog.setCancelable(false);
        shapeLoadingDialog.setLoadingText("登录中,请稍等...");
        shapeLoadingDialog.show();
        final SharedPreferences.Editor editor = sp.edit();
        String url = HttpURLs.LOGIN;
        Map<String, String> params = new HashMap<>();
        params.put("loginname", mUserNameValue);
        params.put("password", MD5Util.MD5(mPasswordValue, 32));
        okHttp.postRequest(url, params, new MyStringCallBack() {
            @Override
            public void onResponse(String response, int id) {
                Log.e("sssss>>>", response);
//              JsonResult<JSONObject> jsonRet = new JsonResult<>();
//              JSONObject Result = jsonRet.getData();
                LoginJson login = JSONObject.parseObject(response, LoginJson.class);
                int code = login.getCode();
                String desc = login.getDesc();
                switch (code) {
                    case 200:
                        shapeLoadingDialog.dismiss();
                        ToastUtils.showToast(Login.this, "登录成功");
                        String UserId = login.getData().getId();//ID
                        String UserEmail = login.getData().getEmail();//邮箱
                        SharedPreferencesUtil.setParam(Login.this, "UserID", UserId);
                        SharedPreferencesUtil.setParam(Login.this, "UserEmail", UserEmail);
                        SharedPreferencesUtil.setParam(Login.this, "UserName", mUserNameValue);
                        //保存用户名和密码
                        editor.putString("USERNAME", mUserNameValue);
                        editor.putString("PASSWORD", mPasswordValue);
                        editor.commit();
                        startActivity(new Intent(Login.this, MainActivity.class));
                        finish();
                        break;
                    case 400:
                        shapeLoadingDialog.dismiss();
                        ToastUtils.showToast(Login.this, desc);
                        break;
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                shapeLoadingDialog.dismiss();
                Toast.makeText(Login.this, R.string.ConnectionError, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 登录
     */
//    private void LOGIN() {
//        // 默认可登录帐号123,密码123
//        userNameValue = mEt_UserName.getText().toString();
//        passwordValue = mEt_Password.getText().toString();
//        SharedPreferences.Editor editor = sp.edit();
//        if (userNameValue.equals("123") && passwordValue.equals("123")) {
//            ToastUtils.showToast(Login.this, "登录成功");
//            //保存用户名和密码
//            editor.putString("USER_NAME", userNameValue);
//            editor.putString("PASSWORD", passwordValue);
//            editor.commit();
//            //跳转
//            startActivity(new Intent(Login.this, MainActivity.class));
//            finish();
//        } else {
//            ToastUtils.showToast(Login.this, "用户名或密码错误，请重新输入");
//        }
//    }


    /**
     * 登录
     */
//    private void LOGIN() {
//        MyDialog = new ProgressDialog(this);
//        //依次设置标题,内容,是否用取消按钮关闭,是否显示进度
//        MyDialog.setTitle("登录");
//        MyDialog.setMessage("登录中，请稍后...");
//        MyDialog.setCancelable(false);
//        //这里是设置进度条的风格,HORIZONTAL是水平进度条,SPINNER是圆形进度条
//        MyDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        MyDialog.setIndeterminate(true);
//        //调用show()方法将ProgressDialog显示出来
//        MyDialog.show();
//        userNameValue = mEt_zhanghao.getText().toString();
//        passwordValue = mEt_mima.getText().toString();
//        String url = "http://" + SharedPreferencesUtil.getParam(Ac_Login.this, "IP", "") + ":" + SharedPreferencesUtil.getParam(Ac_Login.this, "PORT", "") + "/mes-service/SystemManageServer/User/loginApp";
//        HttpUtils http = new HttpUtils();
//        http.configCurrentHttpCacheExpiry(1000);
//        RequestParams params = new RequestParams();
//        params.addBodyParameter("account", userNameValue);
//        params.addBodyParameter("password", MD5Utils.MD5(passwordValue, 32));
//        http.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {
//            @Override
//            public void onSuccess(ResponseInfo<String> responseInfo) {
//                String s = responseInfo.result;
//                Log.e("---sssssssssssss", s);
//                LoginJson li = JSONObject.parseObject(s, LoginJson.class);
//                String role = li.getRole();
//                String userId = li.getUserId();
//                SharedPreferencesUtil.setParam(Ac_Login.this, "role", role + "");
//                SharedPreferencesUtil.setParam(Ac_Login.this, "userId", userId + "");
//                Log.e("ssssss", role + "");
//                switch (li.getResult()) {
//                    case "success":
//                        MyDialog.dismiss();
//                        SharedPreferences.Editor editor = sp.edit();
//                        // 保存用户名和密码
//                        editor.putString("USER_NAME", userNameValue);
//                        editor.putString("PASSWORD", passwordValue);
//
//                        // 是否记住密码
//                        if (mCb_jizhumima.isChecked()) {
//                            editor.putBoolean("remember", true);
//                        } else {
//                            editor.putBoolean("remember", false);
//                        }
//                        // 是否自动登录
//                        if (mCb_zidongdenglu.isChecked()) {
//                            editor.putBoolean("autologin", true);
//                            mCb_jizhumima.setChecked(true);
//                        } else {
//                            editor.putBoolean("autologin", false);
//                        }
//                        editor.commit();
//                        // 跳转
//                        Intent intent = new Intent(Ac_Login.this, MainActivity.class);
//                        startActivity(intent);
//                        finish();
//                        break;
//                    default:
//                        ToastUtils.showToast(Ac_Login.this, "账号或密码错误");
//                        MyDialog.dismiss();
//                        break;
//                }
//            }
//
//            @Override
//            public void onFailure(HttpException e, String s) {
//                MyDialog.dismiss();
//                Toast.makeText(Ac_Login.this, "网络连接失败，请查询网络", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

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

    /**
     * 透明标题栏
     */
    private void TransparentTitleBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }
}