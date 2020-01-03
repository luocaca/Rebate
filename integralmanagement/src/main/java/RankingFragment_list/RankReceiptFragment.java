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

import Adapter.VpAdapter4;
import RankingFragment_list.RankReceipt_List.Month_Receipt_RankFragment;
import RankingFragment_list.RankReceipt_List.Week_Receipt_RankFragment;
import RankingFragment_list.RankReceipt_List.Yesterday_Receipt_RankFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankReceiptFragment extends Fragment {
    private ViewPager mViewpage;
    private TabLayout mTablyout;
    private String Authorization = "";
    private int Id;
    private Yesterday_Receipt_RankFragment yesterday_receipt_rankFragment=new Yesterday_Receipt_RankFragment();
    private Week_Receipt_RankFragment week_receipt_rankFragment=new Week_Receipt_RankFragment();
    private Month_Receipt_RankFragment month_receipt_rankFragment=new Month_Receipt_RankFragment();
    
    public RankReceiptFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_rank_receipt, container, false);
        mViewpage=view.findViewById(R.id.Rank_Page);
        mTablyout=view.findViewById(R.id.Tablayout);
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
        yesterday_receipt_rankFragment.setArguments(bundle);
        week_receipt_rankFragment.setArguments(bundle);
        month_receipt_rankFragment.setArguments(bundle);
        return view;
    }

    private void initViewPage() {
        mViewpage.setAdapter(new VpAdapter4(getChildFragmentManager(),getInitFragment()));
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
        fragments.add(yesterday_receipt_rankFragment);
        fragments.add(week_receipt_rankFragment);
        fragments.add(month_receipt_rankFragment);
        return fragments;
    }

}
