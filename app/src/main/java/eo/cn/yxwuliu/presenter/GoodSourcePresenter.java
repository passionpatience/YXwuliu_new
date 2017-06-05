package eo.cn.yxwuliu.presenter;


import android.util.Log;

import eo.cn.yxwuliu.base.BasePresenter;
import eo.cn.yxwuliu.bean.GoodsBean;
import eo.cn.yxwuliu.fragment.FragmentAllSource;
import eo.cn.yxwuliu.model.GoodSourceModelImpl;
import eo.cn.yxwuliu.model.IGoodSourceModel;
import eo.cn.yxwuliu.view.IGoodSourceView;

/**
 * Created by kebi on 2017/5/30.
 */

public class GoodSourcePresenter extends BasePresenter<IGoodSourceView>{

    private IGoodSourceView mIGoodSourceView;
    private IGoodSourceModel mIGoodSourceModel = new GoodSourceModelImpl(false);

    public GoodSourcePresenter(IGoodSourceView mIGoodSourceView) {
       this.mIGoodSourceView = mIGoodSourceView;
    }

    //第一次获取数据显示
    public void getGoodSourceData(){
        if (mIGoodSourceModel != null) {
            mIGoodSourceView.showLoading();
            mIGoodSourceModel.setOnGetGoodSourceData(mIGoodSourceView.getType()
                    , new IGoodSourceModel.OnGetGoodSourceData() {
                        @Override
                        public void getGoodSourceSuccess(GoodsBean goodsBean) {
                            mIGoodSourceView.showGoodSource(goodsBean);
                            mIGoodSourceView.hideLoading();
                        }

                        @Override
                        public void getDataFail(Object smg) {
                            mIGoodSourceView.showDataFail(smg);
                            mIGoodSourceView.hideLoading();
                        }
                    });
        }
    }

    //上拉加载更多
    public void getMoreDataGoodSource(){
        if (mIGoodSourceModel != null) {
            mIGoodSourceView.showLoading();
            mIGoodSourceModel.setOnGetGoodSourceData(mIGoodSourceView.getType()
                    , new IGoodSourceModel.OnMoreGetGoodSourceData() {
                        @Override
                        public void getMoreDataSuccess(GoodsBean goodsBean) {
                            mIGoodSourceView.showMoreData(goodsBean);
                            mIGoodSourceView.hideLoading();
                        }

                        @Override
                        public void getDataFail(Object smg) {
                            mIGoodSourceView.showDataFail(smg);
                            mIGoodSourceView.hideLoading();
                        }
                    });
        }
    }

    //下拉刷新
    public void getRefreshGood(){
        if (mIGoodSourceModel != null) {
            mIGoodSourceModel.setOnGetProductListener(mIGoodSourceView.getType()
                    , new IGoodSourceModel.OnGetRefreshingProductListener() {
                        @Override
                        public void getRefreshingDataSuccess(GoodsBean goodsBean) {
                            mIGoodSourceView.showResettingData(goodsBean);
                        }

                        @Override
                        public void getDataFail(Object smg) {
                            mIGoodSourceView.showDataFail(smg);
                        }
                    });
        }
    }
}
