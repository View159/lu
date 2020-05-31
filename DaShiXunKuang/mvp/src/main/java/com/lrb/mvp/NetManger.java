package com.lrb.mvp;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetManger {
    private static NetManger netManger;

    private NetManger() {
    }

    public static NetManger getInstance(){
        if(netManger==null){
            synchronized (NetManger.class){
                if(netManger==null){
                    netManger=new NetManger();
                }
            }
        }
        return netManger;
    }

    public <T> ApiService getApiService(T...t){
        String baseUrl=ServerConfig.BASEURL;
        if(t!=null&&t.length!=0){
            baseUrl= (String) t[0];
        }

      return   new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(ApiService.class);
    }

    public <T> void getLoadData(Observable observable, final Contract.CommonPresenter presenter, final int whichApi, T...t){

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                        presenter.addDisposable(d);
                    }

                    @Override
                    public void OnSuccess(Object o) {
                        presenter.Success(whichApi,o);
                    }

                    @Override
                    public void OnFailed(Throwable e) {
                        presenter.Failed(whichApi,e);
                    }
                });
    }

}
