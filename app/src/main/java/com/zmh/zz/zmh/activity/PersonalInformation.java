package com.zmh.zz.zmh.activity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.lzy.imagepicker.util.ProviderUtil;
import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.MainActivity;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.uploaImage.SelectPortraitDialog;
import com.zmh.zz.zmh.utils.CircleImageView1Util;
import com.zmh.zz.zmh.utils.ToastUtils;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator
 * 个人信息
 */

public class PersonalInformation extends BaseActivity implements View.OnClickListener {
    private RelativeLayout Rl_Express_address, Rl_head_portrait;
    private CircleImageView1Util Im_head_portrait;
    private Bitmap mBitmap;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    private Uri tempUri;
    private String midPath;
    private File filePath;
    private static String path;
    private SelectPortraitDialog selectDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("个人信息");
        FindViewById();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_personal_information;
    }

    private void FindViewById() {
        Im_head_portrait = (CircleImageView1Util) findViewById(R.id.im_head_portrait);
        Rl_head_portrait = (RelativeLayout) findViewById(R.id.rl_head_portrait);
        Rl_Express_address = (RelativeLayout) findViewById(R.id.rl_express_address);
        Rl_head_portrait.setOnClickListener(this);
        Rl_Express_address.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_head_portrait:
                selectDialog = new SelectPortraitDialog(this);
                selectDialog.show();
                selectDialog.setClicklistener(new SelectPortraitDialog.ClickListenerInterface() {
                    @Override
                    public void photograph() {
                        //相机
                        File DatalDir = Environment.getExternalStorageDirectory();
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss", Locale.SIMPLIFIED_CHINESE);
                        midPath = "DCIM/Camera/" + "IMG_" + sdf.format(new Date()) + ".jpg";
                        tempUri = Uri.fromFile(new File(DatalDir.getPath(), midPath));
                        try {
                            filePath = new File(new URI(tempUri.toString()));
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(filePath));
                            startActivityForResult(intent, TAKE_PICTURE);
                        } else {
                            if (ContextCompat.checkSelfPermission(PersonalInformation.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                                    ContextCompat.checkSelfPermission(PersonalInformation.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                ToastUtils.showToast(PersonalInformation.this, "请手动添加您的权限");
                            } else {
                                startCamera();
                                selectDialog.dismiss();
                            }
                        }
                        selectDialog.dismiss();
                    }

                    @Override
                    public void photo() {
                        //相册
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        startActivityForResult(intent, CHOOSE_PICTURE);
                        selectDialog.dismiss();
                    }
                });
                break;
            case R.id.rl_express_address:
                startActivity(new Intent(PersonalInformation.this, NewExpressAddress.class));
                break;
        }
    }

    private void startCamera() {
        File DatalDir = Environment.getExternalStorageDirectory();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss", Locale.SIMPLIFIED_CHINESE);
        midPath = "DCIM/Camera/" + "IMG_" + sdf.format(new Date()) + ".jpg";
        tempUri = Uri.fromFile(new File(DatalDir.getPath(), midPath));
        try {
            filePath = new File(new URI(tempUri.toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ContentValues contentValues = new ContentValues(1);
        contentValues.put(MediaStore.Images.Media.DATA, filePath.getAbsolutePath());
        tempUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
        startActivityForResult(intent, TAKE_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == PersonalInformation.RESULT_OK) {
            switch (requestCode) {
                case TAKE_PICTURE://拍照
                    cutImage(tempUri); // 对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE://相册
                    cutImage(data.getData()); // 对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * 裁剪图片方法实现
     */
    protected void cutImage(Uri uri) {
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        //com.android.camera.action.CROP这个action是用来裁剪图片用的
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    //将tempUri转换成真实的图片路径
    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        path = null;
        if (scheme == null)
            path = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            path = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        path = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return path;
    }

    /**
     * 保存裁剪之后的图片数据
     * 图片上传操作
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            mBitmap = extras.getParcelable("data");
            Im_head_portrait.setImageBitmap(mBitmap);//显示图片
            getRealFilePath(PersonalInformation.this, tempUri);
            Log.e("sssss>>", path);
            String ImgName = path.replace("/", "");
            Log.e("sssss>>", ImgName);
            ToastUtils.showToast(PersonalInformation.this, "上传图片需要的>>>" + "图片的名字>>>" + ImgName + "图片的路径>>>" + path);
            //在这个地方可以写上上传该图片到服务器的代码，后期将单独写一篇这方面的博客，敬请期待...

        }
    }
}
