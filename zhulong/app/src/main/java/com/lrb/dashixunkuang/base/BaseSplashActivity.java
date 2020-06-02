package com.lrb.dashixunkuang.base;

import com.lrb.infobean.Device;
import com.lrb.mvp.Contract;
import com.lrb.mvp.FrameApplication;
import com.lrb.mvp.secret.SystemUtils;
import com.yiyatech.utils.NetworkUtils;

public abstract class BaseSplashActivity extends BaseMvpActivity {


    public void initDevice() {
        Device device = new Device();
        device.setScreenWidth(SystemUtils.getSize(this).x);
        device.setScreenHeight(SystemUtils.getSize(this).y);
        device.setDeviceName(SystemUtils.getDeviceName());
        device.setSystem(SystemUtils.getSystem(this));
        device.setVersion(SystemUtils.getVersion(this));
        device.setDeviceId(SystemUtils.getDeviceId(this));
        device.setLocalIp(NetworkUtils.getLocalIpAddress());
        FrameApplication.getFrameApplication().setDeviceInfo(device);
    }
}
