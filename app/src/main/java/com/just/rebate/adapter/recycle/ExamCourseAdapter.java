package com.just.rebate.adapter.recycle;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.just.rebate.entity.ExamDataBean;

import java.util.List;

public class ExamCourseAdapter extends BaseSectionQuickAdapter<ExamDataBean, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public ExamCourseAdapter(int layoutResId, int sectionHeadResId, List<ExamDataBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, ExamDataBean item) {

    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ExamDataBean item) {

    }
}
