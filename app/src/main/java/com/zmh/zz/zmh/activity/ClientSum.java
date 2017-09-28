package com.zmh.zz.zmh.activity;

import android.os.Bundle;
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
    private ListView mLv_client_sum;
    private ClientSumAdapter clientSumAdapter;
    private List<ClientSumInfo> clientSumtList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("客户余额");
        InitData();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_client_sum;
    }

    private void InitData() {
        mLv_client_sum = (ListView) findViewById(R.id.lv_client_sum);
        clientSumtList = new ArrayList<>();
        clientSumAdapter = new ClientSumAdapter(clientSumtList, ClientSum.this);
        mLv_client_sum.setAdapter(clientSumAdapter);
        clientSumAdapter.notifyDataSetChanged();
    }
}
