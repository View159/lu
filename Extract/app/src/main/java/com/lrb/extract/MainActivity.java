package com.lrb.extract;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.lrb.extract.adapter.RecyclerAdapter;
import com.lrb.extract.base.BaseMvpActivity;
import com.lrb.frame.ApiConfig;
import com.lrb.frame.Contract;
import com.lrb.frame.LoadTypeConfig;
import com.lrb.test.InfoBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends BaseMvpActivity {


    private RecyclerView recycler;
    private SmartRefreshLayout smart;
    private RecyclerAdapter recyclerAdapter;
    private HashMap<String, Object> objectObjectHashMap;
    private int page;
    @Override
    protected void initData() {

        testPresenter.getData(ApiConfig.TEST_TYPE, LoadTypeConfig.NORMAL,objectObjectHashMap,page);
    }

    @Override
    protected void initView() {
        recycler=findViewById(R.id.recycler);
        smart=findViewById(R.id.smart);
        objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("c", "api");
        objectObjectHashMap.put("a", "getList");
        recyclerAdapter = new RecyclerAdapter(this);
        recycler.setAdapter(recyclerAdapter);

        initRecyclerLayout(recycler, smart, new DataListener() {
            @Override
            public void getState(int loadType) {
                switch (loadType){
                    case LoadTypeConfig.MORE:
                        page++;
                        testPresenter.getData(ApiConfig.TEST_TYPE, LoadTypeConfig.MORE,objectObjectHashMap,page);
                        break;
                    case LoadTypeConfig.REFRESH:
                        page=0;
                        testPresenter.getData(ApiConfig.TEST_TYPE, LoadTypeConfig.REFRESH,objectObjectHashMap,page);
                        break;
                }
            }
        });
    }

    @Override
    protected Contract.CommonModul initModul() {
        return new TextModul();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void netSuccess(int whichApi, int loadType, Object[] pv) {

        switch (whichApi){
            case ApiConfig.TEST_TYPE:
                List<InfoBean.DatasBean> datas = ((InfoBean) pv[0]).getDatas();
                if(loadType==LoadTypeConfig.REFRESH){
                    smart.finishRefresh();
                    recyclerAdapter.refresh(datas);
                }else if(loadType==LoadTypeConfig.MORE){
                    smart.finishLoadMore();
                    recyclerAdapter.addList(datas);
                }else if(loadType==LoadTypeConfig.NORMAL){
                    recyclerAdapter.addList(datas);
                }
                break;
        }
    }


}
