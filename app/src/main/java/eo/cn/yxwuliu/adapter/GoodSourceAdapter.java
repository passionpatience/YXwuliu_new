package eo.cn.yxwuliu.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 这个是查询的适配器，配合tablayout、viewpager、fragment使用
 * Created by kebi on 2017/5/30.
 */

public class GoodSourceAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private String[] titles;

    public GoodSourceAdapter(FragmentManager fm,List<Fragment> fragments,String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    /**
     *这个方法可以让notifyDataSetChanged的调用起作用
     * @param object
     * @return
     */
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
