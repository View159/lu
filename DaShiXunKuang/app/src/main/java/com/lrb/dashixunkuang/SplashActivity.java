package com.lrb.dashixunkuang;

import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lrb.dashixunkuang.base.BaseMvpActivity;
import com.lrb.infobean.FirstBean;
import com.lrb.mvp.ApiConfig;
import com.lrb.mvp.Contract;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseMvpActivity implements View.OnClickListener {

    private ImageView iv_img;
    private TextView tv_skip;
    private int duration=5;
    private Timer timer;
    private Runnable runnable;
    private Thread thread;

    @Override
    protected void initData() {

        HashMap<String, String> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("positions_id", "APP_QD_01");
        objectObjectHashMap.put("is_show", "0");
        pPresenter.getData(ApiConfig.FIRST, objectObjectHashMap);
    }



    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //状态栏透明
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        iv_img = findViewById(R.id.iv_img);
        tv_skip=findViewById(R.id.tv_skip);
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
                final FirstBean firstBean = (FirstBean) pV[0];

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            iv_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            if (firstBean.getErrNo() == 0) {
                                String info_url = firstBean.getResult().getInfo_url();
                                Glide.with(SplashActivity.this).load(info_url).into(iv_img);
                                tv_skip.setVisibility(View.VISIBLE);
                                startAdTimer();
                            }
                        }
                    });

                }
            }).start();



                break;
        }
    }

    private void startAdTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(duration==1){
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            duration--;
                            tv_skip.setText("跳过 "+duration);
                        }
                    });


                }

            }
        },1000,1000);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer!=null){
            timer.cancel();;
            timer=null;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_skip:
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
                break;
        }
    }
}
