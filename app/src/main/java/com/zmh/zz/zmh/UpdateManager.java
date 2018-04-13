package com.zmh.zz.zmh;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.HttpUtils;
import com.zmh.zz.zmh.LoadingDialog.ShapeLoadingDialog;
import com.zmh.zz.zmh.activity.BindEmail;
import com.zmh.zz.zmh.httpurls.HttpURLs;
import com.zmh.zz.zmh.modeljson.UserJson;
import com.zmh.zz.zmh.utils.MyStringCallBack;
import com.zmh.zz.zmh.utils.OkHttpUtil;
import com.zmh.zz.zmh.utils.SharedPreferencesUtil;
import com.zmh.zz.zmh.utils.ToastUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * 版本升级的代码
 */
public class UpdateManager {
    HttpUtils http = new HttpUtils();
    private Context mContext;
    // 模拟服务器中的版本号
    private int serviceCode = 2;
    // 更新进度条
    private ProgressDialog mDownloadDialog;
    // 准备安装新版本应用标记
    private static final int INSTALL_TOKEN = 1;
    // 外存sdcard存放路径
    private static final String FILE_PATH = Environment.getExternalStorageDirectory() + "/" + "Zmh" + "/";
    // 下载应用存放全路径
    private static final String FILE_NAME = FILE_PATH + "Zmh.apk";
    // Log日志打印标签
    private static final String TAG = "Update_log";
    private OkHttpUtil okHttp = new OkHttpUtil();

    public UpdateManager(Context context) {
        this.mContext = context;
    }

    /**
     * 获取软件版本号
     *
     * @param context
     */
    private int getVersionCode(Context context) {
        int versionCode = 0;
        try {
            // 获取当前软件版本号
            versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 检测软件更新
     */
    public void CheckUpdate() {
        int versionCode = getVersionCode(mContext);
        if (serviceCode >= versionCode) {
            showNoticeDialog();
        } else {
            ToastUtils.showToast(mContext, "已经是最新版本了");
        }
//        String url = "http://" + SharedPreferencesUtil.getParam(mContext, "IP", "") + ":" + SharedPreferencesUtil.getParam(mContext, "PORT", "") + "/mes-service/SystemManageServer/AppUpdate/appBate";
//        http.configCurrentHttpCacheExpiry(1000);
//        http.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
//            @Override
//            public void onSuccess(ResponseInfo<String> responseInfo) {
//                String s = responseInfo.result;
//                Toast.makeText(mContext, s, Toast.LENGTH_LONG).show();
//                int serviceCode = Integer.parseInt(s);
//                int versionCode = getVersionCode(mContext);
//                if (serviceCode == versionCode) {
//                    showNoticeDialog();
//                } else {

//                }
//            }
//
//            @Override
//            public void onFailure(HttpException e, String s) {
//                Toast.makeText(mContext, "网络连接失败，请查询网络", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    /**
     * 用户信息
     */
    public void UserInfo() {
        final ShapeLoadingDialog shapeLoadingDialog = new ShapeLoadingDialog(mContext);
        shapeLoadingDialog.setCancelable(false);
        shapeLoadingDialog.setLoadingText("加载中,请稍等...");
        shapeLoadingDialog.show();
        String Token = (String) SharedPreferencesUtil.getParam(mContext, "Token", "");
        String url = HttpURLs.TOUSERINFO;
        Map<String, String> params = new HashMap<>();
        params.put("token", Token);
        okHttp.postRequest(url, params, new MyStringCallBack() {
            @Override
            public void onResponse(String response, int id) {
                shapeLoadingDialog.dismiss();
                Log.e("sssss>>>", response);
                UserJson user = JSONObject.parseObject(response, UserJson.class);
                int code = user.getCode();
                switch (code) {
                    case 200:
                        shapeLoadingDialog.dismiss();
                        SharedPreferencesUtil.setParam(mContext, "accountNum", user.getData().getAccountNum());//用户数字ID
                        SharedPreferencesUtil.setParam(mContext, "verifiedStatus", user.getData().getVerifiedStatus());//实名认证
                        SharedPreferencesUtil.setParam(mContext, "bankStatus", user.getData().getBankStatus());//银行卡认证
                        SharedPreferencesUtil.setParam(mContext, "name", user.getData().getName());//实名认证过以后的户名
                        if (TextUtils.isEmpty(user.getData().getEmail())) {
                            mContext.startActivity(new Intent(mContext, BindEmail.class));
                            finish();
                        }
                        break;
                    case 400:
                        shapeLoadingDialog.dismiss();
                        ToastUtils.showToast(mContext, "系统异常");
                        break;
                }
            }

            private void finish() {
                finish();
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                shapeLoadingDialog.dismiss();
                Toast.makeText(mContext, R.string.ConnectionError, Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 显示提示更新对话框
     */
    private void showNoticeDialog() {
        new AlertDialog.Builder(mContext)
                .setCancelable(false)
                .setTitle("检测到新版本")
                .setMessage("检查到有新版本请下载！")
                .setPositiveButton("下载", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        showDownloadDialog();
                    }
                }).setNegativeButton("下次再说", null).show();
    }

    /**
     * 显示下载进度对话框
     */
    public void showDownloadDialog() {
        mDownloadDialog = new ProgressDialog(mContext);
        mDownloadDialog.setTitle("正在下载...");
        mDownloadDialog.setCanceledOnTouchOutside(true);
        mDownloadDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mDownloadDialog.setCancelable(false);//禁止获得dialog以外的焦点
        /**
         * 下载apk文件
         */
        new downloadAsyncTask().execute();
    }


    /**
     * 下载新版本应用
     */
    private class downloadAsyncTask extends AsyncTask<Void, Integer, Integer> {
        @Override
        protected void onPreExecute() {
            mDownloadDialog.show();
        }

        @Override
        protected Integer doInBackground(Void... params) {
            HttpURLConnection connection = null;
            InputStream in = null;
            FileOutputStream out = null;
            try {
                URL url = new URL("http://d10.muzisoft.com/1609/com.zy.mapdemo.apk");
                connection = (HttpURLConnection) url.openConnection();
                in = connection.getInputStream();
                long fileLength = connection.getContentLength();
                File file_path = new File(FILE_PATH);
                if (!file_path.exists()) {
                    file_path.mkdir();
                }
                out = new FileOutputStream(new File(FILE_NAME));//为指定的文件路径创建文件输出流
                byte[] buffer = new byte[1024 * 1024];
                int len = 0;
                long readLength = 0;
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);//从buffer的第0位开始读取len长度的字节到输出流
                    readLength += len;
                    int curProgress = (int) (((float) readLength / fileLength) * 100);
                    publishProgress(curProgress);
                    if (readLength >= fileLength) {
                        break;
                    }
                }
                out.flush();
                return INSTALL_TOKEN;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null) {
                    connection.disconnect();
                }
            }
            return null;
        }

        //数字显示
        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.e(TAG, "异步更新进度接收到的值：" + values[0]);
            mDownloadDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            mDownloadDialog.dismiss();//关闭进度条
            //安装应用
            installApk();
        }
    }

    /**
     * 安装APK文件
     */
    private void installApk() {
        File appFile = new File(FILE_NAME);
        if (!appFile.exists()) {
            return;
        }
        // 跳转到新版本应用安装页面
        Intent installApkIntent = new Intent();
        installApkIntent.setAction(Intent.ACTION_VIEW);
        installApkIntent.addCategory(Intent.CATEGORY_DEFAULT);
        installApkIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            installApkIntent.setDataAndType(FileProvider.getUriForFile(mContext, "com.zmh.zz.zmh.fileprovider", appFile), "application/vnd.android.package-archive");
            installApkIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            installApkIntent.setDataAndType(Uri.fromFile(appFile), "application/vnd.android.package-archive");
        }
        if (mContext.getPackageManager().queryIntentActivities(installApkIntent, 0).size() > 0) {
            mContext.startActivity(installApkIntent);
        }
    }
}
