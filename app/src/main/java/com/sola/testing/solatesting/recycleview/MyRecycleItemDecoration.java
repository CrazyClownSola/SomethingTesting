package com.sola.testing.solatesting.recycleview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Description:
 * <p/>
 * author: Sola
 * 2015/10/9
 */
public class MyRecycleItemDecoration extends RecyclerView.ItemDecoration {
    // ===========================================================
    // Constants
    // ===========================================================

//    android.support.v7.widget.RecyclerView.Ite

    // ===========================================================
    // Fields
    // ===========================================================

    final Drawable mDrawable;

    // ===========================================================
    // Constructors
    // ===========================================================

    public MyRecycleItemDecoration(Drawable mDrawable) {
        this.mDrawable = mDrawable;
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    /**
     * 在itemView绘制完成之前调用，也就是说此方法draw出来的效果将会在itemView的下面
     * 在RecyclerView的onDraw方法中被调用
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    /**
     * 与onDraw相反，draw出来的效果将叠加在itemView的上面
     * 在RecyclerView的draw方法中被调用
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
//        super.onDrawOver(c, parent, state);
        if (mDrawable == null)
            super.onDrawOver(c, parent, state);
        else {
            final int left = parent.getPaddingLeft();
            final int right = parent.getWidth() - parent.getPaddingRight();
            final int childCount = parent.getChildCount();

            for (int i=1; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                final int size = mDrawable.getIntrinsicHeight();
                final int top = child.getTop() - params.topMargin;
                final int bottom = top + size;
                mDrawable.setBounds(left, top, right, bottom);
                mDrawable.draw(c);
            }
        }
//        mDrawable.getIntrinsicHeight()
    }

    /**
     * 计算通过配置outRect来设置itemView的inset边界，相当于设置itemView的margin
     *
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (mDrawable == null) return;
        if (parent.getChildAdapterPosition(view) < 1) return;
//        InsetDrawable d = (InsetDrawable) mDrawable;
//        int top = d.getIntrinsicHeight();
//        int left = d.getIntrinsicWidth();
//        if (getOrientation(parent) == LinearLayoutManager.VERTICAL)
        outRect.top = mDrawable.getIntrinsicHeight();
//        outRect.left = d.getIntrinsicWidth();

//        InsetDrawable d;
//        d.getIntrinsicHeight()
//        outRect.right
//        else outRect.left = mDivider.getIntrinsicWidth();
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
