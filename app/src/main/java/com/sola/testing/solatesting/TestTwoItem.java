package com.sola.testing.solatesting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nineoldandroids.animation.AnimatorSet;
import com.sola.testing.solatesting.recycleview.utils.IRecycleAnimatorListItem;
import com.sola.testing.solatesting.recycleview.utils.IRecycleListItem;
import com.sola.testing.solatesting.view.TestTwoItemView;
import com.sola.testing.solatesting.view.TestTwoItemView_;

/**
 * Description:
 * <p/>
 * author: Sola
 * 2015/9/28
 */
public class TestTwoItem implements IRecycleAnimatorListItem {

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

    public TestTwoItem(String test) {
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
        return TestTwoItemView_.build(context);
    }

    @Override
    public RecyclerView.ViewHolder getHolder(Context context) {
        return new ViewHolder(getView(context));
    }

    @Override
    public void refreshView(RecyclerView.ViewHolder holder) {
        TestTwoItemView view = (TestTwoItemView) holder.itemView;
        view.id_text_test.setText(test);
    }

//    @Override
//    public Animator[] getAnimators(View view) {
//        return new Animator[0];
//    }

    @Override
    public AnimatorSet getAnimatorSet(View view) {
        return null;
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
