package com.lrb.frame;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class TestPresenter<V extends Contract.CommonView, M extends Contract.CommonModul> implements Contract.CommonPresenter {

    private SoftReference<V> IView;
    private SoftReference<M> IModul;
    private List<Disposable> addDisposa;

    public TestPresenter(V pView, M pModul) {
        IView = new SoftReference<>(pView);
        IModul = new SoftReference<>(pModul);
        addDisposa = new ArrayList<>();
    }

    @Override
    public void Success(int whichApi, int loadType, Object[] pv) {
        if (IView != null && IView.get() != null) {
            IView.get().Success(whichApi, loadType, pv);
        }
    }

    @Override
    public void Failed(int whichApi, Throwable throwable) {
        if (IView != null && IView.get() != null) {
            IView.get().Failed(whichApi, throwable);
        }
    }

    @Override
    public void getData(int whichApi, Object... pP) {
        if (IModul != null && IModul.get() != null) {
            IModul.get().getData(this, whichApi, pP);
        }
    }

    @Override
    public void addDispose(Disposable disposable) {
        if (addDisposa == null) return;
        addDisposa.add(disposable);
    }

    public void clear() {
        for (Disposable dis : addDisposa) {
            if (dis != null && !dis.isDisposed()) dis.dispose();
        }
        if (IView != null) {
            IView.clear();
            IView = null;
        }
        if (IModul != null) {
            IModul.clear();
            IModul = null;
        }
    }
}
