package com.lrb.mvp;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class Presenter<V extends Contract.CommonView,M extends Contract.CommonModule> implements Contract.CommonPresenter {

    private SoftReference<V> mView;
    private SoftReference<M> mModule;
    private final List<Disposable> disposeList;

    public Presenter(V pView, M pModule) {
        mView=new SoftReference<>(pView);
        mModule=new SoftReference<>(pModule);
        disposeList = new ArrayList<>();
    }

    @Override
    public void Success(int whichApi, Object... pV) {
        if(mView!=null&&mView.get()!=null)mView.get().Success(whichApi, pV);
    }

    @Override
    public void Failed(int whichApi, Throwable pThrowable) {
        if(mView!=null&&mView.get()!=null)mView.get().Failed(whichApi, pThrowable);
    }

    @Override
    public void getData(int whichApi, Object... pP) {
        if(mModule!=null&&mModule.get()!=null)mModule.get().getData(this, whichApi, pP);

    }

    @Override
    public void addDisposable(Disposable disposable) {
        disposeList.add(disposable);
    }

    public void clear(){
        for (int i = 0; i < disposeList.size(); i++) {
            if(disposeList.get(i)!=null){
                disposeList.get(i).dispose();
            }
        }
        if(mView!=null){
            mView.clear();
            mView=null;
        }

        if(mModule!=null){
            mModule.clear();
            mModule=null;
        }

    }
}
