package com.lrb.dashixunkuang;

import com.lrb.mvp.ApiConfig;
import com.lrb.mvp.Contract;
import com.lrb.mvp.NetManger;

import java.util.HashMap;
import java.util.Map;

public   class FirstModule  implements Contract.CommonModule {

    NetManger netManger=NetManger.getInstance();
    @Override
    public void getData(Contract.CommonPresenter pPresenter, int whichApi, Object[] pM) {
        switch (whichApi){
            case ApiConfig.FIRST:
                HashMap hashMap = (HashMap) pM[0];
                netManger.getLoadData( netManger.getApiService().getFirstImage(hashMap),pPresenter,whichApi);
                break;
        }



    }
}
