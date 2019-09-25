package com.just.rebate.ui.fragment;

import android.content.Intent;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.just.rebate.R;
import com.just.rebate.adapter.viewpager.ViewPageAdapter;
import com.just.rebate.ui.activity.MessageNotificationActivity;
import com.just.rebate.ui.fragment.FragmentHome_list.ArrivalAccountFragment;
import com.just.rebate.ui.fragment.FragmentHome_list.InvalidFragment;
import com.just.rebate.ui.fragment.FragmentHome_list.OrderFragment;
import com.just.rebate.ui.fragment.FragmentHome_list.TrackingProcessingFragment;
import com.rebate.base.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderFragmentHome extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.order_page)
    ViewPager mViewPage;

    @BindView(R.id.TabLayout)
    TabLayout mTablyout;

    @OnClick({R.id.img_msg})
    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.img_msg:
                intent = new Intent(getActivity(), MessageNotificationActivity.class);
                startActivity(intent);
                break;
        }

    }

    @Override
    protected int bindFragmentLayoutId() {
        return R.layout.fragment_order_home;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        initViewPager();

    }

    @Override
    protected void initData() {

    }

    private void initViewPager() {
        mViewPage.setAdapter(new ViewPageAdapter(getChildFragmentManager(), getInitFragment()));

        mViewPage.setOffscreenPageLimit(8);
        mTablyout.setupWithViewPager(mViewPage);
        mViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mViewPage.setCurrentItem(0);
                } else if (position == 1) {
                    mViewPage.setCurrentItem(1);
                } else if (position == 2) {
                    mViewPage.setCurrentItem(2);
                } else if (position == 3) {
                    mViewPage.setCurrentItem(3);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private List<BaseFragment> getInitFragment() {
        List<BaseFragment> baseFragments = new ArrayList<>();
        baseFragments.add(new OrderFragment());
        baseFragments.add(new TrackingProcessingFragment());
        baseFragments.add(new ArrivalAccountFragment());
        baseFragments.add(new InvalidFragment());
        return baseFragments;


    }

}
