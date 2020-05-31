package com.lrb.extract.base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;

import com.lrb.extract.R;
import com.lrb.frame.Contract;
import com.lrb.frame.TestPresenter;

public abstract class BaseMvpActivity<M extends Contract.CommonModul> extends BaseActivity implements  Contract.CommonView{
    M mModul;
    protected TestPresenter testPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mModul=initModul();
        testPresenter = new TestPresenter(this, mModul);
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract M initModul();

    protected abstract int getLayout();
    protected abstract void netSuccess(int whichApi, int loadType, Object[] pv);
    protected  void netFailed(int whichApi, Throwable throwable){

    }

    @Override
    public void Success(int whichApi, int loadType, Object[] pv) {
        netSuccess( whichApi,  loadType, pv);
    }

    @Override
    public void Failed(int whichApi, Throwable throwable) {
        showLog(whichApi+"content:"+throwable != null && !TextUtils.isEmpty(throwable.getMessage()) ? throwable.getMessage() : "不明错误类型");
        netFailed( whichApi,  throwable);
    }
}
