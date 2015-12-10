package com.sola.testing.solatesting.test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Scroller;

/**
 * Description:
 * <p/>
 * author: Sola
 * 2015/10/16
 */
public class ScrollerTesting extends ViewGroup {


    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    final Scroller mScroller;

    View content;

    // ===========================================================
    // Constructors
    // ===========================================================
    public ScrollerTesting(Context context) {
        this(context, null);
    }

    public ScrollerTesting(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int viewCount = getChildCount();

        if (viewCount == 1) {
            content = getChildAt(0);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final MarginLayoutParams lp = (MarginLayoutParams) content.getLayoutParams();
//lp.
        final int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec,
                getPaddingLeft() + getPaddingRight() + lp.leftMargin + lp.rightMargin, lp.width);
        final int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec,
                getPaddingTop() + getPaddingBottom() + lp.topMargin, lp.height);

        content.measure(childWidthMeasureSpec, childHeightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d("Sola", "onLayout change[" + changed + "],l[" + l + "]," +
                "t[" + t + "],r[" + r + "],b[" + b + "]");

        int offsetX = 0;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();


        if (content != null) {
            MarginLayoutParams lp = (MarginLayoutParams) content.getLayoutParams();
            final int left = paddingLeft + lp.leftMargin;
            final int top = paddingTop + lp.topMargin + offsetX;
            final int right = left + content.getMeasuredWidth();
            final int bottom = top + content.getMeasuredHeight();

            content.layout(left, top, right, bottom);
        }
    }

    int mLastFlingY;

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            int y = mScroller.getCurrY();
            Log.d("Sola", "Scroller Y [" + y + "]");
            int deltaY = y - mLastFlingY;
//            content.scrollTo(0, deltaY);
            content.offsetTopAndBottom(deltaY);
//            content.offsetLeftAndRight(dex);
            mLastFlingY = y;
            invalidate();
//            mScroller.f
//            Animation
        }
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p != null && p instanceof LayoutParams;
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }
    // ===========================================================
    // Methods
    // ===========================================================

    public void startScroll() {
        mScroller.startScroll(0, 0, 0, 400, 1000);
        invalidate();
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    public static class LayoutParams extends MarginLayoutParams {

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        @SuppressWarnings({"unused"})
        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }
}
