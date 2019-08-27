package me.luocaca.rebate.ui.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.just.rebate.entity.HomeItem;
import com.rebate.base.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.luocaca.rebate.R;
import me.luocaca.rebate.adapter.recycle.PersonalAdapter;
import me.luocaca.rebate.adapter.recycle.SectionAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalFragment extends BaseFragment {
    private List<HomeItem> mData;

    @BindView(R.id.rv_list2)
    RecyclerView recyclerView;

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

        recyclerView.setAdapter( new BaseQuickAdapter<HomeItem, BaseViewHolder>(R.layout.item_section_content, mData) {
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

}
