package me.luocaca.rebate.ui.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import com.rebate.base.fragment.BaseFragment;

import butterknife.BindView;
import me.luocaca.rebate.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RechargeFragment extends BaseFragment {

    @BindView(R.id.recharge_toolbar)
    Toolbar toolbar;

    @Override
    protected int bindFragmentLayoutId() {
        return R.layout.fragment_recharge;
    }

    @Override
    protected void initViewsAndEvents(View view) {

    }

}
