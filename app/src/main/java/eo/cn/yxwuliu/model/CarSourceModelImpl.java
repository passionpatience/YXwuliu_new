package eo.cn.yxwuliu.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import eo.cn.yxwuliu.base.BaseModel;
import eo.cn.yxwuliu.bean.GoodsBean;
import eo.cn.yxwuliu.http.FilterSubscriber;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 这是执行具体的获取货源数据的操作
 * Created by kebi on 2017/5/30.
 */

public class CarSourceModelImpl extends BaseModel implements ICarSourceModel {

    private int number = 2;

    private List<GoodsBean.DataBean> data = new ArrayList<GoodsBean.DataBean>();

    public CarSourceModelImpl(boolean isCache) {
        super(isCache);
    }

    @Override
    public void setOnGetCarSourceData(String type, final OnGetCarSourceData onGetCarSourceData) {
        Observable<GoodsBean> getCarSource = iHttpInterface.getCarSource("2", "1");
        getCarSource.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<GoodsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        onGetCarSourceData.getDataFail(error);
                    }

                    @Override
                    public void onNext(GoodsBean goodsBean) {
//                        data.addAll(goodsBean.getData());
                        if (goodsBean.getData() != null) {
                        /*for (ProductCold.ResultBean bean : productCold.getResult()){
                            Log.e("分类查询",bean.getId()+"==="+number);
                        }*/
                            onGetCarSourceData.getCarSourceSuccess(goodsBean);
                        } else {
                            //返回错误
                            onGetCarSourceData.getDataFail(goodsBean.getInfo()+"服务器");
                        }
                    }
                });
    }

    @Override
    public void setOnGetCarSourceData(String type, final OnMoreGetCarSourceData onMoreGetCarSourceData) {
        Observable<GoodsBean> getCarSource = iHttpInterface.getCarSource("2", number + "");
        getCarSource.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<GoodsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        onMoreGetCarSourceData.getDataFail(error);
                    }

                    @Override
                    public void onNext(GoodsBean goodsBean) {
                        number++;
                        data.addAll(goodsBean.getData());
                        goodsBean.setData(data);
                        onMoreGetCarSourceData.getMoreDataSuccess(goodsBean);
                    }
                });
    }

    @Override
    public void setOnGetCarListener(String type, final OnGetRefreshingCarListener onGetRefreshingCarListener) {
        Observable<GoodsBean> getGoodSource = iHttpInterface.getGoodSource("4","1");
        getGoodSource.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<GoodsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        onGetRefreshingCarListener.getDataFail(error);
                    }

                    @Override
                    public void onNext(GoodsBean goodsBean) {
                        onGetRefreshingCarListener.getRefreshingDataSuccess(goodsBean);
                    }
                });
    }
}
