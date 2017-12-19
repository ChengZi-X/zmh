package com.zmh.zz.zmh.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.uploaImage.GlideImageLoader;
import com.zmh.zz.zmh.uploaImage.ImagePickerAdapter;
import com.zmh.zz.zmh.uploaImage.SelectPortraitDialog;
import com.zmh.zz.zmh.wheelview.PickerScrollView;
import com.zmh.zz.zmh.wheelview.PickersBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator
 * 添加银行卡
 */

public class AddBankCard extends BaseActivity implements View.OnClickListener, ImagePickerAdapter.OnRecyclerViewItemClickListener {
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; // 当前选择的所有图片
    private int maxImgCount = 2;               // 允许选择图片最大数
    private TextView Tv_Opening_bank;
    private PickerScrollView pickerscrlllview; // 滚动选择器
    private List<PickersBean> mList;           // 滚动选择器数据
    private String[] id;
    private String[] name;
    private TextView mPicker_yes, mPicker_no;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("添加银行卡");
        setRtTitle("提交");
        setRightBtnVisible(true);
        FindViewById();
        initImagePicker();
        initWidget();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_add_bank_card;
    }

    private void FindViewById() {
        Tv_Opening_bank = (TextView) findViewById(R.id.tv_opening_bank);
        Tv_Opening_bank.setOnClickListener(this);
    }

    //右键点击
    @Override
    protected void onClickRight() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(AddBankCard.this);
        dialog.setTitle("提示");
        dialog.setMessage("\r\r\r\r\r\r\r\r您的资料已提交,请耐心等待,我们将在1-2个工作日人审核完成。");
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {// 确定按钮的响应事件
                dialog.dismiss();
            }
        }).setCancelable(false).show();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mList = new ArrayList<>();
        id = new String[]{"1", "2", "3", "4", "5", "6"};
        name = new String[]{"中国银行", "中国农业银行", "中国招商银行", "中国工商银行", "中国建设银行", "中国民生银行"};
        for (int i = 0; i < name.length; i++) {
            mList.add(new PickersBean(name[i], id[i]));
        }
        // 设置数据，默认选择第一条
        pickerscrlllview.setData(mList);
        pickerscrlllview.setSelected(0);
    }

    // 滚动选择器选中事件
    PickerScrollView.onSelectListener pickerListener = new PickerScrollView.onSelectListener() {
        @Override
        public void onSelect(PickersBean pickers) {
            Tv_Opening_bank.setText(pickers.getShowConetnt());
            if (!Tv_Opening_bank.getText().toString().equals("请选择")) {
                Tv_Opening_bank.setTextColor(getResources().getColor(R.color.absolute_black));
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_opening_bank:
                showPopWindow();
                initData();
                break;
            case R.id.picker_yes://确定
                popupWindow.dismiss();
                break;
            case R.id.picker_no://取消
                popupWindow.dismiss();
                break;
        }
    }

    private void showPopWindow() {
        //  适配PopupWindow布局文件
        View popView = View.inflate(this, R.layout.layout_picker_pop, null);
        //  创建PopupWindow
        popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        //  隐藏输入法
        popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST);
        //  设置SelectPicPopupWindow弹出窗体的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //  点击外部区域消失
        popupWindow.setOutsideTouchable(false);
        //  设置PopupWindow在ll_main下显示
        popupWindow.showAtLocation(findViewById(R.id.ll_main), Gravity.BOTTOM | Gravity.LEFT, 0, 0);
        //  PopupWindow控件ID
        pickerscrlllview = (PickerScrollView) popView.findViewById(R.id.pickerscrollview);
        mPicker_yes = (TextView) popView.findViewById(R.id.picker_yes);
        mPicker_no = (TextView) popView.findViewById(R.id.picker_no);
        pickerscrlllview.setOnSelectListener(pickerListener);
        mPicker_yes.setOnClickListener(this);
        mPicker_no.setOnClickListener(this);
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
                        Intent intent = new Intent(AddBankCard.this, ImageGridActivity.class);
                        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                        startActivityForResult(intent, REQUEST_CODE_SELECT);
                        selectDialog.dismiss();
                    }

                    @Override
                    public void photo() {
                        //相册
                        ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                        Intent intent = new Intent(AddBankCard.this, ImageGridActivity.class);
                        startActivityForResult(intent, REQUEST_CODE_SELECT);
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

}
