package com.just.rebate.adapter.recycle;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.just.rebate.R;
import com.just.rebate.entity.HomeItem;
import com.just.rebate.entity.PlatformsBean;
import com.just.rebate.ui.activity.web.WebViewActivity;

import java.util.List;

/**
 * Created by luoxw on 2016/8/9.
 */
public class HomeExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    private static final String TAG = HomeExpandableItemAdapter.class.getSimpleName();

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;
    private String URL="";
    private Context mContext;


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeExpandableItemAdapter(List<MultiItemEntity> data,Context context) {
        super(data);
        //R.layout.item_section_content, R.layout.def_section_head,
        this.mContext=context;
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
            Glide.with(mContext).load(((PlatformsBean) item).Logo).apply(RequestOptions.bitmapTransform(new CircleCrop())).into((ImageView) helper.getView(R.id.iv));
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(mContext, WebViewActivity.class);
                    intent.putExtra("url",((PlatformsBean) item).Url);
                    mContext.startActivity(intent);
                }
            });
        }


    }


}
