package eo.cn.yxwuliu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
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
import eo.cn.yxwuliu.adapter.SearchGoodsAdapter;
import eo.cn.yxwuliu.base.BaseMvpFragment;
import eo.cn.yxwuliu.bean.GoodsBean;
import eo.cn.yxwuliu.presenter.GoodSourcePresenter;
import eo.cn.yxwuliu.view.IGoodSourceView;

/**
 * Created by kebi on 2017/5/30.
 */

public class FragmentNearSource extends BaseMvpFragment<IGoodSourceView, GoodSourcePresenter> implements IGoodSourceView {

    @BindView(R.id.xrecyclerview_neargood)
    XRecyclerView mXrecyclerviewNeargood;
    Unbinder unbinder;
    private GoodSourceFragment goodSourceFragment;
    private GoodsBean goodsBean = null;
    private List<GoodsBean.DataBean> goodsBeenes = new ArrayList<GoodsBean.DataBean>();
    private List<GoodsBean.DataBean> tmp = new ArrayList<GoodsBean.DataBean>();
    private SearchGoodsAdapter searchGoodsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_product_near_list, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {
        goodSourceFragment = (GoodSourceFragment) getParentFragment();
        goodsBean = goodSourceFragment.goodsBean;
        if (goodsBean != null) {
            goodsBeenes.clear();
            goodsBeenes.addAll(goodsBean.getData());
            tmp = goodsBean.getData();
        }
    }

    @Override
    public void initView() {
        //设置recyclerview的adapter
        mXrecyclerviewNeargood.setLayoutManager(new LinearLayoutManager(getActivity()));
        mXrecyclerviewNeargood.setPullRefreshEnabled(true);
        searchGoodsAdapter = new SearchGoodsAdapter(goodsBeenes, getActivity());
        mXrecyclerviewNeargood.setAdapter(searchGoodsAdapter);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setListener() {
        //这个是上拉和下拉的监听事件
        mXrecyclerviewNeargood.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                presenter.getRefreshGood();
            }

            @Override
            public void onLoadMore() {
                presenter.getMoreDataGoodSource();
            }
        });
    }

    @Override
    public GoodSourcePresenter initPresenter() {
        return new GoodSourcePresenter(this);
    }

    @Override
    public String getType() {
        return goodSourceFragment.type+"";
    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public void showGoodSource(GoodsBean goodsBean) {
        goodsBeenes.clear();
        goodsBeenes.addAll(tmp);
        goodsBeenes.addAll(goodsBean.getData());
        searchGoodsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDataFail(Object smg) {
        if (smg != null){
            Toast.makeText(getActivity(),"获取数据失败"+smg,Toast.LENGTH_SHORT).show();
            mXrecyclerviewNeargood.refreshComplete();
            mXrecyclerviewNeargood.loadMoreComplete();
        }
    }

    @Override
    public void showMoreData(GoodsBean goodsBean) {
        if (goodsBean.getData().size() < 5) {
            mXrecyclerviewNeargood.setNoMore(true);
        } else {
            goodsBeenes.addAll(goodsBean.getData());
//            searchGoodsAdapter = new SearchGoodsAdapter(goodsBeenes, getActivity());
            searchGoodsAdapter.notifyDataSetChanged();
            mXrecyclerviewNeargood.loadMoreComplete();
        }
    }

    @Override
    public void showResettingData(GoodsBean goodsBean) {
        goodsBeenes.clear();
        goodsBeenes.addAll(goodsBean.getData());
//        searchGoodsAdapter = new SearchGoodsAdapter(goodsBeenes, getActivity());
        searchGoodsAdapter.notifyDataSetChanged();
        mXrecyclerviewNeargood.refreshComplete();
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
