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
import eo.cn.yxwuliu.base.BaseMvpFragment;
import eo.cn.yxwuliu.bean.GoodsBean;
import eo.cn.yxwuliu.presenter.GoodSourcePresenter;
import eo.cn.yxwuliu.util.CustomProgressDialog;
import eo.cn.yxwuliu.view.IGoodSourceView;

/**
 * 货源界面
 * Created by mk on 2017/5/26.
 */

public class GoodSourceFragment extends BaseMvpFragment<IGoodSourceView, GoodSourcePresenter> implements IGoodSourceView{
    public static volatile GoodSourceFragment instance = null;
    @BindView(R.id.iv_add)
    RelativeLayout ivAdd;
    @BindView(R.id.title)
    RelativeLayout title;
    @BindView(R.id.tab_car_source)
    TabLayout tabCarSource;
    @BindView(R.id.vp_car_source)
    ViewPager vpCarSource;
    Unbinder unbinder;
    private final String[] titles = {"全部","附近"};
    public int type = 0;//货源类型，0全部，1附近
    private GoodSourceAdapter goodSourceAdapter;
    public GoodsBean goodsBean = null;
    private CustomProgressDialog progressDialog;

    public static GoodSourceFragment newInstance() {
        if (instance == null) {
            synchronized (GoodSourceFragment.class) {
                if (instance == null) {
                    instance = new GoodSourceFragment();
                }
            }
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_good_source, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        //设置tablayout
        tabCarSource.setTabMode(TabLayout.MODE_FIXED);
        tabCarSource.addTab(tabCarSource.newTab().setText(titles[0]));
        tabCarSource.addTab(tabCarSource.newTab().setText(titles[1]));
        //设置viewpager
        List<Fragment> fragments = new ArrayList<Fragment>();
        FragmentAllSource fragmentAllSource = new FragmentAllSource();
        FragmentNearSource fragmentNearSource = new FragmentNearSource();
        fragments.add(fragmentAllSource);
        fragments.add(fragmentNearSource);
        goodSourceAdapter = new GoodSourceAdapter(getChildFragmentManager(), fragments, titles);
        vpCarSource.setAdapter(goodSourceAdapter);
        //使tablayout和viewpager联动
        tabCarSource.setupWithViewPager(vpCarSource,true);
        tabCarSource.setTabsFromPagerAdapter(goodSourceAdapter);
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initView() {
        presenter.getGoodSourceData();
    }

    @Override
    public void setListener() {
        tabCarSource.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //获取货源类型
                type = tab.getPosition();
                presenter.getGoodSourceData();
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
    public GoodSourcePresenter initPresenter() {
        return new GoodSourcePresenter(this);
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
    public void showGoodSource(GoodsBean goodsBean) {
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
