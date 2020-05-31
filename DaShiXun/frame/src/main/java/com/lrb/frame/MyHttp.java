package com.lrb.frame;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyHttp<T> {

    private static  MyHttp myHttp;
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

    public <T> ApiService getRetrofit(T ...t){
        String baseUrl=ServerAddressConfig.BASE_URL;
        if(t!=null&&t.length!=0){
            baseUrl=t.toString();
        }
       return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(ApiService.class);
    }

    public void netWork(Observable<T> ocalTestInfo,final ICommonPresenter pPresenter, final int whichApi, final int loadType){
        ocalTestInfo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObservable() {
                    @Override
                    public void Success(Object values) {
                        pPresenter.Success(whichApi, loadType, values);
                    }

                    @Override
                    public void Failed(Throwable e) {
                        pPresenter.Falied(whichApi,e);
                    }
                });
    }

}
