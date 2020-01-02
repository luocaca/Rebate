package com.just.rebate.ui;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.just.rebate.R;
import com.just.rebate.adapter.viewpager.VpAdapter;
import com.just.rebate.entity.GetRuleData;
import com.just.rebate.ui.fragment.HomeFragment;
import com.just.rebate.ui.fragment.InviteFragment;
import com.just.rebate.ui.fragment.OrderFragmentHome;
import com.just.rebate.ui.fragment.PersonalFragment;
import com.rebate.base.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private List<GetRuleData>getRuleData=new ArrayList<>();

    @BindView(R.id.view_pager)
    public ViewPager mViewPager;

    @BindView(R.id.nav_view)
    public BottomNavigationView navView;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        refreshItemIcon();
        switch (item.getItemId()) {
            case R.id.navigation_home://首页
                mViewPager.setCurrentItem(0);
                item.setIcon(R.mipmap.home1);
                return true;
            case R.id.navigation_order://订单
                mViewPager.setCurrentItem(1);
                item.setIcon(R.mipmap.dingdan1);
                return true;
            case R.id.navigation_invite://邀请
                mViewPager.setCurrentItem(2);
                item.setIcon(R.mipmap.yaoqing1);
                return true;
            case R.id.navigation_mime://我的
                mViewPager.setCurrentItem(3);
                item.setIcon(R.mipmap.wode1);
                return true;
        }

        return true;
    };
    /*protected void onResume(){
        int id = getIntent().getIntExtra("id",1);
        if (id ==2){
            Fragment fragment =new Fragment();
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction transaction=fragmentManager.beginTransaction();
            transaction.replace(R.id.view_pager,fragment);
            transaction.commit();
            mViewPager.setCurrentItem(1);
            Intent intent=new Intent();
            intent.setClass(MainActivity.this,OrderFragment.class);
            intent.putExtra("id",1);
        }
        super.onResume();
    }*/



    public void refreshItemIcon() {
        MenuItem item0 = navView.getMenu().findItem(R.id.navigation_home);
        item0.setIcon(R.mipmap.home);
        MenuItem item1 = navView.getMenu().findItem(R.id.navigation_order);
        item1.setIcon(R.mipmap.dingdan);
        MenuItem item2 = navView.getMenu().findItem(R.id.navigation_invite);
        item2.setIcon(R.mipmap.yaoqing);
        MenuItem item3 = navView.getMenu().findItem(R.id.navigation_mime);
        item3.setIcon(R.mipmap.wode);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViewPager();


        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        navView.setItemIconTintList(null);
        navView.setSelectedItemId(R.id.navigation_home);


    }



    /**
     * 初始化view pager
     */
    private void initViewPager() {

        mViewPager.setAdapter(new VpAdapter(getSupportFragmentManager(), getInitFragment(), navView));
        mViewPager.setOffscreenPageLimit(8);
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
        baseFragments.add(new OrderFragmentHome());//订单
//        baseFragments.add(new RechargeFragment());//充值界面
        baseFragments.add(new InviteFragment());//邀请界面
        baseFragments.add(new PersonalFragment());//个人中心
        return baseFragments;
    }

}
