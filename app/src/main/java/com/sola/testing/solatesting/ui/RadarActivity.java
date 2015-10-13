package com.sola.testing.solatesting.ui;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.sola.testing.solatesting.R;
import com.sola.testing.solatesting.view.progress.MyProgressBar;
import com.sola.testing.solatesting.view.radar.MyRadarView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.BackgroundExecutor;

/**
 * Description:
 * <p/>
 * author: Sola
 * 2015/10/12
 */
@EActivity(R.layout.activity_radar)
public class RadarActivity extends AppCompatActivity {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    @ViewById
    Toolbar id_tool_bar;

    @ViewById
    MyRadarView id_radar_view;

    //    @Bean
    MyProgressBar progressBar;

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
//        RadarActivity_
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (progressBar == null)
            progressBar = MyProgressBar.createProgress(this);
        if (item.getItemId() == R.id.action_settings) {
            id_radar_view.startRadar();
//            progressBar.dismiss();
        } else if (item.getItemId() == R.id.action_show) {

            progressBar.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BackgroundExecutor.cancelAll("radar", true);
    }

    // ===========================================================
    // Methods
    // ===========================================================

    @AfterViews
    public void afterViews() {
        setSupportActionBar(id_tool_bar);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
