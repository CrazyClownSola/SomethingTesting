package com.sola.testing.solatesting.view;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sola.testing.solatesting.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Description:
 * <p/>
 * author: Sola
 * 2015/9/28
 */
@EViewGroup(R.layout.list_item_test_item_dto)
public class TestItemView extends LinearLayout {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    @ViewById
    public TextView id_text_test;

    // ===========================================================
    // Constructors
    // ===========================================================

    public TestItemView(Context context) {
        super(context);
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

}
