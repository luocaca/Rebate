package Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class VpAdapter4 extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    public VpAdapter4(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        if (fragmentList == null) {
            fragmentList = new ArrayList<>();
        }
        this.fragmentList = fragments;
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
            return "昨日排行榜";
        } else if (position == 1) {
            return "周排行榜";
        } else if (position == 2) {
            return "月排行榜";
        }
        return "";
    }





}
