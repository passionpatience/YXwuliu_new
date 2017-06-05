package eo.cn.yxwuliu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import eo.cn.yxwuliu.R;
import eo.cn.yxwuliu.bean.GoodsBean;
import eo.cn.yxwuliu.holder.TypeItemSearchGoodHolder;

/**
 * 这是查询货源的适配器
 * Created by kebi on 2017/5/30.
 */

public class SearchGoodsAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<T> data;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public SearchGoodsAdapter(List<T> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TypeItemSearchGoodHolder(mLayoutInflater.inflate(R.layout.item_search_good,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.e("数据", "onBindViewHolder: "+data.get(position) );
        ((TypeItemSearchGoodHolder)holder).bindHolder(mContext,(GoodsBean.DataBean) data.get(position) );
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
