package com.sola.testing.solatesting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.sola.testing.solatesting.recycleview.utils.IRecycleAnimatorListItem;
import com.sola.testing.solatesting.recycleview.utils.IRecycleListItem;
import com.sola.testing.solatesting.view.TestItemView;
import com.sola.testing.solatesting.view.TestItemView_;

/**
 * Description:
 * <p/>
 * author: Sola
 * 2015/9/28
 */
public class TestItemDTO implements IRecycleAnimatorListItem {


    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    String test;

    // ===========================================================
    // Constructors
    // ===========================================================

    public TestItemDTO(String test) {
        this.test = test;
    }


    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
    @Override
    public View getView(Context context) {
        return TestItemView_.build(context);
//        return View.inflate(context,R.layout.list_item_test_item_dto,null);
    }

    @Override
    public RecyclerView.ViewHolder getHolder(Context context) {
        return new ViewHolder(getView(context));
    }

    @Override
    public void refreshView(RecyclerView.ViewHolder holder) {
//        holder
        TestItemView view = (TestItemView) holder.itemView;
        view.id_text_test.setText(test);
    }
//
//    @Override
//    public Animator[] getAnimators(View view) {
//        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0, 1f);
//        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0, 1f);
//        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0, 1f);
//        return new ObjectAnimator[]{alpha, scaleX, scaleY};
////        return new Animator[0];
//    }

    @Override
    public AnimatorSet getAnimatorSet(View view) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0, 1f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0, 1f);
        AnimatorSet set = new AnimatorSet();
        set.play(alpha).with(scaleX).with(scaleY);
        set.setDuration(600);
        set.setInterpolator(new LinearInterpolator());
        return set;
    }
    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
    static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(
                View v) {
            super(v);
        }
    }
}
