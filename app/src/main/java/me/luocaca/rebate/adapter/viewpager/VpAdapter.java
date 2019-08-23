package me.luocaca.rebate.adapter.viewpager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.rebate.base.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class VpAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragmentList;

    public VpAdapter(FragmentManager fm, List<BaseFragment> baseFragments) {
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


}
