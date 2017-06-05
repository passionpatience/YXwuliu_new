package eo.cn.yxwuliu.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import eo.cn.yxwuliu.R;
import eo.cn.yxwuliu.base.BaseMvpActivity;
import eo.cn.yxwuliu.bean.GoodsBean;
import eo.cn.yxwuliu.presenter.CarDetailPresenter;
import eo.cn.yxwuliu.view.ICarDetailActivityView;
import eo.cn.yxwuliu.widgets.RoundImageView;

/**
 * 这个是车源详情界面
 * 根据简介界面的传入发布人的信息来获取更多的信息并且显示出来
 * Created by Administrator on 2017/6/2.
 */

public class CarDetailsActivity extends BaseMvpActivity<ICarDetailActivityView, CarDetailPresenter> implements ICarDetailActivityView {

    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.face_im)
    RoundImageView mFaceIm;
    @BindView(R.id.g_role_tv)
    TextView mGRoleTv;
    @BindView(R.id.g_name_tv)
    TextView mGNameTv;
    @BindView(R.id.release_num_tv)
    TextView mReleaseNumTv;
    @BindView(R.id.tv_register_time)
    TextView mTvRegisterTime;
    @BindView(R.id.g_time_tv)
    TextView mGTimeTv;
    @BindView(R.id.tv_mypublishcars_starting)
    TextView mTvMypublishcarsStarting;
    @BindView(R.id.tv_mypublishcars_ending)
    TextView mTvMypublishcarsEnding;
    @BindView(R.id.tv_and_my_distance)
    TextView mTvAndMyDistance;
    @BindView(R.id.tv_mygoods_transport_course)
    TextView mTvMygoodsTransportCourse;
    @BindView(R.id.departureTime)
    TextView mDepartureTime;
    @BindView(R.id.vehicleDetails)
    TextView mVehicleDetails;
    @BindView(R.id.loadWeight)
    TextView mLoadWeight;
    @BindView(R.id.carrierVolume)
    TextView mCarrierVolume;
    @BindView(R.id.phonenumber)
    TextView mPhonenumber;
    @BindView(R.id.noteInformation)
    TextView mNoteInformation;
    @BindView(R.id.makePhoneCall_btn)
    LinearLayout mMakePhoneCallBtn;
    @BindView(R.id.sendMessage_btn)
    LinearLayout mSendMessageBtn;
    private String carId;

    /**
     * 通过此方法跳转此页面
     * @param context
     * @param carId 这个是被点击到的发布者信息的ID
     */
    public static void actionStart(Context context, String carId) {
        Intent intent = new Intent(context, CarDetailsActivity.class);
        intent.putExtra("ID", carId);//车源信息id
        context.startActivity(intent);
    }

    @Override
    public String getCarDetailID() {
        return carId;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        carId = intent.getStringExtra("ID");
        presenter.getCarDetail();
    }

    @Override
    public void initView() {

    }

    @Override
    public void getSuccessData(GoodsBean goodsbean) {
        //数据显示,暂时使用goodbean测试
    }

    @Override
    public void getDataFail(Object smg) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_car_details;
    }

    @Override
    public CarDetailPresenter initPresenter() {
        return new CarDetailPresenter(this);
    }

    @Override
    protected void setListener() {

    }

    @OnClick({R.id.ll_back, R.id.face_im, R.id.makePhoneCall_btn, R.id.sendMessage_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.face_im:
                break;
            case R.id.makePhoneCall_btn:
                Toast.makeText(this, "打电话", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sendMessage_btn:
                Toast.makeText(this, "发短信", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
