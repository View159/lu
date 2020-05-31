package com.lrb.dashixun;

import com.lrb.frame.ICommonModule;
import com.lrb.frame.ICommonPresenter;
import com.lrb.frame.MyHttp;


import java.util.Map;

public class CommonModul implements ICommonModule {
    MyHttp myHttp=MyHttp.getInstance();
    @Override
    public void getData(ICommonPresenter presenter, int wichApi, Object[] pM) {
        final int loadType = (int) pM[0];
        Map param = (Map) pM[1];
        int pageId = (int) pM[2];

        myHttp.netWork( myHttp.getRetrofit().getTestData(param,pageId),presenter, wichApi, loadType);
    }
}
