package eo.cn.yxwuliu.holder;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import eo.cn.yxwuliu.R;
import eo.cn.yxwuliu.bean.GoodsBean;
import eo.cn.yxwuliu.util.DateUtils;
import eo.cn.yxwuliu.widgets.RoundImageView;

/**
 * 这个是车源界面的viewHolder
 * Created by kebi on 2017/5/30.
 */

public class TypeItemSearchCarHolder extends TypeBaseViewHolder<GoodsBean.DataBean> {

    RoundImageView face_im;
    TextView g_role_tv;
    TextView g_name_tv;
    TextView up_time;
    TextView order_num_tv;
    TextView tv_register_time;
    TextView tv_starting;
    TextView tv_ending;
    TextView tv_distance;
    TextView tv_cars_name;
    TextView tv_cars_go_time;
    LinearLayout turn_ll;
    TextView tv_book;
    LinearLayout second_ll;

    public TypeItemSearchCarHolder(View itemView) {
        super(itemView);
        //推荐大家在Holder类中进行find
//            tv_item = (TextView) itemView.findViewById(R.id.tv_item);
        face_im=(RoundImageView)itemView.findViewById(R.id.face_im);
        g_role_tv=(TextView)itemView.findViewById(R.id.g_role_tv);
        g_name_tv=(TextView)itemView.findViewById(R.id.g_name_tv);
        up_time=(TextView)itemView.findViewById(R.id.up_time);
        order_num_tv=(TextView)itemView.findViewById(R.id.order_num_tv);
        tv_register_time=(TextView)itemView.findViewById(R.id.tv_register_time);
        tv_starting=(TextView)itemView.findViewById(R.id.tv_starting);
        tv_ending=(TextView)itemView.findViewById(R.id.tv_ending);
        tv_distance=(TextView)itemView.findViewById(R.id.tv_distance);
        tv_cars_name=(TextView)itemView.findViewById(R.id.tv_cars_name);
        tv_cars_go_time=(TextView)itemView.findViewById(R.id.tv_cars_go_time);
        turn_ll=(LinearLayout)itemView.findViewById(R.id.turn_ll);
        tv_book=(TextView)itemView.findViewById(R.id.tv_book);
        second_ll = (LinearLayout) itemView.findViewById(R.id.second_ll);
    }

    @Override
    public void bindHolder(GoodsBean.DataBean baseGood) {

    }


    @Override
    public void bindHolder(Context context, GoodsBean.DataBean mGoodsBeans) {
        g_name_tv.setText(mGoodsBeans.getHuozhu_name());//名字
        //时间格式化
        int created = mGoodsBeans.getCreated();
        String s = DateUtils.convertTimeToFormat(created);
        up_time.setText(s);//发布时间
        tv_starting.setText(mGoodsBeans.getYuan_dizhi());//出发地
        tv_ending.setText(mGoodsBeans.getFahuo_dizhi());//目的地
        tv_cars_name.setText(mGoodsBeans.getChe_class());//车型
    }



}
