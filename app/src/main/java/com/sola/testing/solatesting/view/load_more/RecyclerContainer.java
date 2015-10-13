package com.sola.testing.solatesting.view.load_more;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Description:
 * <p/>
 * author: Sola
 * 2015/10/13
 */
public class RecyclerContainer extends RecycleContainerBase {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================
    RecyclerView mRecyclerView;

    // ===========================================================
    // Constructors
    // ===========================================================
    public RecyclerContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerContainer(Context context) {
        super(context);
    }
    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
    @Override
    protected RecyclerView retrieveRecycleView() {
        if (getChildCount() > 1) {
            throw new NullPointerException("");
        }
        mRecyclerView = (RecyclerView) getChildAt(0);
        return mRecyclerView;
    }
    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
