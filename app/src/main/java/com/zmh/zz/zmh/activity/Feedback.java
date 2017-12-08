package com.zmh.zz.zmh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.LoadingDialog.ShapeLoadingDialog;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.httpurls.HttpURLs;
import com.zmh.zz.zmh.modeljson.LoginJson;
import com.zmh.zz.zmh.uploaImage.GlideImageLoader;
import com.zmh.zz.zmh.uploaImage.ImagePickerAdapter;
import com.zmh.zz.zmh.uploaImage.SelectPortraitDialog;
import com.zmh.zz.zmh.utils.Base64Util;
import com.zmh.zz.zmh.utils.MyStringCallBack;
import com.zmh.zz.zmh.utils.OkHttpUtil;
import com.zmh.zz.zmh.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;


/**
 * Created by Administrator
 * 意见反馈
 */

public class Feedback extends BaseActivity implements ImagePickerAdapter.OnRecyclerViewItemClickListener {
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 3;               //允许选择图片最大数
    private OkHttpUtil okHttp = new OkHttpUtil();
    private EditText Et_Content;
    private TextView Tv_Num, Tv_Phone;
    private int num = 200;//限制的最大字数
    private String mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("意见反馈");
        setRtTitle("提交");
        setRightBtnVisible(true);
        FindViewById();
        initImagePicker();
        initWidget();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_feedback;
    }

    //右键点击
    @Override
    protected void onClickRight() {
        mContent = Et_Content.getText().toString();
        final ShapeLoadingDialog shapeLoadingDialog = new ShapeLoadingDialog(Feedback.this);
        shapeLoadingDialog.setCancelable(false);
        shapeLoadingDialog.setLoadingText("上传中,请稍等...");
        shapeLoadingDialog.show();
        String Base64 = "";
        String Name = "";
        for (int i = 0; i < selImageList.size(); i++) {
            String bimpName = selImageList.get(i).name;
            String bimpBase64 = Base64Util.imageToBase64(selImageList.get(i).path);
            if (selImageList.size() - 1 == i) {
                Base64 += bimpBase64;
                Name += bimpName;
            } else {
                Base64 += bimpBase64 + "@";
                Name += bimpName + "@";
            }
        }
        Base64 = Base64 + "#" + Name;
        Log.e("s>>>", Base64);
        String url = HttpURLs.UPDATEBASE64;
        Map<String, String> params = new HashMap<>();
        params.put("imgStr", Base64);
        //params.put("Content", mContent);
        okHttp.postRequest(url, params, new MyStringCallBack() {
            @Override
            public void onResponse(String response, int id) {
                Log.e("sssss>>>", response);
                LoginJson login = JSONObject.parseObject(response, LoginJson.class);
                int code = login.getCode();
                String dosc = login.getDesc();
                switch (code) {
                    case 200:
                        shapeLoadingDialog.dismiss();
                        ToastUtils.showToast(Feedback.this, "上传成功");
                        break;
                    case 400:
                        shapeLoadingDialog.dismiss();
                        ToastUtils.showToast(Feedback.this, dosc);
                        break;
                }

            }

            @Override
            public void onError(Call call, Exception e, int id) {
                shapeLoadingDialog.dismiss();
                Toast.makeText(Feedback.this, R.string.ConnectionError, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void FindViewById() {
        Et_Content = (EditText) findViewById(R.id.et_content);
        Tv_Num = (TextView) findViewById(R.id.tv_num);
        Tv_Phone = (TextView) findViewById(R.id.tv_phone);
        Et_Content.addTextChangedListener(this);
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setMultiMode(true);                       //多选
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);
    }

    private void initWidget() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_add_photo);
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));//一行显示个数
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                final SelectPortraitDialog selectDialog = new SelectPortraitDialog(this);
                selectDialog.show();
                selectDialog.setClicklistener(new SelectPortraitDialog.ClickListenerInterface() {
                    @Override
                    public void photograph() {
                        //相机
                        ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                        Intent intent = new Intent(Feedback.this, ImageGridActivity.class);
                        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                        startActivityForResult(intent, REQUEST_CODE_SELECT);
                        selectDialog.dismiss();
                    }

                    @Override
                    public void photo() {
                        //相册
                        ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                        Intent intent1 = new Intent(Feedback.this, ImageGridActivity.class);
                        startActivityForResult(intent1, REQUEST_CODE_SELECT);
                        selectDialog.dismiss();
                    }
                });
                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        }
    }

    //监听Editable输入字数
    private int selectionStart;
    private int selectionEnd;

    @Override
    public void afterTextChanged(Editable s) {
        int number = s.length();
        Tv_Num.setText(number + "");
        selectionStart = Et_Content.getSelectionStart();
        selectionEnd = Et_Content.getSelectionEnd();
        if (s.length() > num) {
            s.delete(selectionStart - 1, selectionEnd);
            int tempSelection = selectionStart;
            Et_Content.setText(s);
            Et_Content.setSelection(tempSelection);//设置光标在最后
        }
    }
}
