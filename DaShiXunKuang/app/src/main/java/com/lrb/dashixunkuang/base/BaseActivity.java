package com.lrb.dashixunkuang.base;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.lrb.dashixunkuang.DataListener;
import com.lrb.dashixunkuang.R;
import com.lrb.mvp.LoadTypeConfig;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    protected void initRecyclerLayout(RecyclerView recyclerView, SmartRefreshLayout smartRefreshLayout, final DataListener dataListener){
        if(recyclerView!=null)recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if(smartRefreshLayout!=null&&dataListener!=null){
            smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    dataListener.State(LoadTypeConfig.MORE);
                }

                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    dataListener.State(LoadTypeConfig.REFRESH);
                }
            });
        }
     }

    protected void showLog(String msg){
        Log.i("Tag", msg);
    }

    protected void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}