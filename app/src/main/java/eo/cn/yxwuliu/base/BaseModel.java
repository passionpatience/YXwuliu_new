package eo.cn.yxwuliu.base;


import eo.cn.yxwuliu.http.RetrofitClient;
import eo.cn.yxwuliu.server.IHttpInterface;

/**
 * Created by mk on 2017/3/30.
 */

public class BaseModel {
    //为了日后的发展，这里可以尽情扩展，当isCache为FALSE时，则为最基本的网络请求

    public IHttpInterface iHttpInterface;

    public BaseModel(boolean isCache){

        if (!isCache){
            iHttpInterface = RetrofitClient.getRetrofit();
        }else {
            //这个来处理另一中请求
        }

    }
}
