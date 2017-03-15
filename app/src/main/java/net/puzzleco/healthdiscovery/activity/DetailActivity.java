package net.puzzleco.healthdiscovery.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import net.puzzleco.healthdiscovery.init.InitDetail;
import net.puzzleco.healthdiscovery.util.ViewUtil;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    private InitDetail initDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initDetail = new InitDetail(this);
        setContentView(initDetail.getView());
        ViewUtil.setBar(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onClick(View view) {

    }
}
