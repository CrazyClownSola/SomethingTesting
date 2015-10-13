package com.sola.testing.solatesting.recycleview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Description:
 * <p 忠实于呈现Drawable本身的一种RecyclerView间隔布局的选择 />
 * author: Sola
 * 2015/10/9
 */
public class OriginalRecycleItemDecoration extends RecyclerView.ItemDecoration {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    /**
     * 在间隔中呈现的布局
     */
    final Drawable mDivider;


    // ===========================================================
    // Constructors
    // ===========================================================

    public OriginalRecycleItemDecoration(Drawable mDivider) {
        this.mDivider = mDivider;
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
     * 在此类中不会被实现
     *
     * @param c      画布
     * @param parent RecyclerView
     * @param state  状态
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    /**
     * 与onDraw相反，draw出来的效果将叠加在itemView的上面
     * 在RecyclerView的draw方法中被调用
     * 主要的界面逻辑在这里
     *
     * @param c      画布
     * @param parent RecyclerView
     * @param state  状态
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
//        state.
    }

    /**
     * 计算通过配置outRect来设置itemView的inset边界，相当于设置itemView的margin
     * 这部分没有特别理解里面的逻辑关系，具体这个outRect是怎么实现的有待研究
     *
     * @param outRect 需要配置的值
     * @param view    单项子布局
     * @param parent  RecyclerView
     * @param state   状态
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        // 在没有设置布局的情况下 或者 是列表中的第一项的时候，什么都不做
        if (mDivider == null || parent.getChildAdapterPosition(view) < 1)
            return;
        int orientation = getOrientation(parent);
        if (orientation == -1)// 判别出非Linearlayout布局向的情况
            return;
        if (orientation == LinearLayoutManager.VERTICAL)
            outRect.top = mDivider.getIntrinsicHeight();
        else
            outRect.left = mDivider.getIntrinsicWidth();
    }

    private int getOrientation(RecyclerView parent) {
         if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) parent.getLayoutManager()).getOrientation();
        }
        return -1;
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
