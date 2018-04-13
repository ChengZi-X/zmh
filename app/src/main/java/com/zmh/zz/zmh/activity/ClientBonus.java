package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.adapter.BaseViewHolder;
import com.zmh.zz.zmh.adapter.ClienBonustAdapter;
import com.zmh.zz.zmh.modelinfo.ClientBonusInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 * 客户奖金
 */

public class ClientBonus extends BaseActivity implements BaseViewHolder.onItemCommonClickListener {
    private RecyclerView mRv_client_bonust;
    private ClienBonustAdapter clienBonustAdapter;
    private List<ClientBonusInfo> clienBonustList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("客户奖金");
        initData();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_client_bonus;
    }

    private void initData() {
        clienBonustList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ClientBonusInfo data = new ClientBonusInfo();
            clienBonustList.add(data);
        }
        mRv_client_bonust = (RecyclerView) findViewById(R.id.rv_client_bonus);
        clienBonustAdapter = new ClienBonustAdapter(ClientBonus.this, clienBonustList, this);
        mRv_client_bonust.setLayoutManager(new LinearLayoutManager(this));
        mRv_client_bonust.setAdapter(clienBonustAdapter);
        clienBonustAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClickListener(int position) {
        Toast.makeText(this, "点击了第:" + position + "条", Toast.LENGTH_SHORT).show();
    }
}
