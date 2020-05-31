package com.lrb.extract;

import com.lrb.frame.ApiConfig;
import com.lrb.frame.Contract;
import com.lrb.frame.MyHttp;

import java.util.HashMap;

public class TextModul implements Contract.CommonModul {
    MyHttp myHttp=MyHttp.getInstance();
    @Override
    public void getData(Contract.CommonPresenter commonPresenter, int whichApi, Object[] pM) {
        switch (whichApi){
            case ApiConfig.TEST_TYPE:
                int loadType = (int) pM[0];
                HashMap hashMap = (HashMap) pM[1];
                int page = (int) pM[2];

                myHttp.netWork( myHttp.getretrofit().getTestData(hashMap,page),commonPresenter,whichApi,loadType);

                break;
                case ApiConfig.ADVERT:
                    break;
        }
    }
}
