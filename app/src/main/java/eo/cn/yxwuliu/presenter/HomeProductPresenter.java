package eo.cn.yxwuliu.presenter;

import android.util.Log;

import eo.cn.yxwuliu.base.BasePresenter;
import eo.cn.yxwuliu.bean.BannerResult;
import eo.cn.yxwuliu.bean.GoodsBean;
import eo.cn.yxwuliu.model.HomeFragmentModelImpl;
import eo.cn.yxwuliu.model.IHomeFragmentModel;
import eo.cn.yxwuliu.view.IHomeFragmentView;

/**
 * Created by Administrator on 2017/5/27.
 * 首页的p层
 */
public class HomeProductPresenter extends BasePresenter<IHomeFragmentView> {

    private IHomeFragmentView mIHomeFragmentView;
    private IHomeFragmentModel iHomeFragmentModel;

    public HomeProductPresenter(IHomeFragmentView homeFragment) {
        this.mIHomeFragmentView = homeFragment;
        iHomeFragmentModel = new HomeFragmentModelImpl(false);
    }

    //获取到头布局数据传递给V层
    public void getBannerData() {
        //如果m层的数据不为空
        if (iHomeFragmentModel != null) {
            iHomeFragmentModel.setOnGetBannerListener(new IHomeFragmentModel.OnGetBannerListener() {

                @Override
                public void getDataSuccess(BannerResult bannerResult) {
                    mIHomeFragmentView.showBanner(bannerResult);
                }

                @Override
                public void getDataFail(Object msg) {
                    mIHomeFragmentView.getDataFail(msg);
                }
            });
        }
    }

    //获取列表的数据
    public void getListData() {
        if (iHomeFragmentModel != null) {
            iHomeFragmentModel.setOnGetListListener(new IHomeFragmentModel.OnGetListListener() {

                @Override
                public void getDataSuccess(GoodsBean goodsBean) {
                    mIHomeFragmentView.showListData(goodsBean);
                }

                @Override
                public void getDataFail(Object smg) {
                    mIHomeFragmentView.getDataFail(smg);
                }
            });
        }
    }

    //刷新 下拉
    public void getRefreshProduct() {
        if (iHomeFragmentModel != null) {
            iHomeFragmentModel.setOnGetListListener(new IHomeFragmentModel.OnGetRefreshingProductListener() {

                @Override
                public void getRefreshDataSuccess(GoodsBean goodsBean) {
                    mIHomeFragmentView.showRefreshData(goodsBean);
                }

                @Override
                public void getDataFail(Object smg) {
                    mIHomeFragmentView.getDataFail(smg);
                }
            });
        }
    }
    //更多 上拉加载
    public void getMoreProductData() {
        if (iHomeFragmentModel != null) {
            iHomeFragmentModel.setOnGetListListener(new IHomeFragmentModel.OnGetMoreProductListener() {

                @Override
                public void getMoreDataSuccess(GoodsBean goodsBean) {
                    Log.v("TAG","getMoreDataSuccess"+"222222222222222222");
                    mIHomeFragmentView.showMoreData(goodsBean);
                }

                @Override
                public void getDataFail(Object smg) {
                    mIHomeFragmentView.getDataFail(smg);
                }
            });
        }
    }
}
