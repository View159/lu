package com.lrb.dashixunkuang.view;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.lrb.dashixunkuang.FirstModule;
import com.lrb.dashixunkuang.R;
import com.lrb.dashixunkuang.adapter.SubjectAdapter;
import com.lrb.dashixunkuang.base.BaseMvpActivity;
import com.lrb.infobean.BaseInfo;
import com.lrb.infobean.SpecialtyChooseEntity;
import com.lrb.mvp.ApiConfig;
import com.lrb.mvp.Contract;
import com.teach.frame.constants.ConstantKey;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SubjectActivity extends BaseMvpActivity {



    private TextView title_content;
    private RecyclerView recyclerView;
    private List<SpecialtyChooseEntity> mListData = new ArrayList<>();
    private SubjectAdapter mAdapter;
    private ImageView back_image;

    @Override
    protected void initData() {
        if (SharedPrefrenceUtils.getSerializableList(this, ConstantKey.SUBJECT_LIST) != null) {
            List<SpecialtyChooseEntity> serializableList = SharedPrefrenceUtils.getSerializableList(this, ConstantKey.SUBJECT_LIST);
            mListData.addAll(serializableList);
            mAdapter.notifyDataSetChanged();
        } else
            pPresenter.getData(ApiConfig.SUBJECT);
    }

    @Override
    protected void initView() {
        title_content=findViewById(R.id.title_content);
        recyclerView=findViewById(R.id.recyclerView);
        back_image = findViewById(R.id.back_image);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SubjectAdapter(mListData,this);
        recyclerView.setAdapter(mAdapter);
        back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected Contract.CommonModule initModule() {
        return new FirstModule();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_subject;
    }

    @Override
    protected void netSuccess(int whichApi, Object[] pV) {
        switch (whichApi) {
            case ApiConfig.SUBJECT:
                BaseInfo<List<SpecialtyChooseEntity>> info = (BaseInfo<List<SpecialtyChooseEntity>>) pV[0];
                mListData.addAll(info.result);
                mAdapter.notifyDataSetChanged();
                SharedPrefrenceUtils.putSerializableList(this,ConstantKey.SUBJECT_LIST,mListData);
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPrefrenceUtils.putObject(this,ConstantKey.SUBJECT_SELECT,mApplication.getSelectedInfo());
    }


}