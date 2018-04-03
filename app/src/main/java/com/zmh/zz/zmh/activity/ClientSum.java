package com.zmh.zz.zmh.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.zmh.zz.zmh.BaseActivity;
import com.zmh.zz.zmh.R;
import com.zmh.zz.zmh.adapter.ClientSumAdapter;
import com.zmh.zz.zmh.modelinfo.ClientSumInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator
 * 客户余额
 */

public class ClientSum extends BaseActivity {
    private RecyclerView mRv_client_sum;
    private ClientSumAdapter clientSumAdapter;
    private List<ClientSumInfo> clientSumtList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("客户余额");
        initData();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_client_sum;
    }

    private void initData() {
        clientSumtList = new ArrayList<>();
        mRv_client_sum = (RecyclerView) findViewById(R.id.rv_client_sum);
        clientSumAdapter = new ClientSumAdapter(clientSumtList, ClientSum.this);
        mRv_client_sum.setLayoutManager(new LinearLayoutManager(this));
        mRv_client_sum.setAdapter(clientSumAdapter);
        clientSumAdapter.notifyDataSetChanged();
    }
}
