package com.sola.testing.solatesting.ui;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeTransform;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sola.testing.solatesting.MainApplication;
import com.sola.testing.solatesting.R;
import com.sola.testing.solatesting.TestItemDTO;
import com.sola.testing.solatesting.recycleview.BasicRecycleViewAdapter;
import com.sola.testing.solatesting.recycleview.RecycleFooterView;
import com.sola.testing.solatesting.recycleview.utils.IRecycleListItem;
import com.sola.testing.solatesting.view.load_more.RecycleLoadMoreContainerBase;
import com.sola.testing.solatesting.view.load_more.RecycleHeaderView;
import com.sola.testing.solatesting.view.load_more.RecycleHeaderView_;
import com.sola.testing.solatesting.view.load_more.RecyclerLoadMoreContainer;
import com.sola.testing.solatesting.view.load_more.interfaces.IRecycleLoadMoreHandler;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Description:
 * <p/>
 * author: Sola
 * 2015/9/25
 */
@EActivity(R.layout.activity_recycle_view)
public class RecycleViewActivity extends AppCompatActivity {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    @ViewById
    Toolbar id_tool_bar;

    @ViewById
    ImageView id_image_item_shown;

    @ViewById
    PtrFrameLayout id_ptr_frame;

    RecycleHeaderView headerView;

    @ViewById
    RecyclerView id_recycler_view;

    @ViewById
    RecyclerLoadMoreContainer id_load_more_container;

//    RecycleAnimatorAdapter<IRecycleAnimatorListItem> adapter;

    BasicRecycleViewAdapter<IRecycleListItem> adapter;

    //    PtrListFooterDTO footerDTO;
    List<IRecycleListItem> list;

    RecycleFooterView footer;
    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    protected void onStart() {
        super.onStart();
        MainApplication.BUS.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MainApplication.BUS.unregister(this);
    }

    @TargetApi(21)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAfterTransition();
    }

    // ===========================================================
    // Methods
    // ===========================================================

    @TargetApi(21)
    @Click
    public void id_image_item_shown(View v) {
        Intent intent = new Intent();
        intent.setClass(this, RecycleDetailActivity_.class);
        ((ViewGroup) id_image_item_shown.getParent()).setTransitionGroup(false);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ((BitmapDrawable) id_image_item_shown.getDrawable()).getBitmap().
                compress(Bitmap.CompressFormat.PNG, 100, stream);
        intent.putExtra("image", stream.toByteArray());
        ActivityOptions options =
                ActivityOptions.makeSceneTransitionAnimation(this,
                        v,
                        "image_transition"
                );
//            options.
        startActivity(intent, options.toBundle());
    }


    @TargetApi(21)
    @UiThread
    public void onEvent(TestItemDTO e) {
        if (e != null) {
            Intent intent = new Intent();
            intent.setClass(this, RecycleDetailActivity_.class);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            id_ptr_frame.setTransitionGroup(false);?
            ((ViewGroup) e.getId_image_item_shown().getParent()).setTransitionGroup(true);
//            ((ViewGroup) (e.getId_image_item_shown().getParent()).getParent()).setTransitionGroup(false);
            ((BitmapDrawable) e.getId_image_item_shown().getDrawable()).getBitmap().
                    compress(Bitmap.CompressFormat.PNG, 100, stream);
//            e.getId_image_item_shown().getParent().tr
            intent.putExtra("image", stream.toByteArray());
            ActivityOptions options =
                    ActivityOptions.makeSceneTransitionAnimation(this,
                            e.getId_image_item_shown(),
                            "image_transition"
                    );
//            options.
            startActivity(intent, options.toBundle());
        }
    }

    @TargetApi(21)
    @AfterViews
    public void afterViews() {
//        android.transition.
        getWindow().setExitTransition(new ChangeTransform());
//        getWindow().setEnterTransition(new Slide());

        setSupportActionBar(id_tool_bar);
//        id_load_more_container.setLoadMoreHandler(new IRecycleLoadMoreHandler() {
//            @Override
//            public void onLoadMore(RecycleLoadMoreContainerBase container) {
//                loadMore();
//            }
//        });
        //设置布局形式
        id_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        id_recycler_view.setItemAnimator(new DefaultItemAnimator());
        list = new ArrayList<>();
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
        for (int i = 0, len = 10; i < len; i++) {
            dto = new TestItemDTO("666666" + i);
            list.add(dto);
        }

        headerView = RecycleHeaderView_.build(this);

        id_ptr_frame.setDurationToClose(500);
        id_ptr_frame.setLoadingMinTime(500);
        id_ptr_frame.setHeaderView(headerView);
        id_ptr_frame.addPtrUIHandler(headerView);
        id_ptr_frame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view1) {
                return PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, id_recycler_view, view1);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                refreshMore();
            }
        });
        id_ptr_frame.autoRefresh(false);//
        if (footer == null)
            footer = new RecycleFooterView();
        adapter = new BasicRecycleViewAdapter<>(this, list);
        adapter.addFooterView(footer);
        id_load_more_container.setLoadMoreUIHandler(footer);
        id_load_more_container.setShowLoadingForFirstPage(true);
        id_recycler_view.setAdapter(adapter);
    }


    @Background
    public void loadMore() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addMore();
    }

    @Background
    public void refreshMore() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        refresh();
    }

    @UiThread
    public void refresh() {
        TestItemDTO dto;
        list = new ArrayList<>();
        for (int i = 0, len = 10; i < len; i++) {
            dto = new TestItemDTO("666666" + i);
            list.add(dto);

        }
        adapter.refreshList(list);
        adapter.notifyDataSetChanged();
        id_ptr_frame.refreshComplete();
    }


    @UiThread
    public void addMore() {
        adapter.notifyDataSetChanged();
        id_load_more_container.loadMoreFinish(true, false);
    }

    @UiThread
    public void startListViewUpdate() {
        id_ptr_frame.autoRefresh();
    }
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
