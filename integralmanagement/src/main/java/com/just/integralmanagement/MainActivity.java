package com.just.integralmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import Adapter.VpAdapter;
import Fragment_list.ApprenticeFragment;
import Fragment_list.HomeFragment;
import Fragment_list.MyFragment;
import Fragment_list.SaveMoenyFragment;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPage;
    private BottomNavigationView navView;
    private HomeFragment homeFragment = new HomeFragment();
    private SaveMoenyFragment saveMoenyFragment = new SaveMoenyFragment();
    private ApprenticeFragment apprenticeFragment = new ApprenticeFragment();
    private MyFragment myFragment = new MyFragment();
    private int Id;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_Home:
                mViewPage.setCurrentItem(0);
                return true;
            case R.id.navigation_Device:
                mViewPage.setCurrentItem(1);
                return true;
            case R.id.navigation_Detail:
                mViewPage.setCurrentItem(2);
                return true;
            case R.id.navigation_Query:
                mViewPage.setCurrentItem(3);
                return true;
        }
        return false;
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);
        mViewPage = findViewById(R.id.viewpage);
        getData();
        initViewPager();
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setSelectedItemId(R.id.navigation_Home);
        getSupportActionBar().hide();
        homeFragment.setOnFragmentClick(new HomeFragment.FragmentOnClick() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.Apprentice:
                        mViewPage.setCurrentItem(2);
                        break;
                    case R.id.save_moeny:
                        mViewPage.setCurrentItem(1);
                        break;
                    case R.id.Integral_Rank:
                        mViewPage.setCurrentItem(3);
                        break;
                }
            }
        });
    }

    private void getData() {
        Intent intent = getIntent();
        Id = intent.getIntExtra("Id", 0);
        Log.i("IDID", "getData: " + Id);
        Bundle bundle = new Bundle();
        bundle.putString("UserName", intent.getStringExtra("UserName"));
        bundle.putInt("Id", intent.getIntExtra("Id", 0));
//        bundle.putString("host", );
        Log.i("IDID", "getData: " + intent.getIntExtra("Id", 0));
        bundle.putString("Authorization", intent.getStringExtra("Authorization"));
        homeFragment.setArguments(bundle);
        saveMoenyFragment.setArguments(bundle);
        apprenticeFragment.setArguments(bundle);
        myFragment.setArguments(bundle);
    }

    private void initViewPager() {
        mViewPage.setAdapter(new VpAdapter(getSupportFragmentManager(), getInitFragment(), navView));
        mViewPage.setOffscreenPageLimit(8);
        mViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    navView.setSelectedItemId(R.id.navigation_Home);
                } else if (position == 1) {
                    navView.setSelectedItemId(R.id.navigation_Device);
                } else if (position == 2) {
                    navView.setSelectedItemId(R.id.navigation_Detail);
                } else if (position == 3) {
                    navView.setSelectedItemId(R.id.navigation_Query);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    /**
     * 获取首页 需要初始化的  fragment
     *
     * @return
     */
    private List<Fragment> getInitFragment() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(homeFragment);
        fragments.add(saveMoenyFragment);
        fragments.add(apprenticeFragment);
        fragments.add(myFragment);//邀请界面
        return fragments;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 80) {
            saveMoenyFragment.onActivityResult(80, 80, null);
        } else if (resultCode == 100) {
            saveMoenyFragment.onActivityResult(100, 100, null);
        } else if (resultCode == 150) {
            homeFragment.onActivityResult(150, 150, null);
        }
    }

}
