package eo.cn.yxwuliu.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
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
import eo.cn.yxwuliu.util.CitySeclectUtil;
import eo.cn.yxwuliu.view.ICarSourceView;

/**
 * 这个是全部车源
 * Created by kebi on 2017/5/30.
 */

public class FragmentAllCar extends BaseMvpFragment<ICarSourceView, CarSourcePresenter> implements ICarSourceView, View.OnClickListener {


    @BindView(R.id.start_search_content)
    TextView mStartSearchContent;
    @BindView(R.id.end_search_content)
    TextView mEndSearchContent;
    @BindView(R.id.s_btn)
    ImageButton mSBtn;
    @BindView(R.id.recycler_goodes)
    XRecyclerView mRecyclerGoodes;
    Unbinder unbinder;
    private List<GoodsBean.DataBean> goodsBeenes = new ArrayList<GoodsBean.DataBean>();
    private CarSourceFragment carSourceFragment;
    private GoodsBean goodsBean = null;
    private List<GoodsBean.DataBean> tmp = new ArrayList<GoodsBean.DataBean>();
    private SearchCarAdapter searchCarAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_product_list, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {
        carSourceFragment = (CarSourceFragment) getParentFragment();
        goodsBean = carSourceFragment.goodsBean;
        if (goodsBean != null) {
            goodsBeenes.clear();
            goodsBeenes.addAll(goodsBean.getData());
            tmp = goodsBean.getData();
        }
    }

    @Override
    public void initView() {
        //设置recyclerview的adapter
        mRecyclerGoodes.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecyclerGoodes.setPullRefreshEnabled(true);
        mRecyclerGoodes.setLoadingMoreEnabled(true);
        searchCarAdapter = new SearchCarAdapter(goodsBeenes, getActivity());
        mRecyclerGoodes.setAdapter(searchCarAdapter);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setListener() {
        mStartSearchContent.setOnClickListener(this);
        mEndSearchContent.setOnClickListener(this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            }
        });

        //这个是上拉和下拉的监听事件
        mRecyclerGoodes.setLoadingListener(new XRecyclerView.LoadingListener() {
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
        return carSourceFragment.type + "";
    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public void showCarSource(GoodsBean goodsBean) {
//        if ((goodsBean.getData().size() % 2) != 0) {
//            mRecyclerGoodes.setNoMore(true);
//        }else {
//            goodsBeenes.clear();
//            goodsBeenes.addAll(tmp);
//            goodsBeenes.addAll(goodsBean.getData());
//            searchGoodsAdapter.notifyDataSetChanged();
//            mRecyclerGoodes.refreshComplete();
//            mRecyclerGoodes.loadMoreComplete();
//        }
        goodsBeenes.clear();
        goodsBeenes.addAll(tmp);
        goodsBeenes.addAll(goodsBean.getData());
//        searchCarAdapter = new SearchCarAdapter(goodsBeenes, getActivity());
    }

    @Override
    public void showDataFail(Object smg) {
        if (smg != null) {
            Toast.makeText(getActivity(), "获取数据失败" + smg, Toast.LENGTH_SHORT).show();
            mRecyclerGoodes.refreshComplete();
            mRecyclerGoodes.loadMoreComplete();
        }
    }

    @Override
    public void showMoreData(GoodsBean goodsBean) {
//        if (goodsBean.getData().size() < 1) {
//            mRecyclerGoodes.setNoMore(true);
//        } else {
//            goodsBeenes.addAll(goodsBean.getData());
//            searchGoodsAdapter = new SearchGoodsAdapter(goodsBeenes, getActivity());
//            searchGoodsAdapter.notifyDataSetChanged();
//            mRecyclerGoodes.loadMoreComplete();
//        }
        goodsBeenes.addAll(goodsBean.getData());
//        searchCarAdapter = new SearchCarAdapter(goodsBeenes, getActivity());
        searchCarAdapter.notifyDataSetChanged();
        mRecyclerGoodes.loadMoreComplete();
    }

    @Override
    public void showResettingData(GoodsBean goodsBean) {
//        goodsBeenes.clear();
        goodsBeenes.addAll(goodsBean.getData());
        searchCarAdapter = new SearchCarAdapter(goodsBeenes, getActivity());
        searchCarAdapter.notifyDataSetChanged();
        mRecyclerGoodes.refreshComplete();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_search_content:
                //选择出发地
                CitySeclectUtil.citySelect2(getActivity(), mStartSearchContent);
                break;
            case R.id.end_search_content:
                //选择到达地
                CitySeclectUtil.citySelect2(getActivity(), mEndSearchContent);
                break;
        }
    }
}
