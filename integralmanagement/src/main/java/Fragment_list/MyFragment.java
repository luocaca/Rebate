package Fragment_list;


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

import Adapter.VpAdapter6;
import RankingFragment_list.RankApprenticeFragment;
import RankingFragment_list.RankReceiptFragment;
import application.ApplicationClass;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {
    private ViewPager mViewpage;
    private TabLayout mTablyout;
    private ApplicationClass applicationClass;
    private String Authorization = "";
    private int Id;
    private RankApprenticeFragment rankApprenticeFragment=new RankApprenticeFragment();
    private RankReceiptFragment rankReceiptFragment=new RankReceiptFragment();

    public MyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        applicationClass= (ApplicationClass) getActivity().getApplication();
        mViewpage = view.findViewById(R.id.rank_page);
        mTablyout = view.findViewById(R.id.tablayout);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Authorization = bundle.getString("Authorization");
            Id = bundle.getInt("Id", 0);
            Log.i("IDID", "onCreateView: " + Id);

        }
        bundle.putInt("Id",Id);
//        bundle.putString("host", );
        bundle.putString("Authorization", Authorization);
        rankApprenticeFragment.setArguments(bundle);
        rankReceiptFragment.setArguments(bundle);
        initViewPage();
        return view;
    }

    private void initViewPage() {
        mViewpage.setAdapter(new VpAdapter6(getChildFragmentManager(), getInitFragment()));
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
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private List<Fragment> getInitFragment() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(rankReceiptFragment);
        fragments.add(rankApprenticeFragment);
        return fragments;
    }

}
