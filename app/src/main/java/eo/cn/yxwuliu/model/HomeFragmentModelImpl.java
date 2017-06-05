package eo.cn.yxwuliu.model;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import eo.cn.yxwuliu.base.BaseModel;
import eo.cn.yxwuliu.bean.BannerResult;
import eo.cn.yxwuliu.bean.GoodsBean;
import eo.cn.yxwuliu.http.FilterSubscriber;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/27.
 * m层的实现类   实现层
 */
public class HomeFragmentModelImpl extends BaseModel implements IHomeFragmentModel {
    private List<BannerResult.DataBean> mResultBean = new ArrayList<>();
    private int page = 2;

    public HomeFragmentModelImpl(boolean isCache) {
        super(isCache);
    }


    @Override
    public void setOnGetBannerListener(final OnGetBannerListener onGetBannerListener) {
        Observable<BannerResult> observable = iHttpInterface.getBannerView();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<BannerResult>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        Log.e("错误", e.getMessage());
                        onGetBannerListener.getDataFail(error + "第一次");
                    }

                    @Override
                    public void onNext(BannerResult bannerResult) {
                        if (bannerResult.getData() != null) {
                            mResultBean.addAll(bannerResult.getData());
                            onGetBannerListener.getDataSuccess(bannerResult);
                        } else {
                            //返回错误
                            onGetBannerListener.getDataFail(bannerResult.getInfo() + "服务器");
                        }
                    }
                });
    }

    @Override
    public void setOnGetListListener(final OnGetListListener onGetListListener) {
        Observable<GoodsBean> observable = iHttpInterface.getListView("2", "1");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<GoodsBean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        Log.e("错误", e + "");
                        onGetListListener.getDataFail(error + "第一次");
                    }

                    @Override
                    public void onNext(GoodsBean goodsBean) {
                        if (goodsBean.getData() != null) {
                            onGetListListener.getDataSuccess(goodsBean);
                        } else {
                            //返回错误
                            onGetListListener.getDataFail(goodsBean.getInfo() + "服务器");
                        }
                    }
                });

    }

    //加载更多的监听
    @Override
    public void setOnGetListListener(final OnGetMoreProductListener onGetMoreProductListener) {
        Observable<GoodsBean> observable = iHttpInterface.getMoreView("2", page + "");
        Log.v("TAG", "observable" + "11111111111111111");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<GoodsBean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        Log.e("错误", e.getMessage() + "");
                        onGetMoreProductListener.getDataFail(error + "第一次");
                    }

                    @Override
                    public void onNext(GoodsBean goodsBean) {
                        if (goodsBean.getData() != null) {
                            page++;
                            onGetMoreProductListener.getMoreDataSuccess(goodsBean);
                        } else {
                            //返回错误
                            onGetMoreProductListener.getDataFail(goodsBean.getInfo() + "服务器");
                        }
                    }
                });

    }

    //下拉刷新的监听
    @Override
    public void setOnGetListListener(OnGetRefreshingProductListener onGetRefreshingProductListener) {

    }

}
