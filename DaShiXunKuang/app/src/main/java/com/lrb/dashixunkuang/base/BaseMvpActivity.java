package com.lrb.dashixunkuang.base;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.lrb.dashixunkuang.R;
import com.lrb.mvp.Contract;
import com.lrb.mvp.Presenter;

 public abstract class  BaseMvpActivity <M extends Contract.CommonModule> extends BaseActivity implements Contract.CommonView {

    M pModule;
    protected Presenter pPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        pModule=initModule();
        pPresenter = new Presenter(this, pModule);
        initView();
        initData();

    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract M initModule();

    protected abstract int getLayout();

    protected abstract void netSuccess(int whichApi, Object[] pV);

    protected  void netFailed(int whichApi, Throwable pThrowable){

    }

    @Override
    public void Success(int whichApi, Object[] pV) {
        netSuccess(whichApi,pV);
    }

    @Override
    public void Failed(int whichApi, Throwable pThrowable) {
        showLog(whichApi+"content throwable:"+pThrowable!=null&& !TextUtils.isEmpty(pThrowable.getMessage())?pThrowable.getMessage():"不明错误类型");
        netFailed(whichApi,pThrowable);
    }
}

