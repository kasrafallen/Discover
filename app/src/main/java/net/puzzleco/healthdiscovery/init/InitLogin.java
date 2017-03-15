package net.puzzleco.healthdiscovery.init;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;

import net.puzzleco.healthdiscovery.activity.LoginActivity;
import net.puzzleco.healthdiscovery.R;
import net.puzzleco.healthdiscovery.util.Util;
import net.puzzleco.healthdiscovery.util.ViewUtil;
import net.puzzleco.healthdiscovery.view.Text;

public class InitLogin {
    private static final boolean LOGIN = false;
    private static final boolean SIGN = true;

    private static final int MAIL = +8748748;
    private static final int PASSWORD = +984224;

    private LoginActivity context;
    private float[] dimen;
    private RelativeLayout layout;

    public InitLogin(LoginActivity loginActivity) {
        this.context = loginActivity;
        this.dimen = new Util(context).getDimen();
    }

    public View getView() {
        layout = new RelativeLayout(context);
        layout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        layout.setBackgroundResource(R.color.theme);
        layout.addView(log());
        return layout;
    }

    private View log() {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        layout.addView(space());
        layout.addView(title(LOGIN));
        layout.addView(space());
        layout.addView(space());
        layout.addView(inputData(LOGIN, MAIL));
        layout.addView(inputData(LOGIN, PASSWORD));
        layout.addView(underLine(true));
        layout.addView(space());
        layout.addView(space());
        layout.addView(button(LOGIN));
        layout.addView(underLine(false));
        layout.addView(space());
        layout.addView(space());
        return layout;
    }

    private View underLine(boolean isForget) {
        Text textView = new Text(context);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-2, ViewUtil.toPx(25, context));
        p.leftMargin = (int) (dimen[0] / 7);
        p.rightMargin = (int) (dimen[0] / 7);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(1, 13);
        textView.setGravity(Gravity.CENTER);
        if (isForget) {
            p.gravity = Gravity.LEFT;
            textView.setText("رمز عبور را فراموش کردم");
        } else {
            p.gravity = Gravity.RIGHT;
            textView.setText("هنوز ثبت نام نکرده اید؟");
        }
        ViewUtil.setBackground(textView, context);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        textView.setLayoutParams(p);
        return textView;
    }

    private View button(boolean login) {
        AppCompatButton button = new AppCompatButton(context);
        button.setTag(Boolean.valueOf(login));
        ViewUtil.set(button, context);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams((int) (dimen[0] - ((dimen[0] / 7) * 2)), (int) (dimen[1] / 11));
        p.gravity = Gravity.CENTER_HORIZONTAL;
        p.bottomMargin = (int) (dimen[1] / 50);
        button.setLayoutParams(p);
        button.setText("ورود");
        button.setSupportBackgroundTintList(new ColorStateList(new int[][]{
                new int[]{android.R.attr.state_pressed},
                new int[]{}
        },
                new int[]{
                        context.getResources().getColor(R.color.base),
                        context.getResources().getColor(R.color.white),
                }));
        button.setTextSize(1, 15);
        button.setTextColor(context.getResources().getColor(R.color.theme));
        button.setOnClickListener(context);
        return button;
    }

    private View inputData(boolean mode, int type) {
        TextInputLayout layout = new TextInputLayout(context);
        layout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        layout.setPadding((int) (dimen[0] / 7), 0, (int) (dimen[0] / 7), 0);

        TextInputEditText editText = new TextInputEditText(context);
        ViewUtil.set(editText, context);
        TextInputLayout.LayoutParams p = new TextInputLayout.LayoutParams(-1, -2);
        p.setMargins(0, (int) (dimen[1] / 50), 0, (int) (dimen[1] / 50));
        editText.setLayoutParams(p);
        editText.setGravity(Gravity.RIGHT);
        editText.setHintTextColor(Color.WHITE);
        editText.setTextColor(Color.WHITE);

        if (mode) {

        } else {
            if (type == MAIL) {
                editText.setHint("آدرس ایمیل");
            } else {
                editText.setHint("رمز عبور");
            }
        }

        layout.addView(editText);
        return layout;
    }

    private View title(boolean login) {
        Text text = new Text(context);
        text.setText("APP NAME");
        text.setTextSize(1, 17);
        text.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        text.setGravity(Gravity.CENTER);
        text.setTextColor(Color.WHITE);
        return text;
    }

    private View space() {
        Space space = new Space(context);
        space.setLayoutParams(new LinearLayout.LayoutParams(-1, -2, 1f));
        return space;
    }
}
