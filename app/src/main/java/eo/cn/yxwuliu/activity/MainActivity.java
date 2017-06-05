package eo.cn.yxwuliu.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import eo.cn.yxwuliu.R;
import eo.cn.yxwuliu.fragment.CarSourceFragment;
import eo.cn.yxwuliu.fragment.GoodSourceFragment;
import eo.cn.yxwuliu.fragment.HomeFragment;
import eo.cn.yxwuliu.fragment.PersonFragment;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private static final String KEY_FRAGMENT_TAG = "fragment_tag";
    private static final String FRAGMENT_TAG_HOME = "fragment_home";
    private static final String FRAGMENT_TAG_Categorize = "fragment_categorize";
    private static final String FRAGMENT_TAG_PURCHASE_ORDER = "fragment_purchase_order";
    private static final String FRAGMENT_TAG_PERSON = "fragment_person";

    private HomeFragment mHomeFragment;
    private GoodSourceFragment mGoodSourceFragment;
    private CarSourceFragment mCarSourceFragment;
    private PersonFragment mPersonFragment;
    private String[] mFragmentTags = new String[]{FRAGMENT_TAG_HOME, FRAGMENT_TAG_Categorize, FRAGMENT_TAG_PURCHASE_ORDER, FRAGMENT_TAG_PERSON};
    private String mFragmentCurrentTag = FRAGMENT_TAG_HOME;
    private LinearLayout[] mLayouts = null;

    @BindView(R.id.img_home)
    ImageView imgHome;
    @BindView(R.id.txt_home)
    TextView txtHome;
    @BindView(R.id.layout_home)
    LinearLayout mHomeLayout;
    @BindView(R.id.layout_my_assert)
    LinearLayout mPurchaseOrderLayout;
    @BindView(R.id.layout_product)
    LinearLayout mCategorizeLayout;
    @BindView(R.id.layout_more)
    LinearLayout mMoreLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        if (savedInstanceState != null){
            Log.e("fragment","恢复fragment"+KEY_FRAGMENT_TAG);
            restoreFragments();//恢复fragment
            mFragmentCurrentTag = savedInstanceState.getString(KEY_FRAGMENT_TAG);
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        setListener();

    }

    private void setListener() {
        for (int i = 0; i < mLayouts.length; i++) {
            mLayouts[i].setOnClickListener(this);
        }
        if (TextUtils.equals(FRAGMENT_TAG_HOME, mFragmentCurrentTag)) {
            mHomeLayout.performClick();
        } else if (TextUtils.equals(FRAGMENT_TAG_Categorize, mFragmentCurrentTag)) {
            mCategorizeLayout.performClick();
        } else if (TextUtils.equals(FRAGMENT_TAG_PURCHASE_ORDER, mFragmentCurrentTag)) {
            mPurchaseOrderLayout.performClick();
        } else if (TextUtils.equals(FRAGMENT_TAG_PERSON, mFragmentCurrentTag)) {
            mMoreLayout.performClick();
        }
    }

    private void initData() {
        mLayouts = new LinearLayout[]{
                mHomeLayout, mCategorizeLayout, mPurchaseOrderLayout, mMoreLayout
        };
    }
    //这是测试

    /**
     * 恢复fragment
     */
    private void restoreFragments() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        for (int i = 0; i < mFragmentTags.length; i++) {
            Fragment fragment = manager.findFragmentByTag(mFragmentTags[i]);
            if(fragment != null){
                if (fragment instanceof HomeFragment) {
                    mHomeFragment = (HomeFragment)fragment;
                } else if (fragment instanceof CarSourceFragment) {
                    mCarSourceFragment = (CarSourceFragment)fragment;
                } else if (fragment instanceof GoodSourceFragment) {
                    mGoodSourceFragment = (GoodSourceFragment)fragment;
                } else if (fragment instanceof PersonFragment) {
                    mPersonFragment = (PersonFragment)fragment;
                }
                transaction.hide(fragment);
            }
        }
        transaction.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_FRAGMENT_TAG, mFragmentCurrentTag);
        super.onSaveInstanceState(outState);
    }
    //进入首页
    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putString("PHONE", phone);
//        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        onTabSelect((LinearLayout)v);
    }

    /**
     * 切换tab页
     * @param itemLayout
     */
    public void onTabSelect(LinearLayout itemLayout) {
        int id = itemLayout.getId();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hideFragments(manager, transaction);

        //下面图标颜色变化
        for (int i = 0; i < mLayouts.length; i++) {
            mLayouts[i].setSelected(false);
        }
        itemLayout.setSelected(true);

        if (id == R.id.layout_home) {
            selectedFragment(transaction, mHomeFragment, HomeFragment.class, FRAGMENT_TAG_HOME);
        } else if (id == R.id.layout_product) {
            selectedFragment(transaction, mGoodSourceFragment, GoodSourceFragment.class, FRAGMENT_TAG_Categorize);
        } else if (id == R.id.layout_my_assert) {
            selectedFragment(transaction, mCarSourceFragment, CarSourceFragment.class, FRAGMENT_TAG_PURCHASE_ORDER);
        } else if (id == R.id.layout_more) {
            selectedFragment(transaction, mPersonFragment, PersonFragment.class, FRAGMENT_TAG_PERSON);
        }
        transaction.commit();
    }
    /**
     * 先全部隐藏
     * @param fragmentManager
     * @param transaction
     */
    private void hideFragments(FragmentManager fragmentManager, FragmentTransaction transaction) {
        for (int i = 0; i < mFragmentTags.length; i++) {
            Fragment fragment = fragmentManager.findFragmentByTag(mFragmentTags[i]);
            if (fragment != null && fragment.isVisible()) {
                transaction.hide(fragment);
            }
        }
    }
    //将fragment与一些属性值进行绑定！比如名字等
    private void selectedFragment(FragmentTransaction transaction, Fragment fragment, Class<?> clazz, String tag) {
        mFragmentCurrentTag = tag;
        if (fragment == null) {
            try {
                //通过反射获得fragment
                Method newInstanceMethod = clazz.getDeclaredMethod("newInstance");
                fragment = (Fragment) newInstanceMethod.invoke(null);
            } catch (Exception ex)   {
                ex.printStackTrace();
            }
            transaction.add(R.id.fragment_container, fragment, tag);
            if(tag.equals(FRAGMENT_TAG_HOME)){
                mHomeFragment = (HomeFragment) fragment;
            }else if(tag.equals(FRAGMENT_TAG_Categorize)){
                mGoodSourceFragment = (GoodSourceFragment) fragment;
            }else if(tag.equals(FRAGMENT_TAG_PURCHASE_ORDER)){
                mCarSourceFragment = (CarSourceFragment) fragment;
            }else if(tag.equals(FRAGMENT_TAG_PERSON)){
                mPersonFragment = (PersonFragment) fragment;
            }
        }
        transaction.show(fragment);
    }
}
