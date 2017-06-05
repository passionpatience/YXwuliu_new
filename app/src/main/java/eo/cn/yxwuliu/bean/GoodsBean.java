package eo.cn.yxwuliu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 * 货源实体类
 */
public class GoodsBean extends ResultBean implements Serializable {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * huozhu_name : 赵云
         * yuan_dizhi : 广州,天河
         * fahuo_dizhi : 广州,番禺
         * huo_kg : 130
         * created : 1495245619
         * che_class : 平板车
         */

        private String huozhu_name;
        private String yuan_dizhi;
        private String fahuo_dizhi;
        private int huo_kg;
        private int created;
        private String che_class;

        public String getHuozhu_name() {
            return huozhu_name;
        }

        public void setHuozhu_name(String huozhu_name) {
            this.huozhu_name = huozhu_name;
        }

        public String getYuan_dizhi() {
            return yuan_dizhi;
        }

        public void setYuan_dizhi(String yuan_dizhi) {
            this.yuan_dizhi = yuan_dizhi;
        }

        public String getFahuo_dizhi() {
            return fahuo_dizhi;
        }

        public void setFahuo_dizhi(String fahuo_dizhi) {
            this.fahuo_dizhi = fahuo_dizhi;
        }

        public int getHuo_kg() {
            return huo_kg;
        }

        public void setHuo_kg(int huo_kg) {
            this.huo_kg = huo_kg;
        }

        public int getCreated() {
            return created;
        }

        public void setCreated(int created) {
            this.created = created;
        }

        public String getChe_class() {
            return che_class;
        }

        public void setChe_class(String che_class) {
            this.che_class = che_class;
        }
    }
}
