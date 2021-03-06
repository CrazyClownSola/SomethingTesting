package com.sola.testing.solatesting.ui;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AbsListView;

import com.sola.testing.solatesting.R;
import com.sola.testing.solatesting.TestItemDTO;
import com.sola.testing.solatesting.recycleview.RecycleAnimatorAdapter;
import com.sola.testing.solatesting.recycleview.RecycleFooterView;
import com.sola.testing.solatesting.recycleview.utils.IRecycleAnimatorListItem;
import com.sola.testing.solatesting.view.load_more.interfaces.IRecycleLoadMoreContainer;
import com.sola.testing.solatesting.view.load_more.interfaces.IRecycleLoadMoreHandler;
import com.sola.testing.solatesting.view.pull_to_refresh.PTRHeaderView;
import com.sola.testing.solatesting.view.pull_to_refresh.PTRHeaderView_;
import com.sola.testing.solatesting.view.pull_to_refresh.RecyclerViewPTRFixLoadMoreContainer;
import com.sola.testing.solatesting.view.pull_to_refresh.RecyclerViewRefreshContainerBase;
import com.sola.testing.solatesting.view.pull_to_refresh.interfaces.IPullToRefreshHandler;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Description:
 * <p/>
 * author: Sola
 * 2015/10/19
 */
@EActivity(R.layout.activity_ptr_container)
public class PTRContainerActivity extends AppCompatActivity {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    @ViewById
    Toolbar id_tool_bar;

    @ViewById
    RecyclerView id_recycler_view;

    @ViewById
    RecyclerViewPTRFixLoadMoreContainer id_ptr_frame;

    PTRHeaderView headerView;

    RecycleFooterView footerView;

    RecycleAnimatorAdapter<IRecycleAnimatorListItem> adapter;

    List<IRecycleAnimatorListItem> cacheList;

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

        setSupportActionBar(id_tool_bar);
        headerView = PTRHeaderView_.build(this);
        footerView = new RecycleFooterView();
        id_ptr_frame.setPTRHandler(new IPullToRefreshHandler() {
            @Override
            public boolean checkCanDoRefresh(RecyclerViewRefreshContainerBase frame, View content, View header) {
                return !canChildScrollUp(content);
            }

            public boolean canChildScrollUp(View view) {
                if (android.os.Build.VERSION.SDK_INT < 14) {
                    if (view instanceof AbsListView) {
                        final AbsListView absListView = (AbsListView) view;
                        return absListView.getChildCount() > 0
                                && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                                .getTop() < absListView.getPaddingTop());
                    } else {
                        return view.getScrollY() > 0;
                    }
                } else {
                    return view.canScrollVertically(-1);
                }
            }

            @Override
            public void onRefreshBegin(RecyclerViewRefreshContainerBase frame) {
                refreshMore();
            }
        });
        id_ptr_frame.setHeaderView(headerView);
        id_ptr_frame.addPTRUIHandler(headerView);

        id_ptr_frame.setLoadMoreHandler(new IRecycleLoadMoreHandler() {
            @Override
            public void onLoadMore(IRecycleLoadMoreContainer container) {
                loadMore();
            }
        });
        id_ptr_frame.setLoadMoreUIHandler(footerView);
        id_ptr_frame.setShowLoadingForFirstPage(true);

        id_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        id_recycler_view.setItemAnimator(new DefaultItemAnimator());

        if (cacheList == null)
            cacheList = new ArrayList<>();
        adapter = new RecycleAnimatorAdapter<>(this, cacheList);
        adapter.addFooterView(footerView);
        id_recycler_view.setAdapter(adapter);
//        id_ptr_frame.autoRefresh(false);
        id_ptr_frame.postDelayed(new Runnable() {
            @Override
            public void run() {
                id_ptr_frame.autoRefresh(false);
            }
        }, 300);

    }

    @Background
    public void refreshMore() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        refreshData();
    }

    @UiThread
    public void refreshData() {
        cacheList = new ArrayList<>();
        TestItemDTO dto;
        for (int i = 0, len = 10; i < len; i++) {

            dto = new TestItemDTO("6666" + new Random().nextInt(100) + i);
            cacheList.add(dto);
        }
        adapter.refreshList(cacheList);
        adapter.notifyDataSetChanged();
        id_ptr_frame.refreshComplete();
    }

    @Background
    public void loadMore() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loadMoreData();
    }

    @UiThread
    public void loadMoreData() {
        int t = new Random().nextInt(10);
        TestItemDTO dto;
        for (int i = 0, len = 10; i < len; i++) {
            dto = new TestItemDTO("666666" + i);
            cacheList.add(dto);
        }
        adapter.refreshList(cacheList);
        adapter.notifyDataSetChanged();
        id_ptr_frame.loadMoreFinish(cacheList.size() == 0, t % 5 != 0);
//        id_ptr_frame.refreshComplete();
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
