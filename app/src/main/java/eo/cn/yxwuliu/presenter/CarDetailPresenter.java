package eo.cn.yxwuliu.presenter;

import eo.cn.yxwuliu.activity.CarDetailsActivity;
import eo.cn.yxwuliu.base.BasePresenter;
import eo.cn.yxwuliu.bean.CarsBean;
import eo.cn.yxwuliu.bean.GoodsBean;
import eo.cn.yxwuliu.model.CarDetailModelImpl;
import eo.cn.yxwuliu.model.ICarDetailModel;
import eo.cn.yxwuliu.view.ICarDetailActivityView;

/**
 * 这个是连接model和view的逻辑处理
 * Created by Administrator on 2017/6/3.
 */

public class CarDetailPresenter extends BasePresenter<ICarDetailActivityView>{

    public ICarDetailActivityView mICarDetailActivityView;
    public ICarDetailModel mICarDetailModel = new CarDetailModelImpl(false);

    public CarDetailPresenter(ICarDetailActivityView mICarDetailActivityView) {
        this.mICarDetailActivityView = mICarDetailActivityView;
    }

    public void getCarDetail(){
        if (mICarDetailModel != null) {
            mICarDetailModel.setOnGetCarDetailData(mICarDetailActivityView.getCarDetailID(),
                    new ICarDetailModel.OnGetCarDetailData() {
                        @Override
                        public void getDataSuccess(GoodsBean goodsBean) {
                            mICarDetailActivityView.getSuccessData(goodsBean);
                        }

                        @Override
                        public void getDataFail(Object smg) {
                            mICarDetailActivityView.getDataFail(smg);
                        }
                    });
        }
    }

}
