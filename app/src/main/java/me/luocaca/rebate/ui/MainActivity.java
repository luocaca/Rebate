package me.luocaca.rebate.ui;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.rebate.base.fragment.BaseFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.luocaca.rebate.R;
import me.luocaca.rebate.adapter.viewpager.VpAdapter;
import me.luocaca.rebate.ui.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.view_pager)
    public ViewPager mViewPager;

    @BindView(R.id.nav_view)
    public BottomNavigationView navView;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            Snackbar.make(mViewPager, "hello world", Snackbar.LENGTH_LONG).show();
            mViewPager.setBackgroundColor(getResources().getColor(R.color.design_default_color_primary));
            return true;
        }
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

        mViewPager.setAdapter(new VpAdapter(getSupportFragmentManager(), getInitFragment()));

    }

    /**
     * 获取首页 需要初始化的  fragment
     *
     * @return
     */
    private List<BaseFragment> getInitFragment() {
        // new HomeFragment();
        List<BaseFragment> baseFragments = new ArrayList<>();
        baseFragments.add(new HomeFragment());
        baseFragments.add(new HomeFragment());
        baseFragments.add(new HomeFragment());
        return baseFragments;
    }

}
