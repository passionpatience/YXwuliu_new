package eo.cn.yxwuliu.view;

import eo.cn.yxwuliu.bean.BannerResult;
import eo.cn.yxwuliu.bean.GoodsBean;

/**
 * Created by Administrator on 2017/5/27.
 */
public interface IHomeFragmentView {
    void showBanner(BannerResult bannerResult);
    void showListData(GoodsBean goodsBean);
    void showMoreData(GoodsBean goodsBean);
    void showRefreshData(GoodsBean goodsBean);



    void getDataFail(Object msg);

    //    void getShowStoreData(Store store);
    void showLoading();

    void hideLoading();

}
