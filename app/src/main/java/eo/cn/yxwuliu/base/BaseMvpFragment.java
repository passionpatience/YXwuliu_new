package eo.cn.yxwuliu.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mk on 2017/4/12.
 */

public abstract class BaseMvpFragment<V, T extends BasePresenter<V>> extends Fragment {
    public T presenter;

    protected Context mContext;//activity的上下文对象

    protected Bundle mBundle;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mBundle != null){
            outState.putBundle("bundle",mBundle);
        }
    }

    /**
     * 绑定activity
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    /**
     * 运行在onAttach（）之后
     * 可以接受别人传递过来的参数
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取bundle保存起来
        if (savedInstanceState != null){
            mBundle = savedInstanceState.getBundle("bundle");
        }else {
            mBundle = getArguments() == null ? new  Bundle() : getArguments();
        }
    }

    /**
     * 运行在onCreate之后
     * s生成视图
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 运行在onCreateView之后
     * 加载数据
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //由于fragment生命周期比较复杂,所以Presenter在onCreateView创建视图之后再进行绑定,不然会报空指针异常
        presenter = initPresenter();//创建presenter
        presenter.attach((V)this);
        initData();
        initView();
        initTitle();
        setListener();


    }

    protected abstract void initData();

    @Override
    public void onDetach() {
        presenter.dettach();
        super.onDetach();
    }

    /**
     * 初始化Fragment应有的视图
     *
     * @return
     */
    public abstract void initView();

    protected abstract void initTitle();

    protected abstract void setListener();

    public abstract T initPresenter();
}
