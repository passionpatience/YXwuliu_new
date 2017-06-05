package eo.cn.yxwuliu.model;

import eo.cn.yxwuliu.bean.CarsBean;
import eo.cn.yxwuliu.bean.GoodsBean;

/**
 * 这个是发布车源的详情界面的接口
 * Created by Administrator on 2017/6/3.
 */

public interface ICarDetailModel {
    /**
     * 根据id来获取更多数据
     * @param id
     * @param mOnGetCarDetailData
     */
    void setOnGetCarDetailData(String id,OnGetCarDetailData mOnGetCarDetailData);

    /**
     * 获取到数据后的操作
     */
    interface OnGetCarDetailData{
//        void getDataSuccess(CarsBean carsBean);//获取数据后，需要把数据作为参数存入，以被使用
        void getDataSuccess(GoodsBean goodsBean);//暂时使用goodbean来测试
        void getDataFail(Object smg);
    }
}
