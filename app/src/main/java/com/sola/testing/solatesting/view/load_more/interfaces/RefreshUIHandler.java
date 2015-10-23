package com.sola.testing.solatesting.view.load_more.interfaces;

import com.sola.testing.solatesting.view.load_more.RecycleLoadMoreContainerBase;

/**
 * Description:
 * <p/>
 * author: Sola
 * 2015/10/13
 */
public interface RefreshUIHandler {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    void onUIReset(RecycleLoadMoreContainerBase container);

    void onUIRefreshPrepare(RecycleLoadMoreContainerBase container);

    void onUIRefreshBegin(RecycleLoadMoreContainerBase container);

    void onUIRefreshComplete(RecycleLoadMoreContainerBase container);

}
