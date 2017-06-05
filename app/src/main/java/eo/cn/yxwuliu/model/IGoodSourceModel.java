package eo.cn.yxwuliu.model;

import eo.cn.yxwuliu.bean.GoodsBean;

/**
 * 这个是根据货源的种类来进行获取数据的接口
 * Created by kebi on 2017/5/30.
 */

public interface IGoodSourceModel {
    /**
     * 根据货源类型来查询
     * @param type：货源的类型
     * @param onGetGetGoodSourceData：获取到数据后的回调
     */

    void setOnGetGoodSourceData(String type,OnGetGoodSourceData onGetGetGoodSourceData);

    void setOnGetGoodSourceData(String type,OnMoreGetGoodSourceData onMoreGetGoodSourceData);

    /**
     * 实现刷新
     * @param onGetRefreshingProductListener
     */
    void setOnGetProductListener(String type,OnGetRefreshingProductListener onGetRefreshingProductListener);

    interface OnGetGoodSourceData{
        void getGoodSourceSuccess(GoodsBean goodsBean);

        void getDataFail(Object smg);
    }

    /**
     * 这个是下拉加载更多的
     */
    interface OnMoreGetGoodSourceData{

        void getMoreDataSuccess(GoodsBean goodsBean);

        void getDataFail(Object smg);
    }
    /**
     * 这个是下拉刷新更多的
     */
    interface OnGetRefreshingProductListener{

        void getRefreshingDataSuccess(GoodsBean goodsBean);

        void getDataFail(Object smg);
    }
}
