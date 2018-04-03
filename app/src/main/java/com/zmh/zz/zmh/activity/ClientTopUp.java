package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.adapter.ClientTopUpAdapter;
import com.zmh.zz.zmh.modelinfo.ClientTopUpInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 * 客户充值
 */

public class ClientTopUp extends BaseActivity {
    private RecyclerView mRv_client_top_up;
    private ClientTopUpAdapter clientTopUpAdapter;
    private List<ClientTopUpInfo> clientTopUptList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("客户充值");
        initData();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_client_top_up;
    }

    private void initData() {
        mRv_client_top_up = (RecyclerView) findViewById(R.id.rv_client_top_up);
        clientTopUptList = new ArrayList<>();
        clientTopUpAdapter = new ClientTopUpAdapter(clientTopUptList, ClientTopUp.this);
        mRv_client_top_up.setLayoutManager(new LinearLayoutManager(this));
        mRv_client_top_up.setAdapter(clientTopUpAdapter);
        clientTopUpAdapter.notifyDataSetChanged();
    }

}
