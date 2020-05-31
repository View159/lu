package com.lrb.frame;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by 任小龙 on 2020/5/29.
 */
public abstract   class BaseObserver implements Observer {
    private Disposable disposable;
    @Override
    public void onSubscribe(Disposable d) {
        disposable=d;
    }

    @Override
    public void onNext(Object o) {
        onSuccess(o);
        dispose();
    }

    @Override
    public void onError(Throwable e) {
        failed( e);
        dispose();
    }

    @Override
    public void onComplete() {
        dispose();
    }

    public abstract void onSuccess(Object o);
    public abstract void failed(Throwable e);

        public void dispose(){
            if(disposable!=null&&!disposable.isDisposed()){
                disposable.dispose();
            }

        }
}
