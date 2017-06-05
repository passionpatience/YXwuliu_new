package eo.cn.yxwuliu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import eo.cn.yxwuliu.R;
import eo.cn.yxwuliu.bean.GoodsBean;
import eo.cn.yxwuliu.holder.TypeItemSearchCarHolder;
import eo.cn.yxwuliu.holder.TypeItemSearchGoodHolder;

/**
 * 这是查询车源的适配器
 * Created by kebi on 2017/5/30.
 */

public class SearchCarAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //getItemType
    //getTypeCount
//    @Override
//    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
//    }

//    getView
    //缓存view如果为空
    //就会打气筒来一个view,然后将view赋值给缓存view,然后创建新的holder,找控件,setTag,
    //不为空
    //getTag ,就拿出holder
    //设置数据

    //准备holder
    private List<T> data;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private OnCarIcoClickListener onCarIcoClickListener;
    private OnCarItemClickListener onCarItemClickListener;
    private OnCarBookClickListener onCarBookClickListener;

    public SearchCarAdapter(List<T> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TypeItemSearchCarHolder(mLayoutInflater.inflate(R.layout.item_search_car,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((TypeItemSearchCarHolder)holder).bindHolder(mContext,(GoodsBean.DataBean) data.get(position) );
        holder.itemView.findViewById(R.id.face_im).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCarIcoClickListener.icoClick(v,position);
            }
        });
        holder.itemView.findViewById(R.id.second_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCarItemClickListener.itemClick(v,position);
            }
        });
        holder.itemView.findViewById(R.id.tv_book).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCarBookClickListener.bookClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * 当点击头像时
     */
    public interface OnCarIcoClickListener{
        void icoClick(View view,int position);
    }

    /**
     * 当点击简介时
     */
    public interface OnCarItemClickListener{
        void itemClick(View view,int position);
    }

    /**
     * 当点击预定时
     */
    public interface OnCarBookClickListener{
        void bookClick(View view,int position);
    }

    public void setOnClickListener(OnCarIcoClickListener onCarIcoClickListener){
        this.onCarIcoClickListener = onCarIcoClickListener;
    }

    public void setOnClickListener(OnCarItemClickListener onCarItemClickListener){
        this.onCarItemClickListener = onCarItemClickListener;
    }

    public void setOnClickListener(OnCarBookClickListener onCarBookClickListener){
        this.onCarBookClickListener = onCarBookClickListener;
    }

}
