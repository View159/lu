package com.lrb.frame;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObservable implements Observer {

    private Disposable mDisposable;
    @Override
    public void onSubscribe(Disposable d) {
        mDisposable=d;
    }

    @Override
    public void onNext(Object o) {
        Success(o);
        dispose();
    }

    @Override
    public void onError(Throwable e) {
        Failed(e);
        dispose();
    }

    @Override
    public void onComplete() {
        dispose();
    }

    public abstract void Success(Object values);
    public abstract void Failed(Throwable e);

    public void dispose(){
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
