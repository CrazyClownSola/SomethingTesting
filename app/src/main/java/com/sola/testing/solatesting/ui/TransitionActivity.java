package com.sola.testing.solatesting.ui;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.sola.testing.solatesting.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.ByteArrayOutputStream;

/**
 * author: Sola
 * 2015/12/4
 */
@EActivity(R.layout.acitivty_transition)
public class TransitionActivity extends AppCompatActivity {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    @ViewById
    ImageView id_image_item_shown;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    @Click
    public void id_image_item_shown() {
        Intent intent = new Intent();
        intent.setClass(this, RecycleDetailActivity_.class);
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
////            id_ptr_frame.setTransitionGroup(false);?
////            ((ViewGroup) (e.getId_image_item_shown().getParent()).getParent()).setTransitionGroup(false);
//        ((BitmapDrawable) id_image_item_shown.getDrawable()).getBitmap().
//                compress(Bitmap.CompressFormat.PNG, 100, stream);
////            e.getId_image_item_shown().getParent().tr
//        intent.putExtra("image", stream.toByteArray());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options =
                    ActivityOptions.makeSceneTransitionAnimation(this,
                            id_image_item_shown,
                            "image_transition"
                    );
//            options.
            ActivityCompat.startActivity(this, intent, options.toBundle());
        }

    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
