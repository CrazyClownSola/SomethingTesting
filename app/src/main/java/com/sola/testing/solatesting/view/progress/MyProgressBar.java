package com.sola.testing.solatesting.view.progress;


import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.sola.testing.solatesting.R;

/**
 * Description:
 * <p/>
 * author: Sola
 * 2015/10/12
 */
public class MyProgressBar extends Dialog {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    static MyProgressBar instance;

    AnimatorSet set;

    //    @ViewById
    ImageView id_image_loading;

    //    @ViewById
    ImageView id_image_loading_inner;

    //    @ViewById
    TextView id_text_progress_message;

    Animator rotation;


    // ===========================================================
    // Constructors
    // ===========================================================

    public MyProgressBar(Context context) {
        super(context, R.style.CustomProgressDialog);
        setContentView(R.layout.layout_progress_bar);
        getWindow().getAttributes().gravity = Gravity.CENTER;
        id_image_loading = (ImageView) findViewById(R.id.id_image_loading);
        rotation = ObjectAnimator.ofFloat(id_image_loading, "rotation", 0, 360);
        ((ObjectAnimator) rotation).setRepeatCount(ValueAnimator.INFINITE);
//        rotation.setRe
    }

    //
    public static MyProgressBar createProgress(Context context) {
        if (instance == null)
            instance = new MyProgressBar(context);
        return instance;
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public void show() {
        if (!isShowing()) {
            super.show();
            startAnimator();
        }

    }

    //
    public void startAnimator() {
        set = new AnimatorSet();
        set.play(rotation);
        set.setInterpolator(new LinearInterpolator());
        set.setStartDelay(0);
        set.setDuration(4000);
        set.start();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (set != null && set.isRunning())
            set.cancel();
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
