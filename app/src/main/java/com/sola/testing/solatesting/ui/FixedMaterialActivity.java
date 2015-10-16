package com.sola.testing.solatesting.ui;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.sola.testing.solatesting.R;
import com.sola.testing.solatesting.TestItemDTO;
import com.sola.testing.solatesting.recycleview.RecycleAnimatorAdapter;
import com.sola.testing.solatesting.recycleview.RecycleFooterView;
import com.sola.testing.solatesting.recycleview.utils.IRecycleAnimatorListItem;
import com.sola.testing.solatesting.view.load_more.RecycleContainerBase;
import com.sola.testing.solatesting.view.load_more.RecycleHeaderView;
import com.sola.testing.solatesting.view.load_more.RecycleHeaderView_;
import com.sola.testing.solatesting.view.load_more.RecyclerContainer;
import com.sola.testing.solatesting.view.load_more.interfaces.RecycleLoadMoreHandler;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Description:
 * <p/>
 * author: Sola
 * 2015/10/15
 */
@EActivity(R.layout.activity_fixed_material)
public class FixedMaterialActivity extends AppCompatActivity {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    @ViewById
    Toolbar id_tool_bar;

    @ViewById
    TabLayout id_tab_layout;

    @ViewById
    RecyclerContainer id_load_more_container;

    @ViewById
    PtrFrameLayout id_ptr_frame;

    @ViewById
    RecyclerView id_recycler_view;

    RecycleAnimatorAdapter<IRecycleAnimatorListItem> adapter;

    List<IRecycleAnimatorListItem> cacheList;

    RecycleHeaderView headerView;

    RecycleFooterView footerView;

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
    public boolean onCreateOptionsMenu(Menu menu) {
//        PtrClassicFrameLayout

        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // ===========================================================
    // Methods
    // ===========================================================

    @AfterViews
    public void afterViews() {
        setSupportActionBar(id_tool_bar);
        id_tool_bar.setTitle("整合版");
        setUpTabLayout();
        setUpHeaderViews();
        setUpFooterViews();
        setUpListViews();
    }

    private void setUpTabLayout() {
//        id_tab_layout.setupWithViewPager(); 官方推荐这种方式去做tab项和ViewPager之间的绑定

        id_tab_layout.addTab(id_tab_layout.newTab().setText("Tab 1"));
        id_tab_layout.addTab(id_tab_layout.newTab().setText("Tab 2"));
        id_tab_layout.addTab(id_tab_layout.newTab().setText("Tab 3"));
        id_tab_layout.addTab(id_tab_layout.newTab().setText("Tab 4"));
        id_tab_layout.addTab(id_tab_layout.newTab().setText("Tab 5"));

    }

    private void setUpFooterViews() {
        if (footerView == null)
            footerView = new RecycleFooterView();

        id_load_more_container.setLoadMoreHandler(new RecycleLoadMoreHandler() {
            @Override
            public void onLoadMore(RecycleContainerBase container) {
                loadMore();
            }
        });
        id_load_more_container.setLoadMoreUIHandler(footerView);
        id_load_more_container.setShowLoadingForFirstPage(true);
    }

    private void setUpHeaderViews() {
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
        id_ptr_frame.autoRefresh(true);//
    }

    private void setUpListViews() {
        //设置布局形式
        id_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        id_recycler_view.setItemAnimator(new DefaultItemAnimator());
        if (cacheList == null)
            cacheList = new ArrayList<>();
        adapter = new RecycleAnimatorAdapter<>(this, cacheList);
        adapter.addFooterView(footerView);
        id_recycler_view.setAdapter(adapter);
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
        id_load_more_container.loadMoreFinish(cacheList.size() == 0, t % 5 != 0);
//        id_ptr_frame.refreshComplete();
    }
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
