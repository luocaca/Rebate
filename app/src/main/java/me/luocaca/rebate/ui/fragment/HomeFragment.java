package me.luocaca.rebate.ui.fragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.SectionEntity;
import com.rebate.base.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.luocaca.rebate.R;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment {


    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    @Override
    protected int bindFragmentLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViewsAndEvents(View view) {

        List list = new ArrayList<>();



        recycleView.setAdapter(new BaseSectionQuickAdapter(R.layout.item_head, R.layout.item_icon_text, list) {
            @Override
            protected void convert(@NonNull BaseViewHolder helper, Object item) {

            }

            @Override
            protected void convertHead(BaseViewHolder helper, SectionEntity item) {

            }
        });
//        recycleView.addItemDecoration(new GridSectionAverageGapItemDecoration(10,10,20,15));
        recycleView.setLayoutManager(new GridLayoutManager(mActivity, 3));


    }
}
