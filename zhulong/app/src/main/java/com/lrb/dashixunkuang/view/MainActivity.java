package com.lrb.dashixunkuang.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;
import com.lrb.dashixunkuang.R;
import com.lrb.dashixunkuang.base.BaseMvpActivity;
import com.lrb.dashixunkuang.fragment.CurriculumFragment;
import com.lrb.dashixunkuang.fragment.HomeFragment;
import com.lrb.dashixunkuang.fragment.MaterialFragment;
import com.lrb.dashixunkuang.fragment.MyCenterFragment;
import com.lrb.dashixunkuang.fragment.VIPFragment;
import com.lrb.mvp.Contract;

public class MainActivity extends BaseMvpActivity {


    private Toolbar toolbar;
    private TabLayout tab;
    private final int HOME = 0;
    private final int CURRICULUM = 1;
    private final int VIP = 2;
    private final int MATERIAL = 3;
    private final int MY = 4;
    private int fragmentType;
    private HomeFragment homeFragment;
    private CurriculumFragment curriculumFragment;
    private VIPFragment vipFragment;
    private MaterialFragment materialFragment;
    private MyCenterFragment myCenterFragment;
    private FragmentManager supportFragmentManager;
    private Fragment lastFragment;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        toolbar=findViewById(R.id.toolbar);
        tab=findViewById(R.id.tab);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        initFragment();
        tabSwitch();


    }

    private void initFragment() {
        homeFragment = new HomeFragment();
        curriculumFragment = new CurriculumFragment();
        vipFragment = new VIPFragment();
        materialFragment = new MaterialFragment();
        myCenterFragment = new MyCenterFragment();
        supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.ll_content, homeFragment)
                .add(R.id.ll_content, curriculumFragment)
                .add(R.id.ll_content, vipFragment)
                .add(R.id.ll_content, materialFragment)
                .add(R.id.ll_content, myCenterFragment)
                .hide(curriculumFragment)
                .hide(vipFragment)
                .hide(materialFragment)
                .hide(myCenterFragment)
                .commit();
        lastFragment = homeFragment;
    }

    private void tabSwitch() {
        tab.addTab(tab.newTab().setCustomView(getView(R.drawable.tab_home,"首页")));
        tab.addTab(tab.newTab().setCustomView(getView(R.drawable.tab_curriculum,"课程")));
        tab.addTab(tab.newTab().setCustomView(getView(R.drawable.tab_vip,"VIP")));
        tab.addTab(tab.newTab().setCustomView(getView(R.drawable.tab_material,"资料")));
        tab.addTab(tab.newTab().setCustomView(getView(R.drawable.tab_mycenter,"我的")));

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        fragmentType=HOME;
                        break;
                    case 1:
                        fragmentType = CURRICULUM;
                        break;
                    case 2:
                        fragmentType = VIP;
                        break;
                    case 3:
                        fragmentType = MATERIAL;
                        break;
                    case 4:
                        fragmentType = MY;
                        break;
                }
                Fragmentswitch();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void Fragmentswitch() {
        Fragment currFragment = getCurrFragment();
        if(currFragment==lastFragment){
            return;
        }
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.show(currFragment).hide(lastFragment).commit();
        lastFragment=currFragment;
    }

    private Fragment getCurrFragment() {
        switch (fragmentType) {
            case HOME:
                if(homeFragment==null){
                    homeFragment=new HomeFragment();
                }
                return homeFragment;
            case CURRICULUM:
                if(curriculumFragment==null){
                    curriculumFragment=new CurriculumFragment();
                }
                return curriculumFragment;
            case VIP:
                if(vipFragment==null){
                    vipFragment=new VIPFragment();
                }
                return vipFragment;
            case MATERIAL:
                if(materialFragment==null){
                    materialFragment=new MaterialFragment();
                }
                return materialFragment;
            case MY:
                if(myCenterFragment==null){
                    myCenterFragment=new MyCenterFragment();
                }
                return myCenterFragment;
        }
        return null;
    }

    private View getView(int port,String title) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.tab, null);
        TextView tab_title = inflate.findViewById(R.id.tab_title);
        ImageView tab_img = inflate.findViewById(R.id.tab_img);
        tab_img.setImageResource(port);
        tab_title.setText(title);
        return inflate;
    }

    @Override
    protected Contract.CommonModule initModule() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void netSuccess(int whichApi, Object[] pV) {

    }


}