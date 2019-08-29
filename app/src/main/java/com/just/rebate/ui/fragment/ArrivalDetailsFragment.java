package com.just.rebate.ui.fragment;


import android.graphics.Rect;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.just.rebate.R;
import com.rebate.base.fragment.BaseFragment;

import java.util.List;

import butterknife.BindView;

import com.just.rebate.adapter.recycle.ArrivalDetailsAdapter;

/**
 * 账单
 */
public class ArrivalDetailsFragment extends BaseFragment {

    @BindView(R.id.rv_list3)
    RecyclerView mrecyclerView;
    private List<String> strings;


    @Override
    protected int bindFragmentLayoutId() {
        return R.layout.fragment_arrival_details;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        ArrivalDetailsAdapter mArrivalDetailsAdapter = new ArrivalDetailsAdapter(mActivity, strings);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mrecyclerView.addItemDecoration(new MyDecoration());
        mrecyclerView.setAdapter(mArrivalDetailsAdapter);
    }

    class MyDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0, 0, 0, getResources().getDimensionPixelOffset(R.dimen.dibufengex));

        }
    }

}