package com.sola.testing.solatesting.view.pull_to_refresh.interfaces;

import com.sola.testing.solatesting.view.pull_to_refresh.PullToRefreshContainer;

/**
 * Description:
 * <p/>
 * author: Sola
 * 2015/10/16
 */
public interface IPullToRefreshUIHandler {
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
     * When the content view has reached top and refresh has been completed, view will be reset.
     */
    void onUIReset(PullToRefreshContainer frame);

    /**
     * prepare for loading
     */
    void onUIRefreshPrepare(PullToRefreshContainer frame);

    /**
     * perform refreshing UI
     */
    void onUIRefreshBegin(PullToRefreshContainer frame);

    /**
     * perform UI after refresh
     */
    void onUIRefreshComplete(PullToRefreshContainer frame);

    void onUIPositionChange(PullToRefreshContainer frame, boolean isUnderTouch, byte status);

}
