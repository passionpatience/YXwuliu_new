package eo.cn.yxwuliu.server;

import java.util.Map;

import eo.cn.yxwuliu.api.Api;
import eo.cn.yxwuliu.bean.BannerResult;
import eo.cn.yxwuliu.bean.GoodsBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by mk on 2017/5/26.
 */

public interface IHttpInterface {

    /**
     * 获取货源
     * @return
     */
    @GET(Api.GOODS_URL)
    Observable<GoodsBean> getGoodSource(
            @Query("num") String num,@Query("page") String page);

    /**
     * 获取车源
     * @return
     */
    @GET(Api.GOODS_URL)//暂时使用货源接口来测试
    Observable<GoodsBean> getCarSource(
            @Query("num") String num,@Query("page") String page);

    // 轮播图(焦点图)
    @GET("feng/imglst")
    Observable<BannerResult> getBannerView();


    @GET("feng/huoyuanlst")
    Observable<GoodsBean> getListView(@Query("num") String num, @Query("page") String page);


    //上拉加载
    @GET("feng/huoyuanlst/")
    Observable<GoodsBean> getMoreView(@Query("num") String num, @Query("page") String page);

    //发布货源
    @GET("feng/huoyuanadd")
    Call<String> getGoodsInfo(@QueryMap Map<String, String> paramsMap);
}
