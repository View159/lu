package com.lrb.frame;

import io.reactivex.disposables.Disposable;

public interface Contract {

     interface CommonView<V> {

        void Success(int whichApi,int loadType,V ...pv);
         void Failed(int whichApi ,Throwable throwable);
    }

    interface CommonPresenter<P> extends CommonView{
         void getData(int whichApi,P ...pP);
        void addDispose(Disposable disposable);
    }

    interface CommonModul<M>{
         void getData(CommonPresenter commonPresenter,int whichApi,M...pM);
    }

}
