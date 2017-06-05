package eo.cn.yxwuliu.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import eo.cn.yxwuliu.R;
import eo.cn.yxwuliu.adapter.MsgSampleAdapter;
import eo.cn.yxwuliu.base.BaseMvpActivity;
import eo.cn.yxwuliu.presenter.MsgPresenter;
import eo.cn.yxwuliu.view.IMsgView;


/**
 * @author jk
 * @time 2017/5/18  10:36
 * @desc 首页右上角消息的页面
 */
public class MsgActivity extends BaseMvpActivity<IMsgView,MsgPresenter> implements IMsgView {

    private RecyclerView mRecycleView;
    private LinearLayoutManager mLayoutManager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_msg;
    }

    @Override
    public MsgPresenter initPresenter() {
        return new MsgPresenter(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initView() {
        mRecycleView = (RecyclerView) findViewById(R.id.recycleView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(mLayoutManager);
        MsgSampleAdapter adapter = new MsgSampleAdapter();
        mRecycleView.setAdapter(adapter);
    }

    @Override
    protected void setListener() {

    }

    public void back(View view) {
        finish();
    }


}
