package eo.cn.yxwuliu.model;

import eo.cn.yxwuliu.bean.BannerResult;
import eo.cn.yxwuliu.bean.GoodsBean;

/**
 * Created by Administrator on 2017/5/27.
 * 首页fragment  model层 接口
 */
public interface IHomeFragmentModel {

    void setOnGetBannerListener(OnGetBannerListener onGetBannerListener);

    /**
     * 这个是第一次获取的数据
     *
     * @param
     */
    void setOnGetListListener(OnGetListListener onGetBannerListener);

    /**
     * 实现获取更多，这个为了性能问题,将使用分页查询
     *
     * @param onGetMoreProductListener
     */
    void setOnGetListListener(OnGetMoreProductListener onGetMoreProductListener);

    /**
     * 实现刷新
     *
     * @param onGetRefreshingProductListener
     */
    void setOnGetListListener(OnGetRefreshingProductListener onGetRefreshingProductListener);

    interface OnGetBannerListener {
        void getDataSuccess(BannerResult bannerResult);
        void getDataFail(Object msg);
    }

    interface OnGetListListener {
        void getDataSuccess(GoodsBean goodsBean);
        void getDataFail(Object msg);
    }

    interface OnGetMoreProductListener{
        void getMoreDataSuccess(GoodsBean goodsBean);
        void getDataFail(Object smg);
    }
    interface OnGetRefreshingProductListener{
        void getRefreshDataSuccess(GoodsBean goodsBean);
        void getDataFail(Object smg);
    }


}
