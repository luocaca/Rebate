package com.just.rebate.adapter.viewpager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rebate.base.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnPageChange;

public class VpAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragmentList;
    private BottomNavigationView navView;

    public VpAdapter(FragmentManager fm, List<BaseFragment> baseFragments, BottomNavigationView navView) {
        super(fm);
        if (fragmentList == null) {
            fragmentList = new ArrayList<>();
        }
        this.navView = navView;
        this.fragmentList = baseFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }





}
