package com.sola.testing.solatesting.view.load_more.interfaces;

import com.sola.testing.solatesting.view.load_more.RecycleContainerBase;

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

    void onUIReset(RecycleContainerBase container);

    void onUIRefreshPrepare(RecycleContainerBase container);

    void onUIRefreshBegin(RecycleContainerBase container);

    void onUIRefreshComplete(RecycleContainerBase container);

}
