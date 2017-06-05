package eo.cn.yxwuliu.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import eo.cn.yxwuliu.R;


/**
 * @author jk
 * @time 2017/5/17  9:40
 * @desc ${TODD}
 */
public class DetailYunDan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yundan_detail);
    }

    public void back(View view){
        finish();
    }
}
