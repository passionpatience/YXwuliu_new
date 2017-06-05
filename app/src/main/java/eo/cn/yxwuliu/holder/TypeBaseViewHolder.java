package eo.cn.yxwuliu.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by mk on 2017/4/6.
 */

public abstract class TypeBaseViewHolder<T> extends RecyclerView.ViewHolder{

    public TypeBaseViewHolder(View itemView) {
        super(itemView);
    }
    public abstract void bindHolder(T baseGood);
    public abstract void bindHolder(Context context,T baseGood);

}
