package com.just.rebate.adapter.viewpager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.rebate.base.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPageAdapter extends FragmentPagerAdapter {


    private List<BaseFragment> fragmentList;
    public ViewPageAdapter(FragmentManager fm, List<BaseFragment> baseFragments) {
        super(fm);
        if (fragmentList == null) {
            fragmentList = new ArrayList<>();
        }
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

    public CharSequence getPageTitle(int position){
        if (position == 0) {
            return "最近下单";
        } else if (position == 1) {
            return "跟踪处理";
        } else if (position == 2) {
            return "最近到账";
        } else if (position == 3) {
            return "无效订单";
        }
        return "";
    }
}
