package com.sola.testing.solatesting.view.pull_to_refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.sola.testing.solatesting.view.pull_to_refresh.interfaces.IPullToRefreshContainer;
import com.sola.testing.solatesting.view.pull_to_refresh.interfaces.IPullToRefreshHandler;
import com.sola.testing.solatesting.view.pull_to_refresh.interfaces.IPullToRefreshUIHandler;

/**
 * Description:
 * <p/>
 * author: Sola
 * 2015/10/16
 */
public class PullToRefreshContainer extends ViewGroup implements IPullToRefreshContainer {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

    public PullToRefreshContainer(Context context) {
        super(context);
    }

    public PullToRefreshContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreshContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public void refreshComplete() {

    }

    @Override
    public void autoRefresh(boolean atOnce) {

    }

    @Override
    public void addPTRUIHandler(IPullToRefreshUIHandler handler) {

    }

    @Override
    public void setPTRHandler(IPullToRefreshHandler handler) {

    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
