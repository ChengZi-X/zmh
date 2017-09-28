package com.zmh.zz.zmh.activity;

import android.os.Bundle;
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
    private ListView mLv_client_top_up;
    private ClientTopUpAdapter clientTopUpAdapter;
    private List<ClientTopUpInfo> clientTopUptList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLtTitle("客户充值");
        InitData();
    }

    @Override
    protected int getContentView() {
        return R.layout.ac_client_top_up;
    }

    private void InitData() {
        mLv_client_top_up = (ListView) findViewById(R.id.lv_client_top_up);
        clientTopUptList = new ArrayList<>();
        clientTopUpAdapter = new ClientTopUpAdapter(clientTopUptList, ClientTopUp.this);
        mLv_client_top_up.setAdapter(clientTopUpAdapter);
        clientTopUpAdapter.notifyDataSetChanged();
    }

}
