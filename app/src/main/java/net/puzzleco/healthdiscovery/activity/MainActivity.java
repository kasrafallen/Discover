package net.puzzleco.healthdiscovery.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;

import net.puzzleco.healthdiscovery.init.InitMain;
import net.puzzleco.healthdiscovery.util.ViewUtil;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private InitMain initMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initMain = new InitMain(this);
        setContentView(initMain.getView());
        ViewUtil.setBar(this);
        start();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        if (initMain.layout.isDrawerOpen(Gravity.RIGHT)) {
            initMain.layout.closeDrawer(Gravity.RIGHT);
        } else {
            super.onBackPressed();
        }
    }

    public void start() {
        initMain.showFirst();
    }

    @Override
    public void onRefresh() {
        if (!initMain.isFirst) {
            initMain.refreshLayout.setRefreshing(false);
            return;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initMain.refreshLayout.setRefreshing(false);
            }
        }, 2000);
    }
}
