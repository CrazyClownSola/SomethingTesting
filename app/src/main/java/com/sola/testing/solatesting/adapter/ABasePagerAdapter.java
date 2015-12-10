package com.sola.testing.solatesting.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Description:
 * <p ViewPager的基础适配器类/>
 * author: Sola
 * 2015/5/14
 */
public abstract class ABasePagerAdapter extends PagerAdapter {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    /**
     * 缓存的布局列表
     */
    protected View[] viewList;

    /**
     * 用于区分进入destroy方法的入口
     * true为notifyDataSetChanged()方法被调用之后
     * false为界面滑动的时候调用触发
     */
    private boolean isDestoryAvailable;

    /**
     * 判断用户的refresh操作是刷新全部数据还是只是update部分数据
     */
    private boolean isRefreshAll;

    /**
     * 调整Adapter的数据加载模式
     */
    private final AdapterLoadMode adapterMode;

    // ===========================================================
    // Constructors
    // ===========================================================

    public ABasePagerAdapter() {
        this(AdapterLoadMode.DESTORY, null);
    }

    public ABasePagerAdapter(List<View> list) {
        this(AdapterLoadMode.DESTORY, list);
    }

    public ABasePagerAdapter(AdapterLoadMode mode) {
        this(mode, null);
    }

    public ABasePagerAdapter(AdapterLoadMode mode, List<View> list) {
        adapterMode = mode;
        listCopy(list);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
    @Override
    public int getCount() {
        return viewList.length;
    }

    @Override
    public int getItemPosition(Object object) {
        //通常情况下，调用notifyDataSetChanged方法会让ViewPager
        // 通过Adapter的getItemPosition方法查询一遍所有child view，
        // 这种情况下，所有child view位置均为POSITION_NONE，
        // 表示所有的child view都不存在，ViewPager会调用destroyItem方法销毁，
        // 并且重新生成，加大系统开销，并在一些复杂情况下导致逻辑问题。
        // 特别是对于只是希望更新child view内容的时候，造成了完全不必要的开销
        isDestoryAvailable = true;
        //这个标志是为了让adapter调用notifyDataSetChanged()方法的时候主动触发destory方法
        return POSITION_NONE;
    }

    /**
     * 如果外部调用的时候代码有执行ViewPager.setOffscreenPageLimit(int)，那么该方法的调用次数==int的值
     * notifyDataSetChanged()之后也会调用该方法
     * 如果外部并没有执行ViewPager.setOffscreenPageLimit(int)方法,那么该方法会根据界面滑动复数次的频繁调用，
     * 类似缓存加载模式
     *
     * @param container -- 对应的是Viewpager
     * @param position  -- 对应index
     * @return view
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        Log.d("Sola", "instantiate position[" + position + "]");
        View view;
        if (adapterMode == AdapterLoadMode.CACHE) {
            if (container.getChildAt(position) == null) {
                // 初始化判定，在数据itemList固定的情况下，View的构建只进行一次
                view = viewList[position];
                container.addView(view);
            } else {
                view = container.getChildAt(position);
            }
        } else {
            view = viewList[position];
            if (view != null) {
//                Log.d("Sola", "in instantiate position[" + position + "]");
                container.addView(view);
            }
        }
        return view;
    }

    /**
     * 调用次数和instantiateItem()规则相同
     *
     * @param container ViewPager
     * @param position  标志位
     * @param object    没在意这是啥
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        Log.d("Sola", "destory position[" + position + "]");
        if (isDestoryAvailable && container.getChildCount() != 0) {
            if (isRefreshAll) {
                //这边这个条件控制是确认界面是在refreshAll()的情况下进入该方法，将整个界面clear刷新
                container.removeAllViews();
            } else {
                if (container.getChildAt(position) != viewList[position])
                    container.removeViewAt(position);
            }
            isDestoryAvailable = false;
        } else if (!isDestoryAvailable) {
            if (adapterMode == AdapterLoadMode.DESTORY)
                container.removeView(viewList[position]);
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }
    // ===========================================================
    // Methods
    // ===========================================================

    protected void listCopy(List<View> list) {
        if (list != null) {
            int listSize = list.size();
            if (listSize == 0)
                viewList = new View[0];
            else {
                viewList = new View[listSize];
                System.arraycopy(list.toArray(), 0, viewList, 0, listSize);
            }
        } else viewList = new View[0];
    }

    /**
     * 刷新全部，整个界面adapter中的数组发生变化的时候调用该方法
     *
     * @param list 存入的新数据
     */
    protected void refreshAll(List<View> list) {
        isRefreshAll = true;
        listCopy(list);
        notifyDataSetChanged();
    }

    protected void refresh(View item, int position) {
        isRefreshAll = false;
        if (position < getCount() && position >= 0) {
            viewList[position] = item;
            notifyDataSetChanged();
        }
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    public enum AdapterLoadMode {
        //Viewpager会一次性加载全部的View进入，这样界面呈现起来不会吨卡,但是数据量过大的时候容易出问题
        CACHE,
        // Viewpager中只缓存部分View，具体缓存的数量根据viewpager.setOffscreenPageLimit()来决定，
        // 此种模式会导致界面加载吨卡,推荐Destory模式+控制setOffscreenPageLimit()数量来调整
        DESTORY
    }

}
