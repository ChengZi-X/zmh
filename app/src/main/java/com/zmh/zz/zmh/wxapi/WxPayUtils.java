package com.zmh.zz.zmh.wxapi;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;

import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.zmh.zz.zmh.activity.AccountPut;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.xmlpull.v1.XmlPullParser;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class WxPayUtils {
    private static final String TAG = "WXPayUtils";
    private String Price;//商品价格
    private String Description;//商品描述
    private String OutTradeNo;//商户订单号
    private AccountPut mContext;
    private PayReq req;
    private final IWXAPI msgApi;
    private Map<String, String> resultunifiedorder;
    private StringBuffer sb;

    public WxPayUtils(AccountPut context, String description, double price, String OutTradeNo) {
        this.mContext = context;
        this.Description = description;
        this.OutTradeNo = OutTradeNo;
        this.Price = String.valueOf((int) (price * 100));//微信支付单位是分，参数必须是整数，因此乘以100再转成整数，转换后单价是元
        msgApi = WXAPIFactory.createWXAPI(context, null);
        req = new PayReq();
        sb = new StringBuffer();
        msgApi.registerApp(Constants.APP_ID);
    }

    public void openWXPay() {
        GetPrepayIdTask getPrepayId = new GetPrepayIdTask();
        getPrepayId.execute();
    }

    /**
     * 生成签名
     */
    private String getPackageSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(Constants.API_KEY);
        String packageSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
        Log.e("orion", packageSign);
        return packageSign;
    }

    private String genAppSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(Constants.API_KEY);

        this.sb.append("sign str\n" + sb.toString() + "\n\n");
        String appSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
        Log.e("orion", appSign);
        return appSign;
    }

    /*
     * 转换成xml
     */
    private String toXml(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        for (int i = 0; i < params.size(); i++) {
            sb.append("<" + params.get(i).getName() + ">");


            sb.append(params.get(i).getValue());
            sb.append("</" + params.get(i).getName() + ">");
        }
        sb.append("</xml>");

        Log.e("orion", sb.toString());
        return sb.toString();
    }

    private class GetPrepayIdTask extends AsyncTask<Void, Void, Map<String, String>> {

        private ProgressDialog dialog;


        @Override
        protected void onPreExecute() {
            dialog = ProgressDialog.show(mContext, "", "提交订单信息中，请稍后...", true);
        }

        @Override
        protected void onPostExecute(Map<String, String> result) {
            sb.append("prepay_id\n" + result.get("prepay_id") + "\n\n");
            resultunifiedorder = result;
            getPayReq();
            setdPayReq();
            if (dialog != null) {
                dialog.dismiss();
            }

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Map<String, String> doInBackground(Void... params) {

            String url = String.format("https://api.mch.weixin.qq.com/pay/unifiedorder");
            String entity = getProductArgs();

            Log.e("orion", entity);

            byte[] buf = Util.httpPost(url, entity);

            String content = new String(buf);
            Log.e("orion", content);
            Map<String, String> xml = decodeXml(content);

            return xml;
        }
    }


    public Map<String, String> decodeXml(String content) {

        try {
            Map<String, String> xml = new HashMap<String, String>();
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(new StringReader(content));
            int event = parser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {

                String nodeName = parser.getName();
                switch (event) {
                    case XmlPullParser.START_DOCUMENT:

                        break;
                    case XmlPullParser.START_TAG:

                        if ("xml".equals(nodeName) == false) {
                            //实例化student对象
                            xml.put(nodeName, parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                event = parser.next();
            }

            return xml;
        } catch (Exception e) {
            Log.e("orion", e.toString());
        }
        return null;

    }

    //生成随机号，防重发
    public String getNonceStr() {
        Random random = new Random();
        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }

    public long getTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    private String getProductArgs() {
        StringBuffer xml = new StringBuffer();
        try {
            String nonceStr = getNonceStr();
            xml.append("</xml>");
            //详见https://pay.weixin.qq.com/wiki/doc/api/app.php?chapter=9_1
            List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
            packageParams.add(new BasicNameValuePair("appid", Constants.APP_ID));
            packageParams.add(new BasicNameValuePair("body", Description));
            packageParams.add(new BasicNameValuePair("mch_id", Constants.MCH_ID));
            packageParams.add(new BasicNameValuePair("nonce_str", nonceStr));
            packageParams.add(new BasicNameValuePair("notify_url", "http://121.40.35.3/test"));
            packageParams.add(new BasicNameValuePair("out_trade_no", OutTradeNo));
            packageParams.add(new BasicNameValuePair("spbill_create_ip", "127.0.0.1"));
            packageParams.add(new BasicNameValuePair("total_fee", Price));
            packageParams.add(new BasicNameValuePair("trade_type", "APP"));
            String sign = getPackageSign(packageParams);
            packageParams.add(new BasicNameValuePair("sign", sign));
            String xmlstring = toXml(packageParams);
            //改变拼接之后xml字符串格式，解决body中文签名时错误的问题
            return new String(xmlstring.toString().getBytes(), "ISO-8859-1");
        } catch (Exception e) {
            Log.e(TAG, "getProductArgs fail, ex = " + e.getMessage());
            return null;
        }
    }

    private String getVersionName(Context context) {
        String versionName = null;
        try {
            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
        } catch (Exception e) {
        }
        return versionName;
    }

    private void getPayReq() {
        /* 详见https://pay.weixin.qq.com/wiki/doc/api/app.php?chapter=9_12 */
        req.appId = Constants.APP_ID;//微信分配的公众账号ID
        req.partnerId = Constants.MCH_ID;//微信支付分配的商户号
        req.prepayId = resultunifiedorder.get("prepay_id");//微信返回的支付交易会话ID
        String versionName = getVersionName(mContext);
        req.packageValue = versionName;
        req.nonceStr = getNonceStr();//随机字符串，不长于32位。推荐随机数生成算法
        req.timeStamp = String.valueOf(getTimeStamp());//时间戳，请见接口规则-参数规定
        List<NameValuePair> signParams = new LinkedList<NameValuePair>();
        signParams.add(new BasicNameValuePair("appid", req.appId));
        signParams.add(new BasicNameValuePair("noncestr", req.nonceStr));
        signParams.add(new BasicNameValuePair("package", req.packageValue));
        signParams.add(new BasicNameValuePair("partnerid", req.partnerId));
        signParams.add(new BasicNameValuePair("prepayid", req.prepayId));
        signParams.add(new BasicNameValuePair("timestamp", req.timeStamp));

        req.sign = genAppSign(signParams);//签名，详见签名生成算法

        sb.append("sign\n" + req.sign + "\n\n");

        Log.e("orion", signParams.toString());

    }

    private void setdPayReq() {
        msgApi.registerApp(Constants.APP_ID);
        msgApi.sendReq(req);
    }
}