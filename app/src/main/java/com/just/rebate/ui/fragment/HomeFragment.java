package com.just.rebate.ui.fragment;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.just.rebate.R;
import com.just.rebate.entity.HomeItem;
import com.rebate.base.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

import com.just.rebate.adapter.recycle.SectionAdapter;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment {
    private List<HomeItem> mData;

    @BindView(R.id.rv_list)
    RecyclerView recycleView;

    @Override
    protected int bindFragmentLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViewsAndEvents(View view) {
        recycleView.setLayoutManager(new GridLayoutManager(mActivity, 3));
//        recycleView.addItemDecoration(new GridSectionAverageGapItemDecoration(10,10,20,15));
        List<HomeItem> homeItems = new ArrayList<>();
        homeItems.add(new HomeItem(true, "购物返利"));
        homeItems.add(new HomeItem(false, "name2"));
        homeItems.add(new HomeItem(false, "name2"));
        homeItems.add(new HomeItem(false, "name2"));
        homeItems.add(new HomeItem(false, "name2"));
        homeItems.add(new HomeItem(false, "name2"));
        homeItems.add(new HomeItem(false, "name2"));
        homeItems.add(new HomeItem(true, "外卖返利"));
        homeItems.add(new HomeItem(false, "name2"));
        homeItems.add(new HomeItem(false, "name2"));
        homeItems.add(new HomeItem(false, "name2"));
        homeItems.add(new HomeItem(true, "生活返利"));
        homeItems.add(new HomeItem(false, "name2"));
        homeItems.add(new HomeItem(false, "name2"));
        homeItems.add(new HomeItem(false, "name2"));
        homeItems.add(new HomeItem(false, "name2"));
        homeItems.add(new HomeItem(false, "name2"));
        homeItems.add(new HomeItem(false, "name2"));
        mData = homeItems;







        SectionAdapter sectionAdapter = new SectionAdapter(R.layout.item_section_content, R.layout.def_section_head, mData) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, HomeItem item) {

            }

            @Override
            protected void convertHead(BaseViewHolder helper, HomeItem item) {
                super.convertHead(helper, item);

//                View view1=layoutInflater.inflate(R.layout.def_section_head,null);
                helper.setText(R.id.header, "购物返利");
            }
        };


        sectionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomeItem mySection = mData.get(position);
                if (mySection.isHeader)
                    Toast.makeText(mActivity, mySection.header, Toast.LENGTH_LONG).show();
            }
        });
        /*sectionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(mActivity, "onItemChildClick", Toast.LENGTH_SHORT).show();
            }
        });*/
        recycleView.setAdapter(sectionAdapter);


        requestDataOnline();

    }

    @Override
    protected void initData() {

    }


    /**
     * 网络请求数据
     */
    public void requestDataOnline() {

        OkHttpUtils
                .get()
                .url("https://www.luocaca.cn/")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {


                        //Toast.makeText(mActivity, "error", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //Toast.makeText(mActivity, "succeed" + response, Toast.LENGTH_SHORT).show();

                    }
                });


    }


}
