package eo.cn.yxwuliu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import eo.cn.yxwuliu.R;
import eo.cn.yxwuliu.adapter.GoodSourceAdapter;
import eo.cn.yxwuliu.base.BaseFragment;
import eo.cn.yxwuliu.base.BaseMvpFragment;
import eo.cn.yxwuliu.bean.GoodsBean;
import eo.cn.yxwuliu.presenter.CarSourcePresenter;
import eo.cn.yxwuliu.util.CustomProgressDialog;
import eo.cn.yxwuliu.view.ICarSourceView;

/**
 * 车源列表
 * Created by mk on 2017/5/26.
 */

public class CarSourceFragment extends BaseMvpFragment<ICarSourceView,CarSourcePresenter> implements ICarSourceView{
    public static volatile CarSourceFragment instance = null;
    @BindView(R.id.iv_add)
    RelativeLayout mIvAdd;
    @BindView(R.id.title)
    RelativeLayout mTitle;
    @BindView(R.id.tab_car_source)
    TabLayout mTabCarSource;
    @BindView(R.id.vp_car_source)
    ViewPager mVpCarSource;
    Unbinder unbinder;
    private final String[] titles = {"全部","附近"};
    public int type = 0;//车源类型，0全部，1附近
    private GoodSourceAdapter goodSourceAdapter;
    public GoodsBean goodsBean = null;
    private CustomProgressDialog progressDialog;

    public static CarSourceFragment newInstance() {
        if (instance == null) {
            synchronized (CarSourceFragment.class) {
                if (instance == null) {
                    instance = new CarSourceFragment();
                }
            }
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_source, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        //设置tablayout
        mTabCarSource.setTabMode(TabLayout.MODE_FIXED);
        mTabCarSource.addTab(mTabCarSource.newTab().setText(titles[0]));
        mTabCarSource.addTab(mTabCarSource.newTab().setText(titles[1]));
        //设置viewpager
        List<Fragment> fragments = new ArrayList<Fragment>();
        FragmentAllCar fragmentAllCar = new FragmentAllCar();
        FragmentNearCar fragmentNearCar = new FragmentNearCar();
        fragments.add(fragmentAllCar);
        fragments.add(fragmentNearCar);
        goodSourceAdapter = new GoodSourceAdapter(getChildFragmentManager(), fragments, titles);//车源和货源的viewpage adapter一样
        mVpCarSource.setAdapter(goodSourceAdapter);
        //使tablayout和viewpager联动
        mTabCarSource.setupWithViewPager(mVpCarSource,true);
        mTabCarSource.setTabsFromPagerAdapter(goodSourceAdapter);
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initView() {
        presenter.getCarSourceData();
    }

    @Override
    public void setListener() {
        mTabCarSource.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //获取货源类型
                type = tab.getPosition();
                presenter.getCarSourceData();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public CarSourcePresenter initPresenter() {
        return new CarSourcePresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public String getType() {
        return type+"";
    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public void showCarSource(GoodsBean goodsBean) {
        this.goodsBean = goodsBean;
        goodSourceAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDataFail(Object smg) {
        Toast.makeText(getActivity(),"网络连接错误",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMoreData(GoodsBean goodsBean) {

    }

    @Override
    public void showResettingData(GoodsBean goodsBean) {

    }

    @Override
    public void showLoading() {
        progressDialog = new CustomProgressDialog(getActivity(),"网络君正在奔跑..", R.drawable.myprogressframe);
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
    }
}
