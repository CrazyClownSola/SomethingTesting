package com.sola.testing.solatesting.adapter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;

/**
 * Description:
 * <p 用于注册界面中的View之间切换的动画适配类/>
 * author: Sola
 * 2015/9/11
 */
public class ScrollAnimatorAdapter {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    View animatorView;

    Context mContext;

    AnimatorSet scrollAnim;

//    boolean isOpen;

    // ===========================================================
    // Constructors
    // ===========================================================

    public ScrollAnimatorAdapter(View animatorView, Context mContext, boolean isOpen) {
        this.animatorView = animatorView;
        this.mContext = mContext;
        resetAnimators(isOpen);
        scrollAnim = buildAnimatorSet(isOpen);
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


    /**
     * 触发动画开始
     */
    public void toggleStart() {
        if (scrollAnim != null && !scrollAnim.isRunning())
            scrollAnim.start();
    }

    private AnimatorSet buildAnimatorSet(boolean isOpen) {

        int deltaX = animatorView.getWidth();

        Animator translateX = ObjectAnimator.ofFloat(
                animatorView, "translationX", isOpen ? -deltaX : 0);

        Animator alpha = ObjectAnimator.ofFloat(
                animatorView, "alpha", isOpen ? 1 : 0.2f, isOpen ? 0.2f : 1
        );

        AnimatorSet fullAnimator = new AnimatorSet();
        fullAnimator.play(translateX).with(alpha);
        fullAnimator.setDuration(700);
        fullAnimator.setStartDelay(isOpen ? 0 : 200);
//        fullAnimator.

        return fullAnimator;
    }

    private void resetAnimators(boolean isOpen) {
        if (!isOpen) {
            int deltaX = animatorView.getWidth();
            animatorView.setTranslationX(deltaX);
//            AnimationSet.
        }
    }


    public void setListener(Animator.AnimatorListener listener) {
        scrollAnim.addListener(listener);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
