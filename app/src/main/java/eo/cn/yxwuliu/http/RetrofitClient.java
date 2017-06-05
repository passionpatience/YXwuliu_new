package eo.cn.yxwuliu.http;


import eo.cn.yxwuliu.api.Api;
import eo.cn.yxwuliu.server.IHttpInterface;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 这个类是自己封装好的请求client，通过okhttpclient
 * Created by Administrator on 2017/3/28 0028.
 * 单例模式
 */

public class RetrofitClient {

    private static IHttpInterface singleton;

    public static IHttpInterface getRetrofit(OkHttpClient client) {
        if (singleton == null) {
            synchronized (RetrofitClient.class) {
                singleton = createRetrofit(client).create(IHttpInterface.class);
            }
        }
        return singleton;
    }
    public static IHttpInterface getRetrofit(){
        if (singleton == null){
            synchronized (RetrofitClient.class){
                singleton = createRetrofit().create(IHttpInterface.class);
            }
        }
        return singleton;
    }


    private static Retrofit createRetrofit(OkHttpClient client) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }
    private static Retrofit createRetrofit(){
        Retrofit retrofit=new Retrofit
                .Builder()
                .baseUrl(Api.baseUrl)
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                //增加返回值为Oservable<T>的支持
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }








}
