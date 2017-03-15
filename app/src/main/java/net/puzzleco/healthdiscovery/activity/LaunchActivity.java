package net.puzzleco.healthdiscovery.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.Toast;

import net.puzzleco.healthdiscovery.util.Util;
import net.puzzleco.healthdiscovery.util.ViewUtil;

public class LaunchActivity extends Activity {
    private FrameLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(createLayout());
        checkForLogin();
        ViewUtil.setBar(this);
    }

    private View createLayout() {
        layout = new FrameLayout(this);
        layout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        return layout;
    }

    private void checkForLogin() {
        Util util = new Util(this);
        if (!util.isDimen()) {
            setObserver(util);
        } else {
            if (util.isSigned()) {

            } else {

            }
            startActivity(new Intent(this, LoginActivity.class));
            this.finish();
        }
    }

    private void setObserver(final Util util) {
        layout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                layout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                util.setDimen(new float[]{layout.getWidth(), layout.getHeight()});
                Toast.makeText(LaunchActivity.this, "width : " + layout.getWidth() + "  height : " + layout.getHeight(), Toast.LENGTH_SHORT).show();
                checkForLogin();
            }
        });
    }
}
