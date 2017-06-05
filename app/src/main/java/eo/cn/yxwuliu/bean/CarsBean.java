package eo.cn.yxwuliu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 车源实体类
 * Created by Administrator on 2017/5/10.
 */
public class CarsBean extends ResultBean implements Serializable{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean{

    private String headimgUrls; //头像
    private String identity; //身份
    private String supperName;  //姓名
    private String upTime;//发布时间
    private String ordersNum;//接单数
    private String beginToWhere;//出发地
    private String arriveToWhere;//目的地
    private Long distance;//里程
    private String carType;//车辆类型
    private String startTime;//发车时间

    public String getHeadimgUrls() {
        return headimgUrls;
    }

    public void setHeadimgUrls(String headimgUrls) {
        this.headimgUrls = headimgUrls;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getSupperName() {
        return supperName;
    }

    public void setSupperName(String supperName) {
        this.supperName = supperName;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    public String getOrdersNum() {
        return ordersNum;
    }

    public void setOrdersNum(String ordersNum) {
        this.ordersNum = ordersNum;
    }

    public String getBeginToWhere() {
        return beginToWhere;
    }

    public void setBeginToWhere(String beginToWhere) {
        this.beginToWhere = beginToWhere;
    }

    public String getArriveToWhere() {
        return arriveToWhere;
    }

    public void setArriveToWhere(String arriveToWhere) {
        this.arriveToWhere = arriveToWhere;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "CarsBean{" +
                "headimgUrls='" + headimgUrls + '\'' +
                ", identity='" + identity + '\'' +
                ", supperName='" + supperName + '\'' +
                ", upTime='" + upTime + '\'' +
                ", ordersNum='" + ordersNum + '\'' +
                ", beginToWhere='" + beginToWhere + '\'' +
                ", arriveToWhere='" + arriveToWhere + '\'' +
                ", distance=" + distance +
                ", carType='" + carType + '\'' +
                ", startTime='" + startTime + '\'' +
                '}';
    }
    }
}
