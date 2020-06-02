package com.lrb.mvp;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.lrb.infobean.Device;
import com.lrb.infobean.LoginInfo;
import com.lrb.infobean.SpecialtyChooseEntity;
import com.yiyatech.utils.UtilsApplication;


public class FrameApplication extends UtilsApplication {
    private static FrameApplication application;
    private Device mDeviceInfo;
    private LoginInfo mLoginInfo;
    private String cookie;
    private  SpecialtyChooseEntity.DataBean selectedInfo;
    public String getCookie() {
        return cookie;
    }

    public void setSelectedInfo(SpecialtyChooseEntity.DataBean pSelectedInfo) {
        selectedInfo = pSelectedInfo;
    }
    public SpecialtyChooseEntity.DataBean getSelectedInfo() {
        return selectedInfo;
    }
    public boolean isLogin(){
        return mLoginInfo != null && !TextUtils.isEmpty(mLoginInfo.getUid());
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public LoginInfo getLoginInfo() {
        return mLoginInfo;
    }

    public void setLoginInfo(LoginInfo mLoginInfo) {
        this.mLoginInfo = mLoginInfo;
    }

    public Device getDeviceInfo() {
        return mDeviceInfo;
    }

    public void setDeviceInfo(Device mDeviceInfo) {
        this.mDeviceInfo = mDeviceInfo;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static FrameApplication getFrameApplication(){
        return application;
    }

    public static Context getFrameApplicationContext(){
        return application.getApplicationContext();
    }
}
