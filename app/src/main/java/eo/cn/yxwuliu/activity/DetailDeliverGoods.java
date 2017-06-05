package eo.cn.yxwuliu.activity;

import android.app.AlertDialog;
import android.text.format.Time;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import cn.aigestudio.datepicker.cons.DPMode;
import cn.aigestudio.datepicker.views.DatePicker;
import eo.cn.yxwuliu.R;
import eo.cn.yxwuliu.adapter.SpinnerArrayAdapter;
import eo.cn.yxwuliu.base.BaseMvpActivity;
import eo.cn.yxwuliu.http.RetrofitClient;
import eo.cn.yxwuliu.presenter.DetailDeliverGoodsPresenter;
import eo.cn.yxwuliu.server.IHttpInterface;
import eo.cn.yxwuliu.util.CitySeclectUtil;
import eo.cn.yxwuliu.view.IDetailDeliverGoodsView;
import eo.cn.yxwuliu.widgets.CustomDatePicker;
import eo.cn.yxwuliu.widgets.ProductTypeSelect;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author jk
 * @time 2017/5/17  9:44
 * @desc 发布货源
 */
public class DetailDeliverGoods extends BaseMvpActivity<IDetailDeliverGoodsView, DetailDeliverGoodsPresenter> implements IDetailDeliverGoodsView, View.OnClickListener {

    @BindView(R.id.textview_delivergoods_starting)
    TextView textviewdelivergoodsstarting; //请选择出发地
    @BindView(R.id.edittext_delivergoods_ending)
    TextView edittextdelivergoodsending; //请选择到达地

    @BindView(R.id.toggleButton_delivergoods_starting)
    ToggleButton toggleButtonDelivergoodsStarting; //需要取货

    @BindView(R.id.takegoodsaddress_view) //取货地址
            RelativeLayout takegoodsaddressview;

    @BindView(R.id.deliveryaddress_view) //送货地址
            RelativeLayout deliveryaddressview;

    @BindView(R.id.imageView_delivergoods_ending)//需要送货
            ToggleButton imageViewDelivergoodsEnding;
    @BindView(R.id.textView_delivergoods_01) //送货地址
            TextView textViewDelivergoods01;

    @BindView(R.id.edittext_delivergoods_type) //货物类型
            TextView edittextDelivergoodsType;
    @BindView(R.id.goodsname_query)  // 货物名称
            AutoCompleteTextView goodsnameQuery;
    @BindView(R.id.edittext_delivergoods_kg) // 请输入货物重量
            EditText edittextDelivergoodsKg;
    @BindView(R.id.spinner_tab)   //请输入货物重量
            Spinner spinnerTab;
    @BindView(R.id.edittext_delivergoods_cube) //请输入货物体积
            EditText edittextDelivergoodsCube;
    @BindView(R.id.edittext_delivergoods_carneed) //选择车辆的类型
            TextView edittextdelivergoodscarneed;
    @BindView(R.id.edittext_delivergoods_time) //选择车辆的时间
            TextView edittextdelivergoodstime;
    @BindView(R.id.textview_delivergoods_unit) //意向价格
            Spinner textviewdelivergoodsunit;
    @BindView(R.id.button_delivergoods_publish) //立即发布
            Button buttondelivergoodspublish;
    private CustomDatePicker mCustomDatePicker;


    @Override
    public int getLayoutId() {
        return R.layout.deliver_goods_detail;
    }

    @Override
    public DetailDeliverGoodsPresenter initPresenter() {
        return new DetailDeliverGoodsPresenter(this);
    }

    @Override
    protected void initData() {
        //数据
        List<String> data_list = new ArrayList<String>();
        data_list.add("吨");
        data_list.add("千克");
        data_list.add("单位");
        //适配器
        SpinnerArrayAdapter arrAdapter = new SpinnerArrayAdapter<String>(this, R.layout.simple_spinner_item, data_list);
        //设置样式
        //                arrAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        arrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinnerTab.setAdapter(arrAdapter);
        spinnerTab.setSelection(data_list.size() - 1, true);

        //价格意向
        //数据
        List<String> data_list2 = new ArrayList<String>();
        data_list2.add("价格面议");
        data_list2.add("元/批");
        data_list2.add("元/吨");
        data_list2.add("元/车");
        //适配器
        SpinnerArrayAdapter arrAdapter2 = new SpinnerArrayAdapter<String>(this, R.layout.simple_spinner_item, data_list2);
        //设置样式
        arrAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        textviewdelivergoodsunit.setAdapter(arrAdapter2);
        textviewdelivergoodsunit.setSelection(data_list2.size() - 1, true);

    }


    @Override
    public void initView() {

    }

    @Override
    protected void setListener() {
        textviewdelivergoodsstarting.setOnClickListener(this);
        edittextdelivergoodsending.setOnClickListener(this);
        toggleButtonDelivergoodsStarting.setOnClickListener(this);
        imageViewDelivergoodsEnding.setOnClickListener(this);
        edittextDelivergoodsType.setOnClickListener(this);

        edittextdelivergoodscarneed.setOnClickListener(this);
        edittextdelivergoodstime.setOnClickListener(this);
        buttondelivergoodspublish.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //选择出发地
            case R.id.textview_delivergoods_starting:
                CitySeclectUtil.citySelect(DetailDeliverGoods.this, textviewdelivergoodsstarting);
                break;
            //选择到达地
            case R.id.edittext_delivergoods_ending:
                CitySeclectUtil.citySelect(DetailDeliverGoods.this, edittextdelivergoodsending);
                break;
            case R.id.toggleButton_delivergoods_starting:
                if (toggleButtonDelivergoodsStarting.isChecked()) {
                    takegoodsaddressview.setVisibility(View.VISIBLE);
                } else {
                    takegoodsaddressview.setVisibility(View.GONE);
                }
                break;
            case R.id.imageView_delivergoods_ending:
                if (imageViewDelivergoodsEnding.isChecked()) {
                    deliveryaddressview.setVisibility(View.VISIBLE);
                } else {
                    deliveryaddressview.setVisibility(View.GONE);
                }
                break;
            //货物类型
            case R.id.edittext_delivergoods_type:
                final ProductTypeSelect typeSelectdialog = new ProductTypeSelect(DetailDeliverGoods.this);
                typeSelectdialog.show();
                typeSelectdialog.setOnItemClickListener(new ProductTypeSelect.OnItemClickListener() {
                    @Override
                    public void itemClick(View view, String type) {
                        edittextDelivergoodsType.setText(type);
                        typeSelectdialog.dismiss();
                    }
                });
                break;

            //车辆需求
            case R.id.textview_delivergoods_unit:

                break;
            //装车时间
            case R.id.edittext_delivergoods_time:
                timeSelect();
                break;
            //立即发布
            case R.id.button_delivergoods_publish:
                String startWhere = textviewdelivergoodsstarting.getText().toString().trim();
                String ArriveWhere = edittextdelivergoodsending.getText().toString().trim();
                String productType = edittextDelivergoodsType.getText().toString().trim();
                String productWeight = edittextDelivergoodsKg.getText().toString().trim();
//                if (TextUtils.isEmpty(startWhere)) {
//                    Toast.makeText(DetailDeliverGoods.this, "请先选择出发地", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(ArriveWhere)) {
//                    Toast.makeText(DetailDeliverGoods.this, "请先选择到达地", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(productType)) {
//                    Toast.makeText(DetailDeliverGoods.this, "请先选择货物类型", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(productWeight)) {
//                    Toast.makeText(DetailDeliverGoods.this, "请输入货物重量", Toast.LENGTH_SHORT).show();
//                    return;
//                }

                IHttpInterface retrofit = RetrofitClient.getRetrofit();
                HashMap<String, String> paramsMap = new HashMap<>();
                paramsMap.put("yuan_dizhi", startWhere);
//                paramsMap.put("fahuo_dizhi", ArriveWhere);
//                paramsMap.put("parent_id", productType);
//                paramsMap.put("huo_kg", productWeight);
                final Call<String> call = retrofit.getGoodsInfo(paramsMap);
                /*new Thread() {
                    public void run() {
                        try {
                            Response<String> response = call.execute();
                            Log.v("TAG", response.body().toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                finish();
                Toast.makeText(DetailDeliverGoods.this, "发布成功", Toast.LENGTH_SHORT).show();
                break;*/
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.code() == 200) {
                            Log.v("TAG", response.body().toString());
                            Toast.makeText(DetailDeliverGoods.this, "发布成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.v("TAG", t.getMessage());
                        Toast.makeText(DetailDeliverGoods.this, "失败" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        }
    }

    private void timeSelect() {

        final AlertDialog dialog = new AlertDialog.Builder(DetailDeliverGoods.this).create();
        dialog.show();
        DatePicker picker = new DatePicker(DetailDeliverGoods.this);
        Time t = new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
        t.setToNow(); // 取得系统时间。
        int year = t.year;
        int month = t.month;
        picker.setDate(year, month);
        picker.setMode(DPMode.SINGLE);
        picker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
            @Override
            public void onDatePicked(String date) {
                edittextdelivergoodstime.setText(date);
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
