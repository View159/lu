package com.lrb.mvp;

import com.lrb.infobean.FirstBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("ad/getAd")
    Observable<FirstBean> getFirstImage(@FieldMap Map<String,String> map);

}
