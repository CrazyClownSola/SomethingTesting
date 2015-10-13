package com.sola.testing.solatesting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.sola.testing.solatesting.recycleview.utils.IRecycleAnimatorListItem;
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
    public View getView(final Context context, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item_test_item_dto,
                parent, false);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, test, Toast.LENGTH_SHORT).show();
            }
        });
        return v;
//        return View.inflate(context,R.layout.list_item_test_item_dto,null);
    }

    @Override
    public RecyclerView.ViewHolder getHolder(final Context context, ViewGroup parent) {
        return new ViewHolder(getView(context, parent));
    }


    @Override
    public void refreshView(Context context, RecyclerView.ViewHolder holder) {
//        holder
//        TestItemView view = (TestItemView) holder.itemView;
        ((ViewHolder) holder).id_text_test.setText(test);
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
        return null;
//        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0, 1f);
//        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0, 1f);
//        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0, 1f);
//        AnimatorSet set = new AnimatorSet();
//        set.play(alpha).with(scaleX).with(scaleY);
//        set.setDuration(600);
//        set.setInterpolator(new LinearInterpolator());
//        return set;
    }
    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView id_text_test;

        public ViewHolder(
                View v) {
            super(v);
            id_text_test = (TextView) v.findViewById(R.id.id_text_test);
        }
    }
}
