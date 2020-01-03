package com.just.rebate.adapter.recycle;


import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.IExpandable;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.just.rebate.R;
import com.just.rebate.entity.HomeItem;
import com.just.rebate.entity.PlatformsBean;

import java.util.List;

/**
 * Created by luoxw on 2016/8/9.
 */
public class HomeExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    private static final String TAG = HomeExpandableItemAdapter.class.getSimpleName();

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeExpandableItemAdapter(List<MultiItemEntity> data) {
        super(data);
        //R.layout.item_section_content, R.layout.def_section_head,
        addItemType(TYPE_LEVEL_0, R.layout.def_section_head);
        addItemType(TYPE_LEVEL_1, R.layout.item_section_content);
    }


    @Override
    protected void convert(@NonNull BaseViewHolder helper, MultiItemEntity item) {

        System.out.println("is head ? " + item.getItemType());
        if (item.getItemType() == 0) {
            HomeItem item1 = (HomeItem) item;
            helper.setText(R.id.header, item1.Name);
        } else {
            PlatformsBean item1 = (PlatformsBean) item;
            helper.setText(R.id.tv, item1.Name);
            Glide.with(mContext).load(item1.Logo).into((ImageView) helper.getView(R.id.iv));
        }


    }


}
