package eo.cn.yxwuliu.base;

/**
 * 封装好的presenter基类
 * Created by Administrator on 2017/3/28 0028.
 */

public abstract class BasePresenter<T> {

    public T mView;
    public void attach(T mView) {
        this.mView = mView;
    }

    public void dettach() {
        mView = null;
    }
}
