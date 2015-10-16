package com.sola.testing.solatesting.view.pull_to_refresh.interfaces;

import android.view.View;

import com.sola.testing.solatesting.view.pull_to_refresh.PullToRefreshContainer;

/**
 * Description:
 * <p/>
 * author: Sola
 * 2015/10/16
 */
public interface IPullToRefreshHandler {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    /**
     * Check can do refresh or not. For example the content is empty or the first child is in view.
     * <p/>
     * {@link in.srain.cube.views.ptr.PtrDefaultHandler#checkContentCanBePulledDown}
     */
    boolean checkCanDoRefresh(final PullToRefreshContainer frame, final View content, final View header);

    /**
     * When refresh begin
     *
     * @param frame
     */
    void onRefreshBegin(final PullToRefreshContainer frame);
}
