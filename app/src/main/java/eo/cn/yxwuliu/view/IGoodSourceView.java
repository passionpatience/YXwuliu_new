package eo.cn.yxwuliu.view;

import eo.cn.yxwuliu.bean.GoodsBean;

/**
 * 这个是货源界面信息
 * Created by kebi on 2017/5/30.
 */

public interface IGoodSourceView {

    /**
     * 这是种类
     * @return
     */
    String getType();

    /**
     * 这个查询方式的,根据目的地查询
     * @return
     */
    String getAddress();

    /**
     * 进行网络请求数据成功的处理
     */
    void showGoodSource(GoodsBean goodsBean);

    void showDataFail(Object smg);

    void showMoreData(GoodsBean goodsBean);

    void showResettingData(GoodsBean goodsBean);

    /**
     * 进度条显示
     */
    void showLoading();

    /**
     * 进度条关闭
     */
    void hideLoading();
}
