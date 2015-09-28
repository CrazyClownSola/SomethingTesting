package com.sola.testing.solatesting.recycleview.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;

/**
 * Description:
 * <p/>
 * author: Sola
 * 2015/9/25
 */
public interface IRecycleListItem {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================


    /**
     * @param context 　控件组件
     */
    public View getView(Context context);

    public RecyclerView.ViewHolder getHolder(Context context);

    public void refreshView(RecyclerView.ViewHolder holder);

}
