package com.sola.testing.solatesting.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.view.ViewHelper;
import com.sola.testing.solatesting.recycleview.utils.IRecycleAnimatorListItem;

import java.util.List;

/**
 * Description:
 * <p/>
 * author: Sola
 * 2015/9/28
 */
public class RecycleAnimatorAdapter<Param extends IRecycleAnimatorListItem>
        extends RecycleViewAdapter<Param> {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private int mLastPosition = -1;

    private boolean isFirstOnly = true;

    // ===========================================================
    // Constructors
    // ===========================================================

    public RecycleAnimatorAdapter(Context mContext, List<Param> cacheList) {
        super(mContext, cacheList);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================
    public void setFirstOnly(boolean isFirstOnly) {
        this.isFirstOnly = isFirstOnly;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        AnimatorSet animSet = cacheList.get(position).getAnimatorSet(holder.itemView);
        if (animSet != null) {
            if (!isFirstOnly || position > mLastPosition) {
                animSet.start();
                mLastPosition = position;
            } else {
                clear(holder.itemView);
            }
        }
    }

    // ===========================================================
    // Methods
    // ===========================================================

    public void clear(View v) {
        ViewHelper.setAlpha(v, 1);
        ViewHelper.setScaleY(v, 1);
        ViewHelper.setScaleX(v, 1);
        ViewHelper.setTranslationY(v, 0);
        ViewHelper.setTranslationX(v, 0);
        ViewHelper.setRotation(v, 0);
        ViewHelper.setRotationY(v, 0);
        ViewHelper.setRotationX(v, 0);
        v.setPivotY(v.getMeasuredHeight() / 2);
        ViewHelper.setPivotX(v, v.getMeasuredWidth() / 2);
    }
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
