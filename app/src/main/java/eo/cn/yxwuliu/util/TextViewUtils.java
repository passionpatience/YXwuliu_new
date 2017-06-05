package eo.cn.yxwuliu.util;

import android.graphics.drawable.Drawable;
import android.widget.TextView;


/**
 * Created by mk on 2017/3/27 0027.
 */

public class TextViewUtils {

    public static void setLeftImage(TextView textView, int resId, int padding){
        if(textView != null){
            Drawable drawable = textView.getContext().getResources().getDrawable(resId);
            //为Drawable指定一个边界矩形。这是drawable在draw（）方法被调用时绘制的地方
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawablePadding(DenisityUtils.dip2px(textView.getContext(),padding));
            textView.setCompoundDrawables(drawable,null, null, null);
        }
    }

    /**
     * 设置右侧图片
     * @param textView
     * @param resId
     * @param padding
     */
    public static void setRightImage(TextView textView, int resId, float padding) {
        if (textView != null) {
            Drawable drawable = textView.getContext().getResources().getDrawable(resId);
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            }
            textView.setCompoundDrawablePadding(DenisityUtils.dip2px(textView.getContext(), padding));
            textView.setCompoundDrawables(null, null, drawable, null);
        }
    }
}
