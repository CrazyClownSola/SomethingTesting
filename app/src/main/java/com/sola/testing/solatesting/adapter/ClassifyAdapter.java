package com.sola.testing.solatesting.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;


import com.sola.testing.solatesting.IListItem;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by 禄骥
 * 2015/6/2.
 */
public class ClassifyAdapter<Target extends IListItem> extends ASimpleAdapter<Target> {

    protected WeakReference<Context> mContext;

    public ClassifyAdapter(Context context, List<Target> iListItems) {
        super(iListItems);
        mContext = new WeakReference<>(context);
    }

    /**
     * @param position    需要显示的布局在数组中的位置
     * @param convertView 传入的容器废弃中所废弃的布局
     * @param parent      适配器的容器布局
     * @return 返回对应布局
     * @TODO 这里需要对这个类进行再一次的优化，对于数组内部数组类型不相同的情况下，如何减少消耗的问题，有待再实现
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return ((IListItem) getItem(position)).getView(mContext.get(), convertView, parent);
    }

}
