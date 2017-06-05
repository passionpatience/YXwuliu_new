package eo.cn.yxwuliu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import eo.cn.yxwuliu.R;
import eo.cn.yxwuliu.bean.GoodsBean;
import eo.cn.yxwuliu.holder.TypeItemHomeHolder;


/**
 * @author jk
 * @time 2017/5/29  11:12
 * @desc ${TODD}
 */
public class HomeAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<T> datas;

    public HomeAdapter(Context context, List<T> datas) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
        this.datas = datas;

    }

    //创建布局
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new TypeItemHomeHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    //绑定数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TypeItemHomeHolder) holder).bindHolder(mContext, (GoodsBean.DataBean) datas.get(position));//绑定数据

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    //    private class MyViewHolder extends RecyclerView.ViewHolder {
    //
    //        private final TextView TvStart;
    //
    //        public MyViewHolder(View itemView) {
    //            super(itemView);
    //
    //            //找控件
    //            TvStart = (TextView) itemView.findViewById(R.id.tv_starting);
    //        }
    //    }
}
