package eo.cn.yxwuliu.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import butterknife.ButterKnife;


/**
 * 这个类是用来模拟栈的模式来管理activity和销毁view的防止内存泄漏
 * Created by Administrator on 2017/3/28 0028.
 */
public abstract class BaseMvpActivity<V,T extends BasePresenter<V>> extends AppCompatActivity {
    public T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        //ActivityCollector.addActivity(this);
        presenter = initPresenter();
        initData();
        initView();
        setListener();
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.attach((V)this);
    }

    @Override
    protected void onDestroy() {
        presenter.dettach();
        //ActivityCollector.removeActivity(this);
        super.onDestroy();
    }

    //获取布局文件的id
    public abstract int getLayoutId();
    public abstract  T initPresenter();
    protected abstract void initData();
    //初始化组件
    public abstract void initView();
    protected abstract void setListener();
}
