package com.sola.testing.solatesting.view.load_more.interfaces;

import com.sola.testing.solatesting.view.load_more.RecycleLoadMoreContainerBase;

/**
 * Description:
 * <p/>
 * author: Sola
 * 2015/10/13
 */
public interface IRecycleLoadMoreUIHandler {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================
    void onLoading(IRecycleLoadMoreContainer container);

    void onLoadFinish(IRecycleLoadMoreContainer container, boolean empty, boolean hasMore);

    void onWaitToLoadMore(IRecycleLoadMoreContainer container);

    void onLoadError(IRecycleLoadMoreContainer container, int errorCode, String errorMessage);
}
