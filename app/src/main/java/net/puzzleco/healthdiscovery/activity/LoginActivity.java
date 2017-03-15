package net.puzzleco.healthdiscovery.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import net.puzzleco.healthdiscovery.init.InitLogin;
import net.puzzleco.healthdiscovery.util.ViewUtil;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new InitLogin(this).getView());
        ViewUtil.setBar(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onClick(View view) {
        if (view.getTag() == Boolean.FALSE) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {

        }
    }
}
