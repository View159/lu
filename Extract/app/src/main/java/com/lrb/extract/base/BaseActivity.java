package com.lrb.extract.base;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.lrb.extract.DataListener;
import com.lrb.extract.R;
import com.lrb.frame.LoadTypeConfig;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void initRecyclerLayout(RecyclerView recyclerView, SmartRefreshLayout smartRefreshLayout, final DataListener dataListener){
                if(recyclerView!=null) recyclerView.setLayoutManager(new LinearLayoutManager(this));
                if(smartRefreshLayout!=null&&dataListener!=null){
                    smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
                        @Override
                        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                            dataListener.getState(LoadTypeConfig.MORE);
                        }

                        @Override
                        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                            dataListener.getState(LoadTypeConfig.REFRESH);
                        }
                    });
                }
    }

    public void showLog(String msg){
        Log.i("Tag", msg);
    }





}
