package com.just.integralmanagement;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import Adapter.VpAdapter2;
import RankingFragment_list.RankApprenticeFragment;
import RankingFragment_list.RankReceiptFragment;

/**
 * 排行界面
 */

public class RankingActivity extends AppCompatActivity {
    private ViewPager mViewPage;
    private BottomNavigationView mNavView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.Ranking_Receipt:
                mViewPage.setCurrentItem(0);
                return true;
            case R.id.Ranking_Apprentice:
                mViewPage.setCurrentItem(1);
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        mNavView = findViewById(R.id.Nav_View);
        mViewPage = findViewById(R.id.ViewPage);
        initViewPager();
        mNavView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mNavView.setSelectedItemId(R.id.Ranking_Receipt);
    }

    private void initViewPager() {
        mViewPage.setAdapter(new VpAdapter2(getSupportFragmentManager(), getInitFragment(), mNavView));
        mViewPage.setOffscreenPageLimit(8);
        mViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mNavView.setSelectedItemId(R.id.Ranking_Receipt);
                } else if (position == 1) {
                    mNavView.setSelectedItemId(R.id.Ranking_Apprentice);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private List<Fragment> getInitFragment() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new RankApprenticeFragment());
        fragments.add(new RankReceiptFragment());
        return fragments;
    }
}
