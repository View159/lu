package com.lrb.dashixun;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.lrb.dashixun.adapter.RecyclerAdapter;
import com.lrb.frame.ApiConfig;
import com.lrb.frame.CommonPresenter;
import com.lrb.frame.ICommonView;
import com.lrb.frame.LoadTypeConfig;
import com.lrb.text.InfoBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements ICommonView {

    private RecyclerView recycler;
    private SmartRefreshLayout smart;
    private RecyclerAdapter recyclerAdapter;
    private Map add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        CommonModul commonModul = new CommonModul();
        CommonPresenter commonPresenter = new CommonPresenter(commonModul, this);
        commonPresenter.getData(ApiConfig.TEST_TYPE, LoadTypeConfig.NORMAL,add,0);
    }

    private void initView() {
        recycler = (RecyclerView) findViewById(R.id.recycler);
        smart = (SmartRefreshLayout) findViewById(R.id.smart);
        add = new HashMap();
        add .put("c", "api");
        add.put("a", "getList");
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerAdapter(this);
        recycler.setAdapter(recyclerAdapter);
    }

    @Override
    public void Success(int whichApi, int loadType, Object[] view) {
        InfoBean infoBean = (InfoBean) view[0];
        List<InfoBean.DatasBean> datas = infoBean.getDatas();
        recyclerAdapter.addList(datas);
    }

    @Override
    public void Falied(int whichApi, Throwable throwable) {

    }
}
