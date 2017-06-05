package eo.cn.yxwuliu.model;

import eo.cn.yxwuliu.bean.GoodsBean;

/**
 * @author jk
 * @time 2017/6/1  10:23
 * @desc ${TODD}
 */
public interface IDetailDeliverGoodsModel {
    void SetOnGetGoodsListener(OnGetGoodsListener onGetGoodsListener);

    interface OnGetGoodsListener {
        void getDataSuccess(GoodsBean goodsBean);

        void getDataFail(Object msg);
    }
}
