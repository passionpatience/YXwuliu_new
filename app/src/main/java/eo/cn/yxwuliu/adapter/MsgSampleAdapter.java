package eo.cn.yxwuliu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import eo.cn.yxwuliu.R;


/**
 * @author jk
 * @time 2017/5/18  11:01
 * @desc ${TODD}
 */
public class MsgSampleAdapter extends RecyclerView.Adapter {

    private ImageView mViewById;
    private TextView mTextview;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item, null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder) {
            mViewById.setImageResource(R.drawable.home_shopcar);
            mTextview.setText("系统消息");
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        public ItemViewHolder(View itemView) {
            super(itemView);
            mViewById = (ImageView) itemView.findViewById(R.id.iv_head);
            mTextview = (TextView) itemView.findViewById(R.id.tv_item);

        }
    }
}
