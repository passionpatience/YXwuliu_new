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
 * 这个是货源界面的viewHolder
 * Created by kebi on 2017/5/30.
 */

public class TypeItemSearchGoodHolder extends TypeBaseViewHolder<GoodsBean.DataBean> {

   private RoundImageView face_im;
   private TextView g_role_tv;
   private TextView g_name_tv;
   private TextView release_num_tv;
   private TextView tv_register_time;
   private TextView tv_starting;
   private TextView tv_ending;
   private TextView tv_distance;
   private TextView tv_good_name_weight;
   private TextView tv_cars_go_need;
   private LinearLayout turn_ll;
   private TextView tv_book;
   private LinearLayout second_ll;
   private TextView g_time_tv;

    public TypeItemSearchGoodHolder(View itemView) {
        super(itemView);
        //推荐大家在Holder类中进行find
//            tv_item = (TextView) itemView.findViewById(R.id.tv_item);
        face_im=(RoundImageView)itemView.findViewById(R.id.face_im);
        g_role_tv=(TextView)itemView.findViewById(R.id.g_role_tv);
        g_name_tv=(TextView)itemView.findViewById(R.id.g_name_tv);
        release_num_tv=(TextView)itemView.findViewById(R.id.release_num_tv);
        tv_register_time=(TextView)itemView.findViewById(R.id.tv_register_time);
        g_time_tv=(TextView)itemView.findViewById(R.id.g_time_tv);//发布时间
        tv_starting=(TextView)itemView.findViewById(R.id.tv_starting);
        tv_ending=(TextView)itemView.findViewById(R.id.tv_ending);
        tv_distance=(TextView)itemView.findViewById(R.id.tv_distance);
        tv_good_name_weight=(TextView)itemView.findViewById(R.id.tv_good_name_weight);//货物重量
        tv_cars_go_need=(TextView)itemView.findViewById(R.id.tv_cars_go_need);//车型
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
        g_time_tv.setText(s);//发布时间
        tv_starting.setText(mGoodsBeans.getYuan_dizhi());//出发地
        tv_ending.setText(mGoodsBeans.getFahuo_dizhi());//目的地
        tv_cars_go_need.setText(mGoodsBeans.getChe_class());//车型
        tv_good_name_weight.setText(mGoodsBeans.getHuo_kg()+"");//货重
    }

}
