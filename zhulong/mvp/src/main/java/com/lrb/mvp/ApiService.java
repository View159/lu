package com.lrb.mvp;

import com.lrb.infobean.BaseInfo;
import com.lrb.infobean.FirstBean;
import com.lrb.infobean.MainAdEntity;
import com.lrb.infobean.SpecialtyChooseEntity;
import com.lrb.mvp.utils.ParamHashMap;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface ApiService {

   @GET("ad/getAd")
    Observable<FirstBean<MainAdEntity>> getFirstImage(@QueryMap ParamHashMap map);

    @GET("lesson/getAllspecialty")
    Observable<BaseInfo<List<SpecialtyChooseEntity>>> getSubjectList();
}
