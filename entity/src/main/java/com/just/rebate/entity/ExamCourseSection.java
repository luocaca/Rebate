package com.just.rebate.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

public class ExamCourseSection extends SectionEntity<ExamDataBean> {

    public ExamCourseSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public ExamCourseSection(ExamDataBean bean) {
        super(bean);
    }


}
