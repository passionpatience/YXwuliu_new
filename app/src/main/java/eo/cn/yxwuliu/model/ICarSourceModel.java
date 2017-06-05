package eo.cn.yxwuliu.model;

import eo.cn.yxwuliu.bean.GoodsBean;

/**
 * 这个是根据车源的种类来进行获取数据的接口
 * Created by kebi on 2017/5/30.
 */

public interface ICarSourceModel {

    /**
     * 根据车源类型来查询
     * @param type：车源的类型
     * @param onGetCarSourceData：获取到数据后的回调
     */

    void setOnGetCarSourceData(String type,OnGetCarSourceData onGetCarSourceData);

    void setOnGetCarSourceData(String type,OnMoreGetCarSourceData onMoreGetCarSourceData);

    /**
     * 实现刷新
     * @param onGetRefreshingCarListener
     */
    void setOnGetCarListener(String type,OnGetRefreshingCarListener onGetRefreshingCarListener);

    interface OnGetCarSourceData{
        void getCarSourceSuccess(GoodsBean goodsBean);

        void getDataFail(Object smg);
    }

    /**
     * 这个是上拉加载更多的
     */
    interface OnMoreGetCarSourceData{

        void getMoreDataSuccess(GoodsBean goodsBean);

        void getDataFail(Object smg);
    }
    /**
     * 这个是下拉刷新更多的
     */
    interface OnGetRefreshingCarListener{

        void getRefreshingDataSuccess(GoodsBean goodsBean);

        void getDataFail(Object smg);
    }

}
