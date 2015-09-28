package com.sola.testing.solatesting.ui;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.nineoldandroids.view.ViewHelper;
import com.sola.testing.solatesting.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

/**
 * Description:
 * <p/>
 * author: Sola
 * 2015/8/26
 */
@EActivity(R.layout.activity_new_main)
public class MainActivity extends FragmentActivity {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

//    MaterialRippleLayout rippleLayout;

//    Orml
//
//    @ViewById
//    RecyclerView id_recycler_view;

    @ViewById
    RelativeLayout container;

    @ViewById
    TextView one;
    @ViewById
    TextView two;
    @ViewById
    TextView three;
    @ViewById
    TextView four;

    int totalHeight;
    int totalWidth;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @AfterViews
    public void afterViews() {
        ViewTreeObserver vto = one.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                one.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                totalHeight = one.getHeight();
                totalWidth = one.getHeight();
                viewChange();
//                ViewH
            }
        });

    }

//    onView

    @UiThread
    public void viewChange() {
//        int deltaX =
        container.setVisibility(View.VISIBLE);
        ViewHelper.setTranslationX(one, totalWidth / 8);
        ViewHelper.setTranslationY(one, -totalHeight / 4);
        ViewHelper.setTranslationX(two, -totalWidth / 8);
        ViewHelper.setTranslationY(two, totalHeight / 4);

        ViewHelper.setTranslationX(three, totalWidth / 8);
        ViewHelper.setTranslationY(three, -totalHeight / 4);
        ViewHelper.setTranslationX(four, -totalWidth / 8);
        ViewHelper.setTranslationY(four, totalHeight / 4);
//        ;
//        container.measure(container.getMeasuredWidth(), container.getMeasuredHeight() + 2 * totalHeight);
//        container.requestLayout();
        container.getLayoutParams().height = container.getLayoutParams().height + 2 * totalHeight;
//        ViewHelper.setTranslationX(one, totalWidth / 2);
//        ViewHelper.setTranslationX(one, totalWidth / 2);
    }

    // ===========================================================
    // Methods
    // ===========================================================

//    @Click
//    public void id_btn_start_view_animation() {
//        switchActivity(ViewAnimationActivity_.class, 0, 0);
//    }
//
//    private void switchActivity(Class<?> activity, int enterAnim, int exitAnim) {
//        Intent intent = new Intent();
//        intent.setClass(this, activity);
//        startActivity(intent);
//
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ECLAIR)
//            overridePendingTransition(enterAnim, exitAnim);
//
//    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
