package eo.cn.yxwuliu.model;

import java.util.ArrayList;
import java.util.List;

import eo.cn.yxwuliu.base.BaseModel;
import eo.cn.yxwuliu.bean.GoodsBean;
import eo.cn.yxwuliu.http.FilterSubscriber;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/6/3.
 */

public class CarDetailModelImpl extends BaseModel implements ICarDetailModel {

    private List<GoodsBean.DataBean> data = new ArrayList<GoodsBean.DataBean>();

    public CarDetailModelImpl(boolean isCache) {
        super(isCache);
    }

    /**
     * 请求车源的详细信息
     * @param id
     * @param mOnGetCarDetailData
     */
    @Override
    public void setOnGetCarDetailData(String id, final OnGetCarDetailData mOnGetCarDetailData) {
        Observable<GoodsBean> getCarDetail = iHttpInterface.getCarSource("1", "1");
        getCarDetail.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FilterSubscriber<GoodsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mOnGetCarDetailData.getDataFail(error);
                    }

                    @Override
                    public void onNext(GoodsBean goodsBean) {
                        if (goodsBean.getData() != null) {
                            mOnGetCarDetailData.getDataSuccess(goodsBean);
                        } else {
                            mOnGetCarDetailData.getDataFail(goodsBean.getInfo()+"服务器");
                        }
                    }
                });
    }
}
