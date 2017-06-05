package eo.cn.yxwuliu.activity;

import android.app.AlertDialog;
import android.text.format.Time;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import cn.aigestudio.datepicker.cons.DPMode;
import cn.aigestudio.datepicker.views.DatePicker;
import eo.cn.yxwuliu.R;
import eo.cn.yxwuliu.base.BaseMvpActivity;
import eo.cn.yxwuliu.presenter.DetailDeliverCarPresenter;
import eo.cn.yxwuliu.util.CitySeclectUtil;
import eo.cn.yxwuliu.widgets.SpinnerButton;

/**
 * @author jk
 * @time 2017/5/17  9:42
 * @desc 发布车源
 */
public class DetailDeliverCar extends BaseMvpActivity implements View.OnClickListener {

    @BindView(R.id.edittext_publishcars_starting)
    TextView mEdittextPublishcarsStarting;
    @BindView(R.id.edittext_publishcars_ending)
    TextView mEdittextPublishcarsEnding;
    @BindView(R.id.edittext_publishcars_cartype)
    TextView mEdittextPublishcarsCartype;
    @BindView(R.id.edittext_publishcars_kg)
    EditText mEdittextPublishcarsKg;
    @BindView(R.id.spinner_tab)
    SpinnerButton mSpinnerTab;
    @BindView(R.id.edittext_publishcars_size)
    EditText mEdittextPublishcarsSize;
    @BindView(R.id.textview_delivergoods_Stere)
    TextView mTextviewDelivergoodsStere;
    @BindView(R.id.edittext_publishcars_starttime) //发车时间
    TextView mEdittextPublishcarsStarttime;
    @BindView(R.id.remark)
    EditText mRemark;
    @BindView(R.id.button_delivergoods_publish)
    Button mButtonDelivergoodsPublish;

    @Override
    public int getLayoutId() {
        return R.layout.deliver_car_detail;
    }

    @Override
    public DetailDeliverCarPresenter initPresenter() {
        return new DetailDeliverCarPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    protected void setListener() {
        mEdittextPublishcarsStarting.setOnClickListener(this);
        mEdittextPublishcarsEnding.setOnClickListener(this);
        mEdittextPublishcarsCartype.setOnClickListener(this); //车辆类型
        mEdittextPublishcarsStarttime.setOnClickListener(this); //发车时间

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edittext_publishcars_starting:
                CitySeclectUtil.citySelect(DetailDeliverCar.this, mEdittextPublishcarsStarting);
                break;
            case R.id.edittext_publishcars_ending:
                CitySeclectUtil.citySelect2(DetailDeliverCar.this, mEdittextPublishcarsEnding);
                break;
            case R.id.edittext_publishcars_cartype:
//                CitySeclectUtil.citySelect2(DetailDeliverCar.this, mEdittextPublishcarsEnding);
                break;
            case R.id.edittext_publishcars_starttime:
                timeSelect();
                break;
        }
    }

    private void timeSelect() {

        final AlertDialog dialog = new AlertDialog.Builder(DetailDeliverCar.this).create();
        dialog.show();
        DatePicker picker = new DatePicker(DetailDeliverCar.this);
        Time t = new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
        t.setToNow(); // 取得系统时间。
        int year = t.year;
        int month = t.month;
        picker.setDate(year, month);
        picker.setMode(DPMode.SINGLE);
        picker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
            @Override
            public void onDatePicked(String date) {
                mEdittextPublishcarsStarttime.setText(date);
                dialog.dismiss();
            }
        });
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setContentView(picker, params);
        dialog.getWindow().setGravity(Gravity.CENTER);
    }

    public void back(View view) {
        finish();
    }


}
