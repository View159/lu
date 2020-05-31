package com.lrb.mvp;

import io.reactivex.disposables.Disposable;

public interface Contract {

    interface CommonView<V>{
        void Success(int whichApi,V...pV);
        void Failed(int whichApi ,Throwable pThrowable);
    }

    interface CommonPresenter<P> extends CommonView{
        void getData(int whichApi,P...pP);
        void addDisposable(Disposable disposable);
    }

    interface CommonModule<M>{
        void getData(CommonPresenter pPresenter,int whichApi,M...pM);
    }



}
