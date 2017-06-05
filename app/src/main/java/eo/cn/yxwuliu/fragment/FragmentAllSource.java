package eo.cn.yxwuliu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import eo.cn.yxwuliu.util.CitySeclectUtil;
import eo.cn.yxwuliu.util.CustomProgressDialog;
import eo.cn.yxwuliu.view.IGoodSourceView;

/**
 * 这个是全部货源
 * Created by kebi on 2017/5/30.
 */

public class FragmentAllSource extends BaseMvpFragment<IGoodSourceView, GoodSourcePresenter> implements IGoodSourceView, View.OnClickListener {

    @BindView(R.id.start_search_content)
    TextView startSearchContent;
    @BindView(R.id.start_search_clear)
    ImageView startSearchClear;
    @BindView(R.id.startAddressView)
    RelativeLayout startAddressView;
    @BindView(R.id.end_search_content)
    TextView endSearchContent;
    @BindView(R.id.end_search_clear)
    ImageView endSearchClear;
    @BindView(R.id.endAddressView)
    RelativeLayout endAddressView;
    @BindView(R.id.s_btn)
    ImageButton sBtn;
    @BindView(R.id.linesView)
    LinearLayout linesView;
    Unbinder unbinder;
    @BindView(R.id.recycler_goodes)
    XRecyclerView mRecyclerGoodes;
    private List<GoodsBean.DataBean> goodsBeenes = new ArrayList<GoodsBean.DataBean>();
    private SearchGoodsAdapter searchGoodsAdapter;
    private GoodSourceFragment goodSourceFragment;
    private GoodsBean goodsBean = null;
    private List<GoodsBean.DataBean> tmp = new ArrayList<GoodsBean.DataBean>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_product_list, null);
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
        mRecyclerGoodes.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerGoodes.setPullRefreshEnabled(true);
        mRecyclerGoodes.setLoadingMoreEnabled(true);
        searchGoodsAdapter = new SearchGoodsAdapter(goodsBeenes, getActivity());
        mRecyclerGoodes.setAdapter(searchGoodsAdapter);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setListener() {
        startSearchContent.setOnClickListener(this);
        endSearchContent.setOnClickListener(this);

        //这个是上拉和下拉的监听事件
        mRecyclerGoodes.setLoadingListener(new XRecyclerView.LoadingListener() {
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
        Log.e("all车源", "getType: "+goodSourceFragment.type);
        return goodSourceFragment.type+"";
    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public void showGoodSource(GoodsBean goodsBean) {
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
        searchGoodsAdapter = new SearchGoodsAdapter(goodsBeenes, getActivity());
    }

    @Override
    public void showDataFail(Object smg) {
        if (smg != null){
            Toast.makeText(getActivity(),"获取数据失败"+smg,Toast.LENGTH_SHORT).show();
            mRecyclerGoodes.refreshComplete();
            mRecyclerGoodes.loadMoreComplete();
        }
    }

    @Override
    public void showMoreData(GoodsBean goodsBean) {
        if (goodsBean.getData().size() < 1) {
            mRecyclerGoodes.setNoMore(true);
        } else {
            goodsBeenes.addAll(goodsBean.getData());
            searchGoodsAdapter = new SearchGoodsAdapter(goodsBeenes, getActivity());
            searchGoodsAdapter.notifyDataSetChanged();
            mRecyclerGoodes.loadMoreComplete();
        }
//        goodsBeenes.addAll(goodsBean.getData());
//        searchGoodsAdapter = new SearchGoodsAdapter(goodsBeenes, getActivity());
//        searchGoodsAdapter.notifyDataSetChanged();
//        mRecyclerGoodes.loadMoreComplete();
    }

    @Override
    public void showResettingData(GoodsBean goodsBean) {
        goodsBeenes.clear();
        goodsBeenes.addAll(goodsBean.getData());
        searchGoodsAdapter = new SearchGoodsAdapter(goodsBeenes, getActivity());
        searchGoodsAdapter.notifyDataSetChanged();
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
                CitySeclectUtil.citySelect2(getActivity(), startSearchContent);
                break;
            case R.id.end_search_content:
                //选择到达地
                CitySeclectUtil.citySelect2(getActivity(), endSearchContent);
                break;
        }
    }
}
