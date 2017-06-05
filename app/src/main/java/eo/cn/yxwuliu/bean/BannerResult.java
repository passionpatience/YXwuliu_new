package eo.cn.yxwuliu.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/27.
 */
public class BannerResult extends ResultBean<BannerResult> {
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * photo :
         */

        private String photo;

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }

}
