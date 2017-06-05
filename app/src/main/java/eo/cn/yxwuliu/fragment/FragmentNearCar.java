package eo.cn.yxwuliu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import eo.cn.yxwuliu.R;
import eo.cn.yxwuliu.activity.CarDetailsActivity;
import eo.cn.yxwuliu.adapter.SearchCarAdapter;
import eo.cn.yxwuliu.base.BaseMvpFragment;
import eo.cn.yxwuliu.bean.GoodsBean;
import eo.cn.yxwuliu.presenter.CarSourcePresenter;
import eo.cn.yxwuliu.view.ICarSourceView;

/**
 * Created by kebi on 2017/5/30.
 */

public class FragmentNearCar extends BaseMvpFragment<ICarSourceView, CarSourcePresenter> implements ICarSourceView {

    @BindView(R.id.xrecyclerview_nearcar)
    XRecyclerView mXrecyclerviewNearcar;
    Unbinder unbinder;
    private CarSourceFragment carSourceFragment;
    private GoodsBean goodsBean = null;
    private List<GoodsBean.DataBean> cars = new ArrayList<GoodsBean.DataBean>();
    private List<GoodsBean.DataBean> tmp = new ArrayList<GoodsBean.DataBean>();
    private SearchCarAdapter searchCarAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_car_near_list, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {
        carSourceFragment = (CarSourceFragment) getParentFragment();
        goodsBean = carSourceFragment.goodsBean;
        if (goodsBean != null) {
            cars.clear();
            cars.addAll(goodsBean.getData());
            tmp = goodsBean.getData();
        }
    }

    @Override
    public void initView() {
        //设置recyclerview的adapter
        mXrecyclerviewNearcar.setLayoutManager(new LinearLayoutManager(getActivity()));
        mXrecyclerviewNearcar.setPullRefreshEnabled(true);
        searchCarAdapter = new SearchCarAdapter(cars, getActivity());
        mXrecyclerviewNearcar.setAdapter(searchCarAdapter);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setListener() {

        //点击头像进入车主详情
        searchCarAdapter.setOnClickListener(new SearchCarAdapter.OnCarIcoClickListener() {
            @Override
            public void icoClick(View view, int position) {

            }
        });

        //点击车源简介进入车源详情
        searchCarAdapter.setOnClickListener(new SearchCarAdapter.OnCarItemClickListener() {
            @Override
            public void itemClick(View view, int position) {
                //position传入的应是车源订单或车主唯一id，不是点击的item位置，现在暂时这样写
                CarDetailsActivity.actionStart(getActivity(),position+"");
            }
        });
        //点预定时的操作
        searchCarAdapter.setOnClickListener(new SearchCarAdapter.OnCarBookClickListener() {
            @Override
            public void bookClick(View view, int position) {

            }
        });

        /**
         * recyclerview的下拉刷新和加载事件
         */
        mXrecyclerviewNearcar.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                presenter.getRefreshCar();
            }

            @Override
            public void onLoadMore() {
                presenter.getMoreDataCarSource();
            }
        });
    }

    @Override
    public CarSourcePresenter initPresenter() {
        return new CarSourcePresenter(this);
    }

    @Override
    public String getType() {
        return carSourceFragment.type+"";
    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public void showCarSource(GoodsBean goodsBean) {
        cars.clear();
        cars.addAll(tmp);
        cars.addAll(goodsBean.getData());
        searchCarAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDataFail(Object smg) {
        if (smg != null){
            Toast.makeText(getActivity(),"获取数据失败"+smg,Toast.LENGTH_SHORT).show();
            mXrecyclerviewNearcar.refreshComplete();
            mXrecyclerviewNearcar.loadMoreComplete();
        }
    }

    @Override
    public void showMoreData(GoodsBean goodsBean) {
        if (goodsBean.getData().size() < 5) {
            mXrecyclerviewNearcar.setNoMore(true);
        } else {
            cars.addAll(goodsBean.getData());
//            searchGoodsAdapter = new SearchGoodsAdapter(goodsBeenes, getActivity());
            searchCarAdapter.notifyDataSetChanged();
            mXrecyclerviewNearcar.loadMoreComplete();
        }
    }

    @Override
    public void showResettingData(GoodsBean goodsBean) {
        cars.clear();
        cars.addAll(goodsBean.getData());
//        searchGoodsAdapter = new SearchGoodsAdapter(goodsBeenes, getActivity());
        searchCarAdapter.notifyDataSetChanged();
        mXrecyclerviewNearcar.refreshComplete();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
