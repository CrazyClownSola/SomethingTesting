package com.sola.testing.solatesting.ui;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sola.testing.solatesting.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EViewGroup;

/**
 * Description:
 * <p/>
 * author: Sola
 * 2015/8/26
 */
@EActivity(R.layout.activity_view_animation)
public class RxJavaActivity extends AppCompatActivity {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    View id_test;

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

    @AfterViews
    public void afterViews() {
        id_test.setOnClickListener(view -> {

        });
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
