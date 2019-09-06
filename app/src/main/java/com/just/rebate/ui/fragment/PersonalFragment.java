package com.just.rebate.ui.fragment;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.just.rebate.R;
import com.just.rebate.entity.HomeItem;
import com.just.rebate.ui.activity.SetUpActivity;
import com.rebate.base.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalFragment extends BaseFragment {
    private List<HomeItem> mData;

    @BindView(R.id.rv_list2)
    RecyclerView recyclerView;

    @BindView(R.id.set_up)
    ImageView imageView;

    @Override
    protected int bindFragmentLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        recyclerView.setLayoutManager(new GridLayoutManager(mActivity, 4));
        List<HomeItem> homeItems = new ArrayList<>();
        homeItems.add(new HomeItem(true, "购物返利"));
        homeItems.add(new HomeItem(false, "name2"));
        homeItems.add(new HomeItem(false, "name2"));
        homeItems.add(new HomeItem(false, "name2"));
        homeItems.add(new HomeItem(false, "name2"));
        homeItems.add(new HomeItem(false, "name2"));
        homeItems.add(new HomeItem(false, "name2"));
        mData = homeItems;

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), SetUpActivity.class);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter( new BaseQuickAdapter<HomeItem, BaseViewHolder>(R.layout.item_personal_content, mData) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, HomeItem item) {

            }
        });




//        personalAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                Toast.makeText(mActivity, "onItemChildClick", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @Override
    protected void initData() {

    }

}
