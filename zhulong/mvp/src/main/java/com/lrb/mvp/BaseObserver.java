package com.lrb.mvp;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver implements Observer {
    Disposable disposable;
    @Override
    public void onSubscribe(Disposable d) {
        disposable=d;
    }

    @Override
    public void onNext(Object o) {
        OnSuccess(o);
        dispose();
    }

    @Override
    public void onError(Throwable e) {
        OnFailed(e);
        dispose();
    }

    @Override
    public void onComplete() {
        dispose();
    }

    public abstract void OnSuccess(Object o);

    public abstract void OnFailed(Throwable e);

    public void dispose(){
        disposable.dispose();
    }

}
