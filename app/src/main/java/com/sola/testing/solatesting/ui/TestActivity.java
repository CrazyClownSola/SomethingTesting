package com.sola.testing.solatesting.ui;

import android.support.v7.app.AppCompatActivity;

import com.sola.testing.solatesting.R;
import com.sola.testing.solatesting.test.ScrollerTesting;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Description:
 * <p/>
 * author: Sola
 * 2015/10/16
 */
@EActivity(R.layout.activity_test)
public class TestActivity extends AppCompatActivity {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    @ViewById
    ScrollerTesting id_scroller_test;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    @Click
    public void id_btn_test() {
        id_scroller_test.startScroll();
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
