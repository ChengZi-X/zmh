package com.zmh.zz.zmh.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.zmh.zz.zmh.MainActivity;
import com.zmh.zz.zmh.R;

import com.zmh.zz.zmh.utlis.ToastUtils;


/**
 * Created by Administrator on 2017/7/10.
 * 登录
 */
public class Ac_Login extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    private long m_exitTime = 1;// 连点两次退出程序
    private SharedPreferences sp;
    private String userNameValue, passwordValue;
    private ProgressDialog MyDialog = null;
    private TextView mTv_register, tv_forget_password;//注册,忘记密码
    private EditText mEt_UserName, mEt_Password;// 账号,密码
    private CheckBox mCb_AutomaticLogin;// 自动登录,记住密码
    private Button mBut_login; // 登录,

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_login);
        mEt_UserName = (EditText) findViewById(R.id.et_username);
        mEt_UserName.setFilters(new InputFilter[]{filter});
        mEt_Password = (EditText) findViewById(R.id.et_password);
        mEt_Password.setFilters(new InputFilter[]{filter});
        mCb_AutomaticLogin = (CheckBox) findViewById(R.id.cb_automaticlogin);
        mBut_login = (Button) findViewById(R.id.but_log);
        mTv_register = (TextView) findViewById(R.id.tv_register);
        tv_forget_password = (TextView) findViewById(R.id.tv_forget_password);
        tv_forget_password.setOnClickListener(this);
        mBut_login.setOnClickListener(this);
        mTv_register.setOnClickListener(this);
        mEt_UserName.addTextChangedListener(this);
        mEt_Password.addTextChangedListener(this);
        AutomaticLogin();
    }

    /**
     * 自动登录逻辑
     */
    private void AutomaticLogin() {
        sp = getSharedPreferences("userInfo", 0);
        String name = sp.getString("USER_NAME", "");
        String pass = sp.getString("PASSWORD", "");
        boolean choseAutoLogin = sp.getBoolean("autologin", false);
        mEt_UserName.setText(name);
        mEt_Password.setText(pass);
        if (choseAutoLogin) {
            mCb_AutomaticLogin.setChecked(true);
            startActivity(new Intent(Ac_Login.this, MainActivity.class));
            finish();
        } else {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_log:
                LOGIN();// 登录
                break;
            case R.id.tv_register:
                startActivity(new Intent(Ac_Login.this, Ac_Register.class));//注册
                break;
            case R.id.tv_forget_password:
                startActivity(new Intent(Ac_Login.this, Ac_Forget_Password.class));//忘记密码
                break;
        }
    }

    /**
     * 登录
     */
    private void LOGIN() {
        // 默认可登录帐号123,密码123
        userNameValue = mEt_UserName.getText().toString();
        passwordValue = mEt_Password.getText().toString();
        SharedPreferences.Editor editor = sp.edit();
        if (userNameValue.equals("123")
                && passwordValue.equals("123")) {
            ToastUtils.showToast(Ac_Login.this, "登录成功");
            //保存用户名和密码
            editor.putString("USER_NAME", userNameValue);
            editor.putString("PASSWORD", passwordValue);
            //是否自动登录
            if (mCb_AutomaticLogin.isChecked()) {
                editor.putBoolean("autologin", true);
            } else {
                editor.putBoolean("autologin", false);
            }
            editor.commit();
            //跳转
            startActivity(new Intent(Ac_Login.this, MainActivity.class));
            finish();
        } else {
            ToastUtils.showToast(Ac_Login.this, "用户名或密码错误，请重新输入");
        }
    }

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
//        String url = "http://" + SharedPreferencesUtils.getParam(Ac_Login.this, "IP", "") + ":" + SharedPreferencesUtils.getParam(Ac_Login.this, "PORT", "") + "/mes-service/SystemManageServer/User/loginApp";
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
//                SharedPreferencesUtils.setParam(Ac_Login.this, "role", role + "");
//                SharedPreferencesUtils.setParam(Ac_Login.this, "userId", userId + "");
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

    //禁止输入空格和换行
    private InputFilter filter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            if (source.equals(" ") || source.toString().contentEquals("\n")) return "";
            else return null;
        }
    };

    /**
     * 再按一次退出程序
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - m_exitTime) > 2000) {
                ToastUtils.showToast(Ac_Login.this, "再按一次退出程序");
                m_exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (mEt_UserName.getText().toString().length() > 0 && mEt_Password.getText().toString().length() > 0) {
            mBut_login.setEnabled(true);
        } else {
            mBut_login.setEnabled(false);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }
}