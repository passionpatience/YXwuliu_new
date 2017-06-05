package eo.cn.yxwuliu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zaaach.citypicker.CityPickerActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import eo.cn.yxwuliu.R;
import eo.cn.yxwuliu.activity.DetailDeliverCar;
import eo.cn.yxwuliu.activity.DetailDeliverGoods;
import eo.cn.yxwuliu.activity.DetailYunDan;
import eo.cn.yxwuliu.activity.DetailYunJia;
import eo.cn.yxwuliu.activity.MsgActivity;
import eo.cn.yxwuliu.adapter.HomeAdapter;
import eo.cn.yxwuliu.adapter.ImagePagerAdapter;
import eo.cn.yxwuliu.api.NetWorkConst;
import eo.cn.yxwuliu.base.BaseMvpFragment;
import eo.cn.yxwuliu.bean.BannerResult;
import eo.cn.yxwuliu.bean.GoodsBean;
import eo.cn.yxwuliu.presenter.HomeProductPresenter;
import eo.cn.yxwuliu.util.ActivityUtil;
import eo.cn.yxwuliu.view.IHomeFragmentView;

import static android.app.Activity.RESULT_OK;


/**
 * Created by mk on 2017/5/26.
 */

public class HomeFragment extends BaseMvpFragment<IHomeFragmentView, HomeProductPresenter> implements IHomeFragmentView {
    private static final int REQUEST_CODE_PICK_CITY = 0;
    public static volatile HomeFragment instance = null;
    Unbinder unbinder;
    @BindView(R.id.XRecyclerView)
    XRecyclerView mXRecyclerView;
    @BindView(R.id.locationImg)
    RelativeLayout mLocationImg;
    @BindView(R.id.msg_setting)
    RelativeLayout mMsgSetting;
    @BindView(R.id.location)
    TextView mLocation;
    private LinearLayout mLLyunjia;
    private LinearLayout mLLyundan;
    private LinearLayout mLLdeliveryCar;
    private LinearLayout mLLdeliveryGoods;
    private LinearLayout mLLtoolBox;
    private View mHeadView;
    private AutoScrollViewPager viewPager;
    private ArrayList<Object> mBannerViewList;
    private HomeAdapter mHomeAdapter;
    private List<GoodsBean.DataBean> mDatas = new ArrayList<GoodsBean.DataBean>();

    public static HomeFragment newInstance() {
        if (instance == null) {
            synchronized (HomeFragment.class) {
                if (instance == null) {
                    instance = new HomeFragment();
                }
            }
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment, null);
        //fragment子布局
        mHeadView = LayoutInflater.from(getContext()).inflate(R.layout.fragmenthome_banner, null);
        unbinder = ButterKnife.bind(this, view);

        //图片的集合
        mBannerViewList = new ArrayList<>();
        return view;
    }

    @Override
    public void initData() {
        presenter.getBannerData();
        presenter.getListData();

        //
        mHomeAdapter = new HomeAdapter(getContext(), mDatas);
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initView() {

        //八个工具
        View view = mHeadView.findViewById(R.id.include_ad_view);
        mLLdeliveryCar = (LinearLayout) view.findViewById(R.id.deliveryCar);
        mLLdeliveryGoods = (LinearLayout) view.findViewById(R.id.deliveryGoods);
        mLLyunjia = (LinearLayout) view.findViewById(R.id.yunjia);
        mLLyundan = (LinearLayout) view.findViewById(R.id.yundan);
        mLLtoolBox = (LinearLayout) view.findViewById(R.id.tool_box_ll);

        //这个是获取广告滚动的布局和绑定
        viewPager = (AutoScrollViewPager) mHeadView.findViewById(R.id.view_pager);

        mXRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mXRecyclerView.setLoadingMoreEnabled(true);

        mXRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mXRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mXRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);

    }

    @Override
    public void setListener() {

        //这个是上拉和下拉的监听事件
        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //最外层的recyclerview的刷新时间
                Log.v("TAG", "onRefresh");
                presenter.getRefreshProduct();
                mXRecyclerView.refreshComplete();
            }


            @Override
            public void onLoadMore() {
                //最外层recyclerview的上拉加载事件
                Log.v("TAG", "onLoadMore" + "");
                presenter.getMoreProductData();
                mXRecyclerView.loadMoreComplete();

            }
        });

        //RecycleView的适配器
        mXRecyclerView.setAdapter(mHomeAdapter);

        //定位的点击事件
        mLocationImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动城市定位
                startActivityForResult(new Intent(getContext(), CityPickerActivity.class),
                        REQUEST_CODE_PICK_CITY);
            }
        });

        //消息的监听
        mMsgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动消息界面
                ActivityUtil.StartActivity(getContext(), MsgActivity.class, false);
            }
        });


        mLLyunjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.StartActivity(getContext(), DetailYunJia.class, false);
            }
        });
        mLLyundan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.StartActivity(getContext(), DetailYunDan.class, false);
            }
        });
        mLLdeliveryCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.StartActivity(getContext(), DetailDeliverCar.class, false);
            }
        });
        mLLdeliveryGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.StartActivity(getContext(), DetailDeliverGoods.class, false);
            }
        });


    }

    //重写onActivityResult方法
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK) {
            if (data != null) {
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                mLocation.setText(city);
            }
        }
    }

    @Override
    public HomeProductPresenter initPresenter() {
        return new HomeProductPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    //只需要展示
    @Override
    public void showBanner(BannerResult bannerResult) {
        Log.d("TAG", bannerResult.toString());

        List<BannerResult.DataBean> dataBeen = bannerResult.getData();
        for (int i = 0; i < dataBeen.size(); i++) {
            BannerResult.DataBean dataBean = dataBeen.get(i);
            String photo = dataBean.getPhoto();
            photo = NetWorkConst.BANNER_URL_IMAGER + photo;
            Log.d("TAG", photo);
            mBannerViewList.add(photo);
        }
        ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter(getContext(), mBannerViewList).setInfiniteLoop(true);
        viewPager.setAdapter(imagePagerAdapter);
        viewPager.setInterval(3000);
        viewPager.startAutoScroll();
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2
                % ImagePagerAdapter.getSize(mBannerViewList));
        mXRecyclerView.addHeaderView(mHeadView);//加入头，无限滚动

    }

    //展示列表
    @Override
    public void showListData(GoodsBean goodsBean) {
        //        mDatas.clear();
        mDatas.addAll(goodsBean.getData());
        mHomeAdapter = new HomeAdapter(getContext(), mDatas);
        mHomeAdapter.notifyDataSetChanged();

    }

    @Override
    public void showMoreData(GoodsBean goodsBean) {
        Log.v("TAG", "goodsBean" + goodsBean.toString() + "333333333333333333333333");
        if (goodsBean.getData().size() < 8) {
            mXRecyclerView.setNoMore(true);
        } else {
            this.mDatas.addAll(goodsBean.getData());
            mHomeAdapter = new HomeAdapter(getContext(), mDatas);
            mHomeAdapter.notifyDataSetChanged();
            mXRecyclerView.loadMoreComplete();
        }
    }

    @Override
    public void showRefreshData(GoodsBean goodsBean) {

    }

    @Override
    public void getDataFail(Object msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
