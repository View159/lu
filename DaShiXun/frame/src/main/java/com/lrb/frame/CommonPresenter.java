package com.lrb.frame;

import java.lang.ref.SoftReference;

public class CommonPresenter<V extends ICommonView,M extends ICommonModule> implements ICommonPresenter {

    protected SoftReference<M> module;
    protected SoftReference<V> Pview;

    public CommonPresenter(M  modu, V view) {
        this.module=new SoftReference<>(modu);
        this.Pview=new SoftReference<>(view);
    }

    @Override
    public void getData(int wichApi, Object... pP) {
            module.get().getData(this, wichApi, pP);
    }

    @Override
    public void Success(int whichApi, int loadType, Object[] view) {
        if(Pview!=null&&Pview.get()!=null){
            Pview.get().Success(whichApi,loadType,view);
        }
    }

    @Override
    public void Falied(int whichApi, Throwable throwable) {
        if(Pview!=null&&Pview.get()!=null){
            Pview.get().Falied(whichApi,throwable);
        }
    }

    public void clear(){
        if (Pview != null){
            Pview.clear();
            Pview = null;
        }
        if (module != null){
            module.clear();
            module = null;
        }
    }
}
