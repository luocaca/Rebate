package RankingFragment_list;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.just.integralmanagement.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.VpAdapter3;
import RankingFragment_list.RankApprentice_List.MonthRankFragment;
import RankingFragment_list.RankApprentice_List.WeekFragment;
import RankingFragment_list.RankApprentice_List.YesterdayRankFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankApprenticeFragment extends Fragment {
    private ViewPager mViewpage;
    private TabLayout mTablyout;
    private String Authorization = "";
    private int Id;
    private YesterdayRankFragment yesterdayRankFragment=new YesterdayRankFragment();
    private WeekFragment weekFragment=new WeekFragment();
    private MonthRankFragment monthRankFragment=new MonthRankFragment();


    public RankApprenticeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_rank_apprentice, container, false);
        mViewpage=view.findViewById(R.id.Rank_page);
        mTablyout=view.findViewById(R.id.TabLayout);
        initViewPage();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Authorization = bundle.getString("Authorization");
            Id = bundle.getInt("Id", 0);
            Log.i("IDID", "onCreateView: " + Id);

        }
        bundle.putInt("Id",Id);
//        bundle.putString("host", );
        bundle.putString("Authorization", Authorization);
        yesterdayRankFragment.setArguments(bundle);
        weekFragment.setArguments(bundle);
        monthRankFragment.setArguments(bundle);
        return view;
    }

    private void initViewPage() {
        mViewpage.setAdapter(new VpAdapter3(getChildFragmentManager(),getInitFragment()));
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
        List<Fragment> fragments=new ArrayList<>();
        fragments.add(yesterdayRankFragment);
        fragments.add(weekFragment);
        fragments.add(monthRankFragment);
        return fragments;
    }


}
