package com.zmh.zz.zmh.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.alipay.OrderInfoUtil2_0;
import com.zmh.zz.zmh.alipay.PayResult;
import com.zmh.zz.zmh.utils.SharedPreferencesUtil;
import com.zmh.zz.zmh.utils.ToastUtils;
import com.zmh.zz.zmh.wxapi.WxPayUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 * Created by Administrator
 * 账户充值
 */

public class AccountPut extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private RadioGroup Rg_Pay;
    private RadioButton Rb_Alipay, Rb_Wechat;
    private Button But_Next;
    private String mPay, mMoneyValue;
    private EditText Et_Money;
    private TextView Tv_Money;
    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APPID = "2017111509943415";
    /**
     * 支付宝账户登录授权业务：入参pid值
     */
    public static final String PID = "";
    /**
     * 支付宝账户登录授权业务：入参target_id值
     */
    public static final String TARGET_ID = "";
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCQLfBDT4Vv5S20yPiy6CmPsBSbPFC/eOXBLgQ0tfyhm1UwusxAeOx0orlilwOwWlsUUbO32ar/gVbMA0kBUTKuW3R7MAuJEU9f/PqM9SGr9dvsYWwtaNwhPrbXquIYfs1rN0EuScvPWs7Pc0i9ndVp42BiMDC06txswGP8tblkyLnlsiKXYLobh1KF/SOazOPNOu34lvKKYT/VxaZili+IHMr8p8axxFJPc/GdM6yG+aZEQQKL7ZAP1I7s6/2g2AiWFrVfCadC6aBG8B8WiAQaA1fhon8vWfYsS2/aoRWK2H8X/pWVzWgT0aSBjwD21G3TzZL7i6mq42ENjEpD/569AgMBAAECggEAYR/RHkbPaNVFuOc5QavLc5P2DazP6GwUudG525hMmsD83lq3hSbwgpGruJJqVb5IZKO1IUBsRLpoMf50i4KbVYEeMm3nF2qlwc+1KIdpK2HvyIjISr68q1iH6zieHkotLvCF5ByqQwC1GGJcuf886JvXLc2wwe8Ele30bSQtw4nAZaBQXbWbv2Cr0QZ2bo3uBBFIqxX9BrQ9Of2YVe/E/4LzGn30h8jM3QJ2UZEvRGxvGnd8PUGwr2xLRQPdV9cmsRJzxuYPSLlOagWHFFsU7SbjOemQh/6zyeLieRwBpKFMqrR/IUsEeqy9W8yN9M4/09ZNvOIgMC00iJise7NIsQKBgQDSeIPtVYeqmWMn9FQ6ITH/Yl66wA43L4kgbdqrzTIkiLjRqhW8yYbPYC452b9OhVKTni7Iesfuq3y/syX+NHIBrKkGfXyvB2DNVYKW0l1CktSW+YUgRMbgJAfmKDu/UCxdpuawPUFdgQujiWfWObOzNoWMi8PjTuZjykqNyCNfuwKBgQCvXlTugGpvwrxxuHOLTHFe09ftkKKpsx2vG3+HB712+SDVUOTxN8pYkhR0xunX9HTh2nnqKCsL9xcak+PP/fFF+hlLvTGjseTF14r7sgcfGBqwG+1X8ufpDehnXmS3wl4YmsR6ISs3YC3A5wYydGlHQUdd1sny7RwFC7LN4LBn5wKBgQCHSWwTzFyOLlZsQkM5ski/De4yQG1iz61nKPXcZOOMVE3oh1m9phR/jCVqjirIQC6aMJ2FxFZrddOULYWIZxG5PdDQGS1iHG3+zq5IYCQOPO9ltDH8Ufk77i0dplLxYImEEVpLgrRXdDGVBye1rhVyZuNlOhnKLNcu4cMWa8VBLQKBgB+YEBcT3Zka7VZTk8Vx1S7vXYR4PUXzdhKZEqWiGFk50td7hnOKyJKiZrsPJwv4B6K0+Kfd6rtyHaB1KmLLbk9cGniRskK6huu5xFlKHoxUjGJT9bOVmIBdOBR/ZgfRJaCrKWCTXzTPyA5r0KsAXAMxWIkobCOu4hBqpiD/LOjbAoGARlopRbqt3qgARudbZS/GmM1BfVIOgYPzS0hegXT3DSHEOo8sKVeT0klbKtuPr7mo4raQIiST3Y5uZEnm3fzkSM23IT8dGR2vBaZ1NeX1ypyNHhctqcLsvb/grLLFG5eUEmNB/NdfJQZtjd+PVBoJRuJyQgTNXrSpEKoWZswsiZ8=";
    public static final String RSA_PRIVATE = "";
    private static final int SDK_PAY_FLAG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("账户充值");
        FindViewById();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_account_put;
    }

    private void FindViewById() {
        Rg_Pay = (RadioGroup) findViewById(R.id.rg_pay);
        Rb_Alipay = (RadioButton) findViewById(R.id.rb_alipay);
        Rb_Wechat = (RadioButton) findViewById(R.id.rb_wechat);
        But_Next = (Button) findViewById(R.id.but_next);
        Et_Money = (EditText) findViewById(R.id.et_money);
        Tv_Money = (TextView) findViewById(R.id.tv_money);
        Et_Money.addTextChangedListener(this);
        Rg_Pay.setOnCheckedChangeListener(this);
        But_Next.setOnClickListener(this);
        Rb_Alipay.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (Rb_Alipay.getId() == checkedId) {
            mPay = "1";
        }
        if (Rb_Wechat.getId() == checkedId) {
            mPay = "2";
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_next:
                switch (mPay) {
                    case "1":
                        pay();
                        break;
                    case "2":
                        //商户订单号
                        String OutTradeNo = "10000" + getTimes() + getOutTradeNo() + "23333";
                        SharedPreferencesUtil.setParam(AccountPut.this, "OutTradeNo", OutTradeNo);
                        SharedPreferencesUtil.setParam(AccountPut.this, "Money", Et_Money.getText().toString());
                        new WxPayUtils(this, "众盟汇订单", Double.parseDouble(Et_Money.getText().toString()), OutTradeNo).openWXPay();
                        break;
                }
        }
    }

    //生成时间
    public static String getTimes() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String str = sdf.format(date);
        return str;
    }

    //生成随机数
    private static String getOutTradeNo() {
        Random random = new Random();
        return String.valueOf(random.nextInt(10000));
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        ToastUtils.showToast(AccountPut.this, "充值成功");
                        Log.e("sssss", OrderInfoUtil2_0.OutTradeNo);
                        Intent intent = new Intent(AccountPut.this, Sum.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    } else {
                        ToastUtils.showToast(AccountPut.this, "充值失败");
                    }
                    break;
            }
        }
    };

    /**
     * 支付宝支付业务
     */
    public void pay() {
//        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
//            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
//                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialoginterface, int i) {
//                            finish();
//                        }
//                    }).show();
//            return;
//        }
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2, Float.parseFloat(Et_Money.getText().toString()));
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;
        Log.e("orderInfo", "orderInfo>>>" + orderInfo);
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(AccountPut.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @Override
    public void afterTextChanged(Editable s) {
        mMoneyValue = Et_Money.getText().toString();
        if (mMoneyValue.equals("")) {
            Tv_Money.setText("0.00");//实时变化的金额
        } else {
            Tv_Money.setText(s + ".00");
        }
        if (mMoneyValue.equals("") || Double.parseDouble(mMoneyValue) < 1) {
            But_Next.setEnabled(false);
        } else {
            But_Next.setEnabled(true);
        }
        //第一位数字不能为0
        if (mMoneyValue.startsWith("0")) {
            s.clear();
        }
    }
}