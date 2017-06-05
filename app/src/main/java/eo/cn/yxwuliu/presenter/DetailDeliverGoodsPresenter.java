package eo.cn.yxwuliu.presenter;

import eo.cn.yxwuliu.base.BasePresenter;
import eo.cn.yxwuliu.model.DetailDeliverGoodsModelImpl;
import eo.cn.yxwuliu.model.IDetailDeliverGoodsModel;
import eo.cn.yxwuliu.view.IDetailDeliverGoodsView;

/**
 * @author jk
 * @time 2017/5/31  17:30
 * @desc 发布货源的p层
 */
public class DetailDeliverGoodsPresenter extends BasePresenter<IDetailDeliverGoodsView> {
    private IDetailDeliverGoodsModel mDetailDeliverGoodsModel;
    private IDetailDeliverGoodsView mIDetailDeliverGoodsView;

    public DetailDeliverGoodsPresenter(IDetailDeliverGoodsView IDetailDeliverGoodsView) {
        mDetailDeliverGoodsModel = new DetailDeliverGoodsModelImpl(false);
        mIDetailDeliverGoodsView = IDetailDeliverGoodsView;
    }
}
