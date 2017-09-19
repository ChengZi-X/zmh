package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.adapter.ClienBonustAdapter;
import com.zmh.zz.zmh.modelinfo.ClientBonusInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 * 客户奖金
 */

public class Ac_Client_Bonus extends BaseActivity {
    private ListView mLv_client_bonust;
    private ClienBonustAdapter clienBonustAdapter;
    private List<ClientBonusInfo> clienBonustList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("客户奖金");
        ListViewInitData();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_client_bonus;//任意非空布局
    }

    private void ListViewInitData() {
        mLv_client_bonust = (ListView) findViewById(R.id.lv_client_bonus);
        clienBonustList = new ArrayList<>();
        clienBonustAdapter = new ClienBonustAdapter(clienBonustList, Ac_Client_Bonus.this);
        mLv_client_bonust.setAdapter(clienBonustAdapter);
        clienBonustAdapter.notifyDataSetChanged();
    }
}
