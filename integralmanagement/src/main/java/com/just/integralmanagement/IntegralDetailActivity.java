package com.just.integralmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Adapter.VpAdapter5;
import IntegralDeatail_List.HistoryIntegralDetailFragment;
import IntegralDeatail_List.TodayIntegralDeatilFragment;
import IntegralDeatail_List.TotalIntegralDetailFragment;
import application.ApplicationClass;

/**
 * 积分明细
 */

public class IntegralDetailActivity extends AppCompatActivity {
    private ViewPager mViewpage;
    private TabLayout mTablyout;
    private String stringbeginTime, stringendTime;
    private TotalIntegralDetailFragment totalIntegralDetailFragment = new TotalIntegralDetailFragment();
    private TodayIntegralDeatilFragment todayIntegralDeatilFragment = new TodayIntegralDeatilFragment();
    private HistoryIntegralDetailFragment historyIntegralDetailFragment = new HistoryIntegralDetailFragment();
    private String Authorization="";
    private int Id;
    private ApplicationClass applicationClass;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral_detail);
        applicationClass= (ApplicationClass) getApplication();
        mViewpage = findViewById(R.id.Integral_Detail_Page);
        mTablyout = findViewById(R.id.Integral_Detail_TabLayout);
        initReceiveData();
        getData();
        initViewPage();
        setToolbars();
        getSupportActionBar().setTitle("积分明细");
    }

    private void initReceiveData() {
        Intent intent=getIntent();
        Id=intent.getIntExtra("Id",0);
        Log.i("putInt", "initReceiveData: "+Id);
        Authorization = intent.getStringExtra("Authorization");
        Log.i("TAG", "initReceiveData: "+Id+"    "+Authorization);
    }

    private void getData() {
        Log.i("IDIDIDID", "getData: "+Id);
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Date date1=new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        Date date2=new Date(System.currentTimeMillis());
        stringbeginTime=simpleDateFormat1.format(date1);
        stringendTime=simpleDateFormat2.format(date2);
        Bundle bundle1 = new Bundle();
        bundle1.putInt("Id",Id);
        Log.i("putInt", "initReceiveData: "+Id);
        bundle1.putString("BeginTime", stringbeginTime);
        bundle1.putString("EndTime", stringendTime);
        bundle1.putString("Authorization",Authorization);
        totalIntegralDetailFragment.setArguments(bundle1);
        todayIntegralDeatilFragment.setArguments(bundle1);
        historyIntegralDetailFragment.setArguments(bundle1);
    }

    private void setToolbars() {
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayShowHomeEnabled(true);//这两句就可以让actionBar的图标可以响应点击事件
        mActionBar.setDisplayHomeAsUpEnabled(true);//这一句主要用于后面返回效果，后面会讲
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:  //id不要写错，前面要加android
                onBackPressed();
                break;
        }
        return true;
    }

    private void initViewPage() {
        mViewpage.setAdapter(new VpAdapter5(getSupportFragmentManager(), getInitFragment()));
        mViewpage.setOffscreenPageLimit(8);
        mTablyout.setupWithViewPager(mViewpage);
        mViewpage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mViewpage.setCurrentItem(0);
                } else if (position == 1) {
                    mViewpage.setCurrentItem(1);
                } else if (position == 2) {
                    mViewpage.setCurrentItem(2);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private List<Fragment> getInitFragment() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(totalIntegralDetailFragment);
        fragmentList.add(todayIntegralDeatilFragment);
        fragmentList.add(historyIntegralDetailFragment);
        return fragmentList;
    }

}
