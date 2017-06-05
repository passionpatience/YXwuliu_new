package eo.cn.yxwuliu.util;

import android.content.Context;

/**
 * Created by mk on 2017/3/27 0027.
 */

public class DenisityUtils {
    /**
     * dip 2 px
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue){
        return (int)(dipValue * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    /**
     * sp 2 px
     * @param context
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        return (int) (spValue * context.getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }

}
