package eo.cn.yxwuliu.widgets;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import eo.cn.yxwuliu.R;
import eo.cn.yxwuliu.bean.GoodsBean;


/**
 * Created by Administrator on 2017/5/19.
 * 弹出接单的对话框
 */
public class OrderDialog extends Dialog implements View.OnClickListener {

    private ImageView mView;
    private EditText mEtPrice;
    private Button mBtnOk;
    private GoodsBean.DataBean mDataBean;
    private TextView mTvStart;
    private TextView mTvEnd;
    private TextView mTvGoodType;
    private TextView mTvGoodDetail;
    private TextView mTvCarWant;
    private TextView mTvAuth_view;
    private TextView mTvLack_view;

    public OrderDialog(Context context, GoodsBean.DataBean dataBean) {
        super(context);
        this.mDataBean = dataBean;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_dialog);
        initUI();
        initEvent();
        initData();
    }

    private void initData() {
        mTvStart.setText(mDataBean.getYuan_dizhi());
        mTvEnd.setText(mDataBean.getFahuo_dizhi());
        mTvGoodType.setText("货物类型:" + mDataBean.getHuo_kg());
        mTvGoodDetail.setText("货物详情:" + mDataBean.getCreated());
        mTvCarWant.setText("车辆需求:" + mDataBean.getChe_class());
    }

    private void initEvent() {
        mView.setOnClickListener(this);
        mBtnOk.setOnClickListener(this);
    }

    private void initUI() {
        mTvStart = (TextView) findViewById(R.id.start_tv);
        mTvEnd = (TextView) findViewById(R.id.end_tv);
        mTvGoodType = (TextView) findViewById(R.id.goods_detail_type);
        mTvGoodDetail = (TextView) findViewById(R.id.goods_detail_tv);
        mTvCarWant = (TextView) findViewById(R.id.car_want_tv);
        mView = (ImageView) findViewById(R.id.iv_close);
        mBtnOk = (Button) findViewById(R.id.btn_order_ok);
        mEtPrice = (EditText) findViewById(R.id.price_et);
        //已认证:
        mTvAuth_view = (TextView) findViewById(R.id.alreadly_auth_view);
        mTvAuth_view.setText("身份证");
        //缺少证件:
        mTvLack_view = (TextView) findViewById(R.id.lack_str_view);
        mTvAuth_view.setText("营业执照丶门头照丶名片照");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.btn_order_ok:
                String price = mEtPrice.getText().toString().trim();
                if (TextUtils.isEmpty(price)) {
                    Toast.makeText(getContext(), "请输入报价", Toast.LENGTH_SHORT).show();
                } else {
                    dismiss();
                }
                break;
        }
    }
}
