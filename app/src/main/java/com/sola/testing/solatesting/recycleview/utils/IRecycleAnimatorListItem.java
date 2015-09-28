package com.sola.testing.solatesting.recycleview.utils;

import android.view.View;

import com.nineoldandroids.animation.AnimatorSet;

/**
 * Description:
 * <p/>
 * author: Sola
 * 2015/9/25
 */
public interface IRecycleAnimatorListItem extends IRecycleListItem {
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
     * 获取每一项加载的时候的动画效果
     *
     * @param view
     * @return
     */
    public AnimatorSet getAnimatorSet(View view);

}
