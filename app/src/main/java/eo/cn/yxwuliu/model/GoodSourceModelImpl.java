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

public class GoodSourceModelImpl extends BaseModel implements IGoodSourceModel {

    private int number = 2;

    private List<GoodsBean.DataBean> data = new ArrayList<GoodsBean.DataBean>();

    public GoodSourceModelImpl(boolean isCache) {
        super(isCache);
    }

    @Override
    public void setOnGetGoodSourceData(String type, final OnGetGoodSourceData onGetGetGoodSourceData) {
    /*Retrofit retrofit=new Retrofit
                .Builder()
                .baseUrl("http://192.168.1.10")
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                //增加返回值为Oservable<T>的支持
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        IHttpInterface mIHttpInterface = retrofit.create(IHttpInterface.class);
        Call<String> coldCall = mIHttpInterface.test();
        coldCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("测试",response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("测试",t.getMessage());

            }
        });*/
        Observable<GoodsBean> getGoodSource = iHttpInterface.getGoodSource("2","1");
        getGoodSource.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<GoodsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        Log.v("错误",e.getMessage());
                        onGetGetGoodSourceData.getDataFail(error+"第一次");
                    }

                    @Override
                    public void onNext(GoodsBean goodsBean) {
                        data.addAll(goodsBean.getData());
                        if (goodsBean.getData() != null) {
                        /*for (ProductCold.ResultBean bean : productCold.getResult()){
                            Log.e("分类查询",bean.getId()+"==="+number);
                        }*/
                            onGetGetGoodSourceData.getGoodSourceSuccess(goodsBean);
                        } else {
                            //返回错误
                            onGetGetGoodSourceData.getDataFail(goodsBean.getInfo()+"服务器");
                        }
                    }
                });
    }

    @Override
    public void setOnGetGoodSourceData(String type, final OnMoreGetGoodSourceData onMoreGetGoodSourceData) {
        Observable<GoodsBean> getGoodSource = iHttpInterface.getGoodSource("2",number+"");
        getGoodSource.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<GoodsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        onMoreGetGoodSourceData.getDataFail(error);
                    }

                    @Override
                    public void onNext(GoodsBean goodsBean) {
                        number++;
                        data.addAll(goodsBean.getData());
                        goodsBean.setData(data);
                        onMoreGetGoodSourceData.getMoreDataSuccess(goodsBean);
                    }
                });
    }

    @Override
    public void setOnGetProductListener(String type, final OnGetRefreshingProductListener onGetRefreshingProductListener) {
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
                        onGetRefreshingProductListener.getDataFail(error);
                    }

                    @Override
                    public void onNext(GoodsBean goodsBean) {
                        onGetRefreshingProductListener.getRefreshingDataSuccess(goodsBean);
                    }
                });
    }
}
