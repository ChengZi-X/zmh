package com.zmh.zz.zmh.utlis;


import com.lzy.imagepicker.bean.ImageItem;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zmh.zz.zmh.uploaImage.BitmapUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OkHttpUtil {

    private PostFormBuilder mPost;
    private GetBuilder mGet;

    public OkHttpUtil() {
        OkHttpUtils.getInstance().getOkHttpClient().newBuilder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .writeTimeout(15 * 1000L, TimeUnit.MILLISECONDS)
                .build();

        mPost = OkHttpUtils.post();
        mGet = OkHttpUtils.get();
    }

    /*
    //POST请求
            Map<String, String> params = new HashMap<>();
            params.put("p", "1");
            okHttpUtil.postRequest(url, params, new MyStringCallBack() {

                @Override
                public void onResponse(String response, int id) {
                    super.onResponse(response, id);
                }

                @Override
                public void onError(Call call, Exception e, int id) {
                    super.onError(call, e, id);
                }
            });
     //Get请求
            okHttpUtil.GetRequest(url, new MyStringCallBack() {
                @Override
                public void onResponse(String response, int id) {
                    super.onResponse(response, id);
                }

                @Override
                public void onError(Call call, Exception e, int id) {
                    super.onError(call, e, id);
                }

            });
     */
    //封装请求Get
    public void GetRequest(String url, MyStringCallBack callback) {
        mGet.url(url).build().execute(callback);
    }

    //封装请求POST
    public void postRequest(String url, Map<String, String> params, MyStringCallBack callback) {
        mPost.url(url).params(params).build().execute(callback);
    }

    //上传文件
    public void postFileRequest(String url, Map<String, String> params, ArrayList<ImageItem> pathList, MyStringCallBack callback) {
        Map<String, File> files = new HashMap<>();
        for (int i = 0; i < pathList.size(); i++) {
            String newPath = BitmapUtils.compressImageUpload(pathList.get(i).path);
            files.put(pathList.get(i).name + i, new File(newPath));
        }
        mPost.url(url).params(params).files("files", files).build().execute(callback);
    }
}
