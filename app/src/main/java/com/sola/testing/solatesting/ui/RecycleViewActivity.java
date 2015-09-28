package com.sola.testing.solatesting.ui;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sola.testing.solatesting.R;
import com.sola.testing.solatesting.TestItemDTO;
import com.sola.testing.solatesting.TestTwoItem;
import com.sola.testing.solatesting.recycleview.RecycleAnimatorAdapter;
import com.sola.testing.solatesting.recycleview.utils.IRecycleAnimatorListItem;
import com.sola.testing.solatesting.recycleview.utils.IRecycleListItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * <p/>
 * author: Sola
 * 2015/9/25
 */
@EActivity(R.layout.activity_recycle_view)
public class RecycleViewActivity extends FragmentActivity {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    @ViewById
    RecyclerView id_recycler_view;

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

    @AfterViews
    public void afterViews() {
        //设置布局形式
        id_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        id_recycler_view.setItemAnimator(new DefaultItemAnimator());
        List<IRecycleListItem> list = new ArrayList<>();
        TestItemDTO dto = new TestItemDTO("11111111111");
        list.add(dto);
        dto = new TestItemDTO("22222");
        list.add(dto);
        dto = new TestItemDTO("33333");
        list.add(dto);
        dto = new TestItemDTO("44444");
        list.add(dto);
        dto = new TestItemDTO("5555");
        list.add(dto);
        dto = new TestItemDTO("666666");
        list.add(dto);
        for (int i = 0, len = 100; i < len; i++) {
            dto = new TestItemDTO("666666" + i);
            list.add(dto);
            if (i % 5 == 0) {
                TestTwoItem item = new TestTwoItem("5555" + i);
                list.add(item);
            }
        }
        RecycleAnimatorAdapter<IRecycleAnimatorListItem> adapter = new RecycleAnimatorAdapter(this, list);
        adapter.setFirstOnly(false);
        id_recycler_view.setAdapter(adapter);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
