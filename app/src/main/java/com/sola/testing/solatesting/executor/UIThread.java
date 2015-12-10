package com.sola.testing.solatesting.executor;


import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * author: Sola
 * 2015/10/28
 */
public class UIThread implements PostExecutionThread {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

    public UIThread() {
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
