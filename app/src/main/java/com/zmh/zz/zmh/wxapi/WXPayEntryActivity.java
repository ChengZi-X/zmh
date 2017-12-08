package com.zmh.zz.zmh.wxapi;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.zmh.zz.zmh.activity.Sum;
import com.zmh.zz.zmh.utils.SharedPreferencesUtil;
import com.zmh.zz.zmh.utils.ToastUtils;


public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    private static final String TAG = "WXPayEntryActivity";
    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.handleIntent(getIntent(), this);
        String Money = (String) SharedPreferencesUtil.getParam(WXPayEntryActivity.this, "Money", "");
        String OutTradeNo = (String) SharedPreferencesUtil.getParam(WXPayEntryActivity.this, "OutTradeNo", "");
        Log.e("genOutTradNo>>>", "OutTradNo>>>>" + OutTradeNo);
        Log.e("genOutTradNo>>>", "Money>>>>" + Money);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == 0) {
                ToastUtils.showToast(WXPayEntryActivity.this, "充值成功");
                Intent intent = new Intent(WXPayEntryActivity.this, Sum.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            } else {
                ToastUtils.showToast(WXPayEntryActivity.this, "充值失败");
            }
            finish();
        }
    }
}