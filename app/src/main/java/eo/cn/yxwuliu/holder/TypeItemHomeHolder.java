package eo.cn.yxwuliu.holder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import eo.cn.yxwuliu.R;
import eo.cn.yxwuliu.activity.ListDetailActivity;
import eo.cn.yxwuliu.bean.GoodsBean;
import eo.cn.yxwuliu.widgets.OrderDialog;
import eo.cn.yxwuliu.widgets.RoundImageView;


/**
 * @author jk
 * @time 2017/5/31  11:55
 * @desc ${TODD}
 */
public class TypeItemHomeHolder extends TypeBaseViewHolder<GoodsBean.DataBean> implements View.OnClickListener {

    private LinearLayout mTurnLL;
    private TextView mBookTv;
    private LinearLayout mSecondLL;
    private TextView name_tv;
    private TextView num_tv;
    private TextView register_time;
    private TextView time_tv;
    private TextView mTv_starting;
    private TextView mTv_ending;
    private TextView mGood_weigh;
    private TextView cars_go_need;
    private TextView mTv_distance;
    private TextView role_tv;
    private RoundImageView mFace_im;
    private Context mContext;
    private GoodsBean.DataBean mDataBean;

    public TypeItemHomeHolder(View itemView) {
        super(itemView);
        mFace_im = (RoundImageView) itemView.findViewById(R.id.face_im);
        role_tv = (TextView) itemView.findViewById(R.id.g_role_tv);
        name_tv = (TextView) itemView.findViewById(R.id.g_name_tv);
        num_tv = (TextView) itemView.findViewById(R.id.book_num_tv);
        register_time = (TextView) itemView.findViewById(R.id.tv_register_time);
        time_tv = (TextView) itemView.findViewById(R.id.msg_time_tv);//发布货源信息的时间
        mTv_starting = (TextView) itemView.findViewById(R.id.tv_starting);
        mTv_ending = (TextView) itemView.findViewById(R.id.tv_ending);
        mGood_weigh = (TextView) itemView.findViewById(R.id.tv_good_name_weight);
        cars_go_need = (TextView) itemView.findViewById(R.id.tv_cars_go_need);
        mTv_distance = (TextView) itemView.findViewById(R.id.tv_distance);

        //item布局的第二个部分
        mSecondLL = (LinearLayout) itemView.findViewById(R.id.second_ll);
        mTurnLL = (LinearLayout) itemView.findViewById(R.id.turn_ll);
        mBookTv = (TextView) itemView.findViewById(R.id.book_tv);

        mSecondLL.setOnClickListener(this);
        mTurnLL.setOnClickListener(this);
        mBookTv.setOnClickListener(this);

    }

    @Override
    public void bindHolder(GoodsBean.DataBean baseProduct) {

    }

    @Override
    public void bindHolder(Context context, GoodsBean.DataBean dataBean) {
        mFace_im.setImageResource(R.drawable.home_shopcar);
        role_tv.setText("货主");
        name_tv.setText(dataBean.getHuozhu_name());
        num_tv.setText("发货:100");
        register_time.setText("注册:1年");
        mTv_starting.setText(dataBean.getYuan_dizhi());
        mTv_ending.setText(dataBean.getFahuo_dizhi());
        mGood_weigh.setText(dataBean.getHuo_kg() + "");
        cars_go_need.setText(dataBean.getChe_class());
        mTv_distance.setText("里程120公里");
        this.mContext = context;
        this.mDataBean = dataBean;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.second_ll:
                //跳转到首页详情
                Intent intent = new Intent(mContext, ListDetailActivity.class);
                intent.putExtra("data", mDataBean);
                mContext.startActivity(intent);
//                ActivityUtil.StartActivity(mContext, MyOrderGoodsDetailActivity.class, false);
                break;
            case R.id.turn_ll:
                //转发
                break;
            case R.id.book_tv:
                //接单
                OrderDialog dialog = new OrderDialog(mContext,mDataBean);
                dialog.show();
                break;
            case R.id.face_im:
                //图片
        }
    }
}
