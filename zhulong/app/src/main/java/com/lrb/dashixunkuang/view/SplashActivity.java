package com.lrb.dashixunkuang.view;

import android.content.Intent;
import android.graphics.Point;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.lrb.dashixunkuang.FirstModule;
import com.lrb.dashixunkuang.R;
import com.lrb.dashixunkuang.base.BaseSplashActivity;
import com.lrb.infobean.FirstBean;
import com.lrb.infobean.MainAdEntity;
import com.lrb.infobean.SpecialtyChooseEntity;
import com.lrb.mvp.ApiConfig;
import com.lrb.mvp.Contract;
import com.lrb.mvp.secret.SystemUtils;
import com.teach.frame.constants.ConstantKey;
import com.yiyatech.utils.newAdd.GlideUtil;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseSplashActivity implements View.OnClickListener {

    private ImageView iv_img;
    private TextView tv_skip;
    private int duration = 5;
    private Timer timer;
    private SpecialtyChooseEntity.DataBean selectedInfo;


    @Override
    protected void initData() {
        selectedInfo = SharedPrefrenceUtils.getObject(this, ConstantKey.SUBJECT_SELECT);
        String specialtyId = "";
        if (selectedInfo != null && !TextUtils.isEmpty(selectedInfo.getSpecialty_id())) {

            mApplication.setSelectedInfo(selectedInfo);
            specialtyId = selectedInfo.getSpecialty_id();
        }
        Point realSize = SystemUtils.getRealSize(this);
        pPresenter.getData(ApiConfig.FIRST, specialtyId, realSize.x, realSize.y);


    }


    @Override
    protected void initView() {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initDevice();
        iv_img = findViewById(R.id.iv_img);
        tv_skip = findViewById(R.id.tv_skip);
        tv_skip.setOnClickListener(this);
    }

    @Override
    protected Contract.CommonModule initModule() {
        return new FirstModule();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void netSuccess(int whichApi, Object[] pV) {
        switch (whichApi) {
            case ApiConfig.FIRST:
                FirstBean<MainAdEntity> mainAdEntityFirstBean = (FirstBean<MainAdEntity>) pV[0];
                GlideUtil.loadImage(iv_img, mainAdEntityFirstBean.result.getInfo_url());
                tv_skip.setVisibility(View.VISIBLE);
                startAdTimer();
                break;
        }
    }

    private void startAdTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (duration == 1) {
                    jump();
                    finish();
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            duration--;
                            tv_skip.setText("跳过 " + duration);
                        }
                    });
                }

            }
        }, 1000, 1000);


    }

    private void jump() {
        startActivity(new Intent(this,selectedInfo != null && !TextUtils.isEmpty(selectedInfo.getSpecialty_id()) ? mApplication.isLogin() ? MainActivity.class : LoginActivity.class : SubjectActivity.class ));
            finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_skip:
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
                break;
        }
    }


}
