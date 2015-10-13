package com.sola.testing.solatesting.view;

import android.content.Context;
import android.os.SystemClock;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Description:
 * <p/>
 * author: Sola
 * 2015/10/8
 */
@CoordinatorLayout.DefaultBehavior(EmptyView.Behavior.class)
public class EmptyView extends FrameLayout {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================
    public EmptyView(Context context) {
        super(context);
    }

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
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

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    public static class Behavior extends CoordinatorLayout.Behavior<EmptyView> {

        public Behavior() {
        }

        public Behavior(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        public boolean onInterceptTouchEvent(CoordinatorLayout parent, EmptyView child, MotionEvent ev) {
//            if (ev == null) {
//                long var15 = SystemClock.uptimeMillis();
//                ev = MotionEvent.obtain(var15, var15, 3, 0.0F, 0.0F, 0);
//            }
            switch (ev.getAction()) {

            }
            return super.onInterceptTouchEvent(parent, child, ev);
        }
    }

}
