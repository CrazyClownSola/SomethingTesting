package com.sola.testing.solatesting.adapter;

import android.content.Context;
import android.view.View;


import com.sola.testing.solatesting.IListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * <p 此种适配器只支持Pager中数据全部刷新的情况，对于只是单个界面数据刷新的情况使用这个会导致多余的消耗/>
 * author: Sola
 * 2015/5/14
 */
public class ClassifyPagerAdapter<Target extends IListItem> extends ABasePagerAdapter {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    /**
     * 缓存的数据List,采用集合的方式进行
     */
    protected List<Target> itemList;


    // ===========================================================
    // Constructors
    // ===========================================================
    public ClassifyPagerAdapter(Context context, List<Target> list) {
        itemList = list;
        listCopy(getViewList(context, list));
    }

    public ClassifyPagerAdapter(Context context, AdapterLoadMode mode, List<Target> list) {
        super(mode);
        itemList = list;
        listCopy(getViewList(context, list));
    }


    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================


    // ===========================================================
    // Methods
    // ===========================================================

    private List<View> getViewList(Context context, List<Target> list) {
        List<View> viewList;
        if (list != null && list.size() != 0) {
            viewList = new ArrayList<>();
            for (int i = 0, len = list.size(); i < len; i++) {
                viewList.add(list.get(i).getView(context, null, null));
            }
        } else {
            viewList = new ArrayList<>();
        }
        return viewList;
    }

    public void refreshAll(Context context, List<Target> list) {
        super.refreshAll(getViewList(context, list));
    }

    public Target getItem(int position) {
        return itemList.get(position);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
