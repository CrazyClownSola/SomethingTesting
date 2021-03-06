package com.sola.testing.solatesting.ui;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.sola.testing.solatesting.R;
import com.sola.testing.solatesting.TestItemDTO;
import com.sola.testing.solatesting.TestTwoItem;
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
 * 2015/9/28
 */
@EActivity(R.layout.activity_materials_new)
public class MaterialsActivity extends AppCompatActivity {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

//
//    @ViewById
//    LinearLayout id_scroll_view;

//    @ViewById
//    AppBarLayout id_app_bar;

//    @ViewById(R.id.id_container)
//    CoordinatorLayout layout;

    @ViewById
    Toolbar id_tool_bar;

    @ViewById
    RecyclerView id_recycler_view;

    @ViewById
    CollapsingToolbarLayout id_collapsing;

    @ViewById
    CoordinatorLayout id_container_two;

    @ViewById
    FloatingActionButton id_btn_fab;

//    AppBarLayout

//    @ViewById

//    @ViewById
//    TextInputLayout id_text_input_user;
//
//    @ViewById
//    TextInputLayout id_text_input_password;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

//    @Override
//    public void setTitle(CharSequence title) {
//        super.setTitle(title);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
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
        //        MenuInflater inflater = getMenuInflater();
        //        inflater.inflate(R.menu.context_menu, menu);
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
//                editNote(info.id);
//                return true;
//            case R.id.delete:
//                deleteNote(info.id);
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

//        id_tool_bar.setTitle("TitleS");
        setSupportActionBar(id_tool_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//设置返回键
        id_collapsing.setTitle("Title");
//        id_app_bar.set
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
//            id_scroll_view.addView(dto.getView(this));
        }
        RecycleAnimatorAdapter<IRecycleAnimatorListItem> adapter = new RecycleAnimatorAdapter(this, list);
        adapter.setFirstOnly(false);
        id_recycler_view.setAdapter(adapter);
//        id_text_input_user.te
    }

//    @AfterTextChange({R.id.id_edit_text_psw, R.id.id_edit_text_user})
//    public void textChange(Editable text, TextView view) {
//        switch (view.getId()) {
//            case R.id.id_edit_text_psw:
//                id_text_input_password.setErrorEnabled(false);
//                break;
//            case R.id.id_edit_text_user:
//                id_text_input_user.setErrorEnabled(false);
//                break;
//        }
//    }
//
//    @Click
//    public void id_btn_login() {
//        id_text_input_user.setError("请输入用户名");
//        id_text_input_password.setError("请输入密码");
//
////
//        Snackbar s = Snackbar.make(
//                id_tool_bar, "请输入用户名和密码", Snackbar.LENGTH_SHORT);
//        s.getView().setBackgroundColor(R.attr.colorPrimaryDark);
//        s.show();
////        s.getView().
//    }

    @Click
    public void id_btn_test() {
        Toast.makeText(this, "   ", Toast.LENGTH_SHORT).show();
    }

    @ViewById
    FrameLayout id_container;


    @Click
    public void id_btn_fab() {
        final Snackbar s = Snackbar.make(
                id_container, "请输入用户名和密码", Snackbar.LENGTH_SHORT);
        s.setAction("Cancel", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s.dismiss();
            }
        });
//        s.
//        s.getView().setBackgroundColor(getColor(R.color.material_blue_700));
//        s.getView().setBackgroundColor();
//        s.getView().getLayoutParams().width = RelativeLayout.LayoutParams.MATCH_PARENT;
        s.show();
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
