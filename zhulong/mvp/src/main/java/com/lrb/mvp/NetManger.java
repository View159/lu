package com.lrb.mvp;

import android.util.Log;

import com.lrb.infobean.FirstBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
                .client(initClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(ApiService.class);
    }

    private OkHttpClient initClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.addInterceptor(new CommonHeadersInterceptor());
        builder.addInterceptor(new CommonParamsInterceptor());
        builder.addInterceptor(initLogInterceptor());
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(15,TimeUnit.SECONDS);
        return builder.build();
    }

    private Interceptor initLogInterceptor(){
        HttpLoggingInterceptor log = new HttpLoggingInterceptor();
        log.setLevel(HttpLoggingInterceptor.Level.BODY);
        return log;
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
