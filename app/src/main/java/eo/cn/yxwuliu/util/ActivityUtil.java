package eo.cn.yxwuliu.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/4/27.
 */

public class ActivityUtil {

    //跳转
    public static void StartActivity(Context context, Class clz,boolean isFinish) {
        Intent intent = new Intent(context, clz);
        context.startActivity(intent);
        if (isFinish){
            ((Activity)context).finish();
        }
    }
    //提示消息
    public static void ShowTip(Context c,String str){
        Toast.makeText(c, str, Toast.LENGTH_SHORT).show();
    }
}
