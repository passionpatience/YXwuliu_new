package eo.cn.yxwuliu.presenter;


import eo.cn.yxwuliu.base.BasePresenter;
import eo.cn.yxwuliu.bean.GoodsBean;
import eo.cn.yxwuliu.model.CarSourceModelImpl;
import eo.cn.yxwuliu.model.GoodSourceModelImpl;
import eo.cn.yxwuliu.model.ICarSourceModel;
import eo.cn.yxwuliu.model.IGoodSourceModel;
import eo.cn.yxwuliu.view.ICarSourceView;
import eo.cn.yxwuliu.view.IGoodSourceView;

/**
 * Created by kebi on 2017/5/30.
 */

public class CarSourcePresenter extends BasePresenter<ICarSourceView>{

    private ICarSourceView mICarSourceView;
    private ICarSourceModel mICarSourceModel = new CarSourceModelImpl(false);

    public CarSourcePresenter(ICarSourceView mICarSourceView) {
       this.mICarSourceView = mICarSourceView;
    }

    //第一次获取数据显示
    public void getCarSourceData(){
        if (mICarSourceModel != null) {
            mICarSourceView.showLoading();
            mICarSourceModel.setOnGetCarSourceData(mICarSourceView.getType()
                    , new ICarSourceModel.OnGetCarSourceData() {
                        @Override
                        public void getCarSourceSuccess(GoodsBean goodsBean) {
                            mICarSourceView.showCarSource(goodsBean);
                            mICarSourceView.hideLoading();
                        }

                        @Override
                        public void getDataFail(Object smg) {
                            mICarSourceView.showDataFail(smg);
                            mICarSourceView.hideLoading();
                        }
                    });
        }
    }

    //上拉加载更多
    public void getMoreDataCarSource(){
        if (mICarSourceModel != null) {
            mICarSourceView.showLoading();
            mICarSourceModel.setOnGetCarSourceData(mICarSourceView.getType()
                    , new ICarSourceModel.OnMoreGetCarSourceData() {
                        @Override
                        public void getMoreDataSuccess(GoodsBean goodsBean) {
                            mICarSourceView.showMoreData(goodsBean);
                            mICarSourceView.hideLoading();
                        }

                        @Override
                        public void getDataFail(Object smg) {
                            mICarSourceView.showDataFail(smg);
                            mICarSourceView.hideLoading();
                        }
                    });
        }
    }

    //下拉刷新
    public void getRefreshCar(){
        if (mICarSourceModel != null) {
            mICarSourceModel.setOnGetCarListener(mICarSourceView.getType()
                    , new ICarSourceModel.OnGetRefreshingCarListener() {
                        @Override
                        public void getRefreshingDataSuccess(GoodsBean goodsBean) {
                            mICarSourceView.showResettingData(goodsBean);
                        }

                        @Override
                        public void getDataFail(Object smg) {
                            mICarSourceView.showDataFail(smg);
                        }
                    });
        }
    }
}
