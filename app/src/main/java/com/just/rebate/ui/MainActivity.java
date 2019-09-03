package com.just.rebate.ui;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.just.rebate.R;
import com.just.rebate.ui.fragment.InviteFragment;
import com.rebate.base.fragment.BaseFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.just.rebate.adapter.viewpager.VpAdapter;
import com.just.rebate.ui.fragment.HomeFragment;
import com.just.rebate.ui.fragment.OrderFragment;
import com.just.rebate.ui.fragment.PersonalFragment;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.view_pager)
    public ViewPager mViewPager;

    @BindView(R.id.nav_view)
    public BottomNavigationView navView;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {

        switch (item.getItemId()) {
            case R.id.navigation_home://首页
                mViewPager.setCurrentItem(0);
                break;

            case R.id.navigation_order://订单
                mViewPager.setCurrentItem(1);

                break;
            case R.id.navigation_invite://邀请
                mViewPager.setCurrentItem(2);

                break;
            case R.id.navigation_mime://我的
                mViewPager.setCurrentItem(3);

                break;
        }

        return true;
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        initViewPager();


        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }


    /**
     * 初始化view pager
     */
    private void initViewPager() {

        mViewPager.setAdapter(new VpAdapter(getSupportFragmentManager(), getInitFragment(), navView));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    navView.setSelectedItemId(R.id.navigation_home);
                } else if (position == 1) {
                    navView.setSelectedItemId(R.id.navigation_order);
                } else if (position == 2) {
                    navView.setSelectedItemId(R.id.navigation_invite);
                } else if (position == 3) {
                    navView.setSelectedItemId(R.id.navigation_mime);
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
    private List<BaseFragment> getInitFragment() {
        // new HomeFragment();
        List<BaseFragment> baseFragments = new ArrayList<>();
        baseFragments.add(new HomeFragment());//首页
        baseFragments.add(new OrderFragment());//订单
//        baseFragments.add(new RechargeFragment());//充值界面
        baseFragments.add(new InviteFragment());//邀请界面
        baseFragments.add(new PersonalFragment());//个人中心
        return baseFragments;
    }

}
