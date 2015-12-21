package com.sola.testing.solatesting.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by 禄骥
 * 2015/7/3.
 * 用于Fragment和ViewPager配合的适配器
 */
public abstract class AFragmentStatePagerAdapter<T> extends FragmentStatePagerAdapter {

    private static final Field mActiveField;

    /**
     * 由于这里的Fragment会和固定的Tab进行匹配，这个是匹配关系的Tab
     */
    private T[] cacheTabs;
    private Fragment[] cacheFragments;


    static {
        Field f = null;
        Class<?> c;
        try {
            c = Class.forName("android.support.v4.app.FragmentManagerImpl");
            f = c.getDeclaredField("mActive");
            f.setAccessible(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        mActiveField = f;
    }

    public AFragmentStatePagerAdapter(FragmentManager fm, T[] tab) {
        super(fm);
        cacheTabs = tab;
        cacheFragments = new Fragment[cacheTabs.length];// 验证了tabs和fragments之间的关系是相互的
        try {
            ArrayList<Fragment> fragments = (ArrayList<Fragment>) mActiveField.get(fm);
            if (fragments != null) {
                for (Fragment f : fragments) {
                    setFragment(getRelation(f), f);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * 这种方式的好处是动态进行对Fragment的加载
     *
     * @param position 显示的位置
     * @return 返回该显示的页面
     */
    @Override
    public Fragment getItem(int position) {
        if (cacheFragments[position] == null) {
            cacheFragments[position] = initFragment(cacheTabs[position]);
        }
        return cacheFragments[position];
    }

    @Override
    public int getCount() {
        return cacheFragments == null ? 0 : cacheFragments.length;
    }

    public void refreshItem(int position, T tab) {
        if (position > -1 && position < getCount()) {
            cacheFragments[position] = null;
            cacheTabs[position] = tab;
        }
    }

    /**
     * 将对应Tab的Fragment放入合适的位置
     *
     * @param tab      fragment对应的tab位置
     * @param fragment 显示对象
     */
    public void setFragment(T tab, Fragment fragment) {
        for (int i = 0, len = cacheTabs.length; i < len; i++) {
            if (cacheTabs[i] == tab) {
                cacheFragments[i] = fragment;
            }
        }
    }

    /**
     * 根据传入的fragment返回和他相对应的tab值
     *
     * @param f 这个是针对于获取到内存中的fragment的时候需要获取对应关系
     * @return 返回对应关系
     */
    abstract T getRelation(Fragment f);

    /**
     * 根据传入的tab值返回和他相对应的fragment，用于在fragment找不到的情况，或者初始化的时候
     *
     * @param tab tab关系值
     * @return 返回初始化的fragment实例
     */
    abstract Fragment initFragment(T tab);
}