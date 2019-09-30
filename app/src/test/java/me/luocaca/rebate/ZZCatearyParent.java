package me.luocaca.rebate;

import java.util.List;

public class ZZCatearyParent {

    private List<IconListBean> iconList;
    private List<CateListBean> cateList;

    public List<IconListBean> getIconList() {
        return iconList;
    }

    public void setIconList(List<IconListBean> iconList) {
        this.iconList = iconList;
    }

    public List<CateListBean> getCateList() {
        return cateList;
    }

    public void setCateList(List<CateListBean> cateList) {
        this.cateList = cateList;
    }

    public static class IconListBean {
        /**
         * backgroundUrl : http://img.58cdn.com.cn/zhuanzhuan/bannericon/46197095640974115781@3x.png
         * picUrl : https://pic1.zhuanstatic.com/zhuanzh/n_v26f21cdd51b0f4e1cb5a68522945b364b.png
         * cateId : 113
         * jumpUrl : zhuanzhuan://jump/core/web/jump?url=https%3A%2F%2Fwxzhuanzhuan.58.com%2FMzhuanzhuan%2FZZBook%2F%3FfromChannel%3Dfenlei%26verticalAbTest%3D0%23%2FBook%2FHome&searchFrom=catepageRecommendCate
         * desc : 图书音像
         */

        private String backgroundUrl;
        private String picUrl;
        private String cateId;
        private String jumpUrl;
        private String desc;

        public String getBackgroundUrl() {
            return backgroundUrl;
        }

        public void setBackgroundUrl(String backgroundUrl) {
            this.backgroundUrl = backgroundUrl;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getCateId() {
            return cateId;
        }

        public void setCateId(String cateId) {
            this.cateId = cateId;
        }

        public String getJumpUrl() {
            return jumpUrl;
        }

        public void setJumpUrl(String jumpUrl) {
            this.jumpUrl = jumpUrl;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public static class CateListBean {
        /**
         * cateId : 113
         * desc : 图书音像
         */

        private String cateId;
        private String desc;

        public String getCateId() {
            return cateId;
        }

        public void setCateId(String cateId) {
            this.cateId = cateId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
