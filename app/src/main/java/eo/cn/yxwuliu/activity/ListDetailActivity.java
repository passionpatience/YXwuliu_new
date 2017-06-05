package eo.cn.yxwuliu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import eo.cn.yxwuliu.R;
import eo.cn.yxwuliu.bean.GoodsBean;
import eo.cn.yxwuliu.widgets.OrderDialog;


/**
 * Created by Administrator on 2017/5/9.
 * 首页详情
 */
public class ListDetailActivity extends AppCompatActivity {
    private TextView mTvName;
    private TextView mTvGoodStart;
    private TextView mTvGoodDetail;
    private TextView mTvCarWant;
    private TextView mTvGoodEnd;
    private TextView mTvGetgoods;
    private TextView mTvWheregoods;
    private LinearLayout GoodsOrder;
    private GoodsBean.DataBean mDataBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail);
        initUI();
        initEvent();
    }

    private void initEvent() {
        GoodsOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderDialog dialog = new OrderDialog(ListDetailActivity.this,mDataBean);
                dialog.show();
            }
        });
    }

    protected void initUI() {
        mDataBean = new GoodsBean.DataBean();
        mTvName = (TextView) findViewById(R.id.g_name_tv);
        mTvGoodStart = (TextView) findViewById(R.id.tv_mypublishcars_starting);
        mTvGetgoods = (TextView) findViewById(R.id.get_goods_adress_tv);
        mTvWheregoods = (TextView) findViewById(R.id.goods_adress_tv);
        mTvGoodEnd = (TextView) findViewById(R.id.tv_mypublishcars_ending);
        mTvGoodDetail = (TextView) findViewById(R.id.goods_detail_tv);
        mTvCarWant = (TextView) findViewById(R.id.cars_want_tv);
        //接单按钮
        GoodsOrder = (LinearLayout) findViewById(R.id.toReport_btn);
        Intent intent = getIntent();
        GoodsBean.DataBean data = (GoodsBean.DataBean) intent.getSerializableExtra("data");
        mTvName.setText(data.getHuozhu_name());

        mTvWheregoods.setText("具体的送货到哪里");
        mTvGetgoods.setText("具体的取货地址");
        mTvGoodStart.setText(data.getYuan_dizhi());
        mTvGoodEnd.setText(data.getFahuo_dizhi());
        mTvGoodDetail.setText("货物详情:"+data.getHuo_kg() + "");
        mTvCarWant.setText("车辆需求:"+data.getChe_class());
    }

    public void back(View view) {
        finish();
    }
}
