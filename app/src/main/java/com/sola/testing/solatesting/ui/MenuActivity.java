package com.sola.testing.solatesting.ui;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.sola.testing.solatesting.R;
import com.sola.testing.solatesting.TestItemDTO;
import com.sola.testing.solatesting.TestTwoItem;
import com.sola.testing.solatesting.recycleview.MyRecycleItemDecoration;
import com.sola.testing.solatesting.recycleview.RecycleAnimatorAdapter;
import com.sola.testing.solatesting.recycleview.utils.IRecycleAnimatorListItem;
import com.sola.testing.solatesting.recycleview.utils.IRecycleListItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * <p/>
 * author: Sola
 * 2015/10/9
 */
@EActivity(R.layout.activity_menu)
public class MenuActivity extends AppCompatActivity {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    @ViewById
    Toolbar id_tool_bar;

    @ViewById
    Button id_btn_test;

    @ViewById
    RecyclerView id_recycler_view;

    private android.view.ActionMode.Callback mActionModeCallBack = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.test_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.edit:
                case R.id.delete:
                    mode.finish();
                    return true;
                default:
                    return false;
            }
//            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };

    private ActionMode mActionMode;

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
        getMenuInflater().inflate(R.menu.menu_file, menu);
        return true;
    }


    /**
     * 和 onCreateOptionsMenu 对应
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {

        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 在注册后的视图收到对应的Action之后，系统会调用如下方法,在此方法中，您通常可通过扩充菜单资源来定义菜单项。
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        registerForContextMenu(view);  调用创建浮动上下文菜单（类似Dialog，只是这个Dialog是在Menu的某项被长按的情况下出现的）
        super.onCreateContextMenu(menu, v, menuInfo);
        //e.g
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.test_menu, menu);
    }

    /**
     * 与onCreateContextMenu相对应
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
//        这个Info的作用有待调试
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        switch (item.getItemId()) {
//            case R.id.edit:
////                editNote(info.id);
//                Toast.makeText(this, "Edit " + info.id, Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.delete:
////                deleteNote(info.id);
//                Toast.makeText(this, "delete " + info.id, Toast.LENGTH_SHORT).show();
//                return true;
//            default:
//                return super.onContextItemSelected(item);
//        }
        return super.onContextItemSelected(item);
    }
    // ===========================================================
    // Methods
    // ===========================================================

    @AfterViews
    public void afterViews() {
        setSupportActionBar(id_tool_bar);
//        id_recycler_view.setHasFixedSize(true);
        id_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        id_recycler_view.addItemDecoration(new MyRecycleItemDecoration(
                getResources().getDrawable(R.drawable.inset_recycler_item_divider)));
        id_recycler_view.setItemAnimator(new DefaultItemAnimator());

        List<IRecycleAnimatorListItem> list = new ArrayList<>();
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
//                list.add(item);
            }
//            id_scroll_view.addView(dto.getView(this));
        }
//        RecycleAnimatorAdapter adapter = new RecycleAnimatorAdapter(this, list);
        RecycleAnimatorAdapter<IRecycleAnimatorListItem> adapter = new RecycleAnimatorAdapter<>(this, list);
        adapter.setFirstOnly(true);
        id_recycler_view.setAdapter(adapter);

//        registerForContextMenu(id_btn_test);

//        id_recycler_view.setMo

//        AbsListView.MultiChoiceModeListener
    }

    @Click
    public void id_btn_test() {
        if (mActionMode != null)
            return;
        mActionMode = startActionMode(mActionModeCallBack);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
