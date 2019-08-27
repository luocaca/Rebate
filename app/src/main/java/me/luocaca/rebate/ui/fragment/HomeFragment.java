package me.luocaca.rebate.ui.fragment;

import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.just.rebate.entity.HomeItem;
import com.just.rebate.entity.HomeMultipleItem;
import com.rebate.base.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import me.luocaca.rebate.R;
import me.luocaca.rebate.adapter.recycle.GridItemDecoration;
import me.luocaca.rebate.adapter.recycle.GridSectionAverageGapItemDecoration;
import me.luocaca.rebate.adapter.recycle.SectionAdapter;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment {
    private List<HomeItem> mData;
    private List<HomeMultipleItem> mDatas;

    @BindView(R.id.rv_list)
    RecyclerView recycleView;

    @Override
    protected int bindFragmentLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
        protected void initViewsAndEvents(View view) {
        recycleView.setLayoutManager(new GridLayoutManager(mActivity,3));
//        recycleView.addItemDecoration(new GridSectionAverageGapItemDecoration(10,10,20,15));
        List<HomeItem>homeItems=  new ArrayList<>();
        homeItems.add(new HomeItem(true,"购物返利"));
        homeItems.add(new HomeItem(false,"name2"));
        homeItems.add(new HomeItem(false,"name2"));
        homeItems.add(new HomeItem(false,"name2"));
        homeItems.add(new HomeItem(false,"name2"));
        homeItems.add(new HomeItem(false,"name2"));
        homeItems.add(new HomeItem(false,"name2"));
        homeItems.add(new HomeItem(true,"外卖返利"));
        homeItems.add(new HomeItem(false,"name2"));
        homeItems.add(new HomeItem(false,"name2"));
        homeItems.add(new HomeItem(false,"name2"));
        homeItems.add(new HomeItem(true,"生活返利"));
        homeItems.add(new HomeItem(false,"name2"));
        homeItems.add(new HomeItem(false,"name2"));
        homeItems.add(new HomeItem(false,"name2"));
        homeItems.add(new HomeItem(false,"name2"));
        homeItems.add(new HomeItem(false,"name2"));
        homeItems.add(new HomeItem(false,"name2"));
        mData=homeItems;
        
     



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



        /*class MyDecoration extends RecyclerView.ItemDecoration{
            public void getItemoffsets(Rect rect,View view1,RecyclerView recyclerView,RecyclerView.State state){
                super.getItemOffsets(rect,view1,recyclerView,state);
                rect.set(0,0,1,getResources().getDimensionPixelOffset(R.dimen.dibufengex));
            }
        }*/

        sectionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomeItem mySection = mData.get(position);
                if (mySection.isHeader)
                    Toast.makeText(mActivity, mySection.header, Toast.LENGTH_LONG).show();
            }
        });
        sectionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(mActivity, "onItemChildClick", Toast.LENGTH_SHORT).show();
            }
        });
        recycleView.setAdapter(sectionAdapter);
    }

    private static List<HomeMultipleItem> getSectionMultiData() {
        List<HomeMultipleItem>homeMultipleItems=  new ArrayList<>();
        homeMultipleItems.add(new HomeMultipleItem(true,"购物返利",true));
        homeMultipleItems.add(new HomeMultipleItem(true, "外卖返利", true));
        homeMultipleItems.add(new HomeMultipleItem(true, "生活返利", false));
        return homeMultipleItems;

    }


}
