package com.sola.testing.solatesting.adapter;

import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 禄骥
 * 2015/6/2.
 */
public abstract class ASimpleAdapter<Param> extends BaseAdapter {

    protected List<Param> cacheList;

    public ASimpleAdapter(List<Param> params) {
        refreshList(params);
    }

    public void refreshList(List<Param> params) {
        if (params == null || params.size() == 0) {
            cacheList = new ArrayList<>();
        } else {
            if (cacheList == null)
                cacheList = new ArrayList<>();
            cacheList.clear();
            cacheList.addAll(params);
        }
    }

    @Override
    public int getCount() {
        return cacheList == null ? 0 : cacheList.size();
    }

    @Override
    public Object getItem(int position) {
        return getCount() == 0 ? null : cacheList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}
