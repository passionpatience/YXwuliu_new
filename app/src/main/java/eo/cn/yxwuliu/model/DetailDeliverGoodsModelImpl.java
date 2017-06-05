package eo.cn.yxwuliu.model;

import eo.cn.yxwuliu.base.BaseModel;

/**
 * @author jk
 * @time 2017/6/1  10:25
 * @desc ${TODD}
 */
public class DetailDeliverGoodsModelImpl extends BaseModel implements IDetailDeliverGoodsModel {

    public DetailDeliverGoodsModelImpl(boolean isCache) {
        super(isCache);
    }


    @Override
    public void SetOnGetGoodsListener(OnGetGoodsListener onGetGoodsListener) {

    }
}
