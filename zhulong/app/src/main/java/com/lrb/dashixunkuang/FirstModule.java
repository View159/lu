package com.lrb.dashixunkuang;


import android.content.Context;
import android.text.TextUtils;

import com.lrb.dashixunkuang.base.Application1907;
import com.lrb.mvp.ApiConfig;

import com.lrb.mvp.Contract;

import com.lrb.mvp.NetManger;
import com.lrb.mvp.utils.ParamHashMap;

import java.util.HashMap;


public   class FirstModule  implements Contract.CommonModule {

    NetManger netManger=NetManger.getInstance();
    private Context mContext = Application1907.get07ApplicationContext();
    @Override
    public void getData(Contract.CommonPresenter pPresenter, int whichApi, Object[] pM) {
        switch (whichApi){
            case ApiConfig.FIRST:
                ParamHashMap map = new ParamHashMap().add("w",pM[1]).add("h",pM[2]).add("positions_id", "APP_QD_01").add("is_show", 0);
                if (!TextUtils.isEmpty((String)pM[0]))map.add("specialty_id",pM[0]);
                netManger.getLoadData( netManger.getApiService().getFirstImage(map),pPresenter,whichApi);
                break;
            case ApiConfig.SUBJECT:
                netManger.getLoadData(netManger.getApiService(mContext.getString(R.string.edu_openapi)).getSubjectList(), pPresenter, whichApi);
                break;
        }



    }
}
