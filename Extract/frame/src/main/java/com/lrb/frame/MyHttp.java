package com.lrb.frame;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyHttp {
    private static MyHttp myHttp;

    private MyHttp() {
    }

    public static MyHttp getInstance(){
        if(myHttp==null){
            synchronized (MyHttp.class){
                if(myHttp==null){
                    myHttp=new MyHttp();
                }
            }
        }
        return myHttp;
    }


    public <T> ApiService getretrofit(T...pT){
        String baseUrl=ServerAddressConfig.BASE_URL;
        if(pT!=null&&pT.length!=0){
            baseUrl= (String) pT[0];
        }
    return new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService.class);

    }

    public<T,O> void netWork(Observable<T> observer, final Contract.CommonPresenter presenter, final int whichApi, final int loadType){

        observer.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                        presenter.addDispose(d);
                    }

                    @Override
                    public void onSuccess(Object o) {
                        presenter.Success(whichApi, loadType, o);
                    }

                    @Override
                    public void failed(Throwable e) {
                        presenter.Failed(whichApi,e);
                    }
                });
    }
}
