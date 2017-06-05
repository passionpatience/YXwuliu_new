package eo.cn.yxwuliu.util;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.Toast;

import com.lljjcoder.citypickerview.widget.CityPicker;

/**
 * Created by Administrator on 2017/5/11.
 */

public class CitySeclectUtil {
    private static CityPicker mCityPicker;

    public static void citySelect(final Context context, final TextView textView) {
        mCityPicker = new CityPicker.Builder(context).textSize(20)
                .titleTextColor("#000000")
                .backgroundPop(0xa0000000)
                .province("广东省")
                .city("广州市")
                .district("番禺区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .build();
        mCityPicker.show();
        mCityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                textView.setText(citySelected[0] + "." + citySelected[1] + "." +
                        citySelected[2]);
            }

            @Override
            public void onCancel() {
                Toast.makeText(context, "已取消", Toast.LENGTH_LONG).show();
            }
        });
    }
    public static void citySelect2(final Context context, final TextView textView) {
        mCityPicker = new CityPicker.Builder(context).textSize(20)
                .titleTextColor("#000000")
                .backgroundPop(0xa0000000)
                .province("广东省")
                .city("广州市")
                .district("番禺区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .build();
        mCityPicker.show();
        mCityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                textView.setText(citySelected[2]);
            }

            @Override
            public void onCancel() {
                Toast.makeText(context, "已取消", Toast.LENGTH_LONG).show();
            }
        });
    }

}
