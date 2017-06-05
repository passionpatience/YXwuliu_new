package eo.cn.yxwuliu.widgets;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eo.cn.yxwuliu.R;

/**
 * Created by Administrator on 2017/5/8.
 * 自定义对话框
 */

public class ProductTypeSelect extends Dialog {

    private GridView mGridView;
    private OnItemClickListener mOnItemClickListener;

    public ProductTypeSelect(Context context) {
        super(context);
    }

    public ProductTypeSelect(Context context, int themeResId) {
        super(context, themeResId);
    }

    private List<Map<String, Object>> mLists = new ArrayList<Map<String, Object>>();

    private String[] strs = {
            "设备",
            "矿产",
            "建材",
            "食品",
            "蔬菜",
            "生鲜",
            "药品",
            "化工",
            "木材",
            "家畜",
            "纺织品",
            "日用品",
            "电子电器",
            "农副产品",
            "其他类型"
    };

    private String type = "";//种类


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_type_dialog);
        initUI();
    }

    //接口回调
    public interface OnItemClickListener {
        void itemClick(View view, String type);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    private void initUI() {
        //数据的初始化
        for (int i = 0; i < strs.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("type", strs[i]);
            mLists.add(item);
        }
        mGridView = (GridView) findViewById(R.id.gv_dialog);
        SimpleAdapter adapter = new SimpleAdapter(getContext(), mLists, R.layout.item_gridview_dialog, new String[]{"type"}, new int[]{R.id.item_tv});
        mGridView.setAdapter(adapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                view.setSelected(true);
                type = strs[position];
                mOnItemClickListener.itemClick(view, type);
            }
        });
    }

}
