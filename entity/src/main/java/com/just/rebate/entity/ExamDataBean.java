package com.just.rebate.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

import java.util.List;

public class ExamDataBean extends SectionEntity<ExamDataBean> {

    private int Status;
    private String Ver;
    private String ErrMsg;
    private List<ExamDataBean> Data;

    public ExamDataBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getVer() {
        return Ver;
    }

    public void setVer(String Ver) {
        this.Ver = Ver;
    }

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String ErrMsg) {
        this.ErrMsg = ErrMsg;
    }

    public List<ExamDataBean> getData() {
        return Data;
    }

    public void setData(ExamDataBean Data) {
        this.Data = Data.Data;
    }


}
