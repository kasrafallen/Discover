package net.puzzleco.healthdiscovery.init;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import net.puzzleco.healthdiscovery.R;
import net.puzzleco.healthdiscovery.activity.MainActivity;
import net.puzzleco.healthdiscovery.util.ViewUtil;
import net.puzzleco.healthdiscovery.view.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class InitDrawer implements View.OnClickListener {
    private static final int EXIT = +12589;
    private static final int LAW = +12588;
    private static final int ABOUT = +12587;
    private static final int CALL = +12586;
    private static final int FEED = +12585;
    private static final int HEALTHCENTERS = +12584;
    private static final int CATEGORY = +12583;
    private static final int PURCHASE = +12582;
    private static final int PROFILE = +12581;
    private static final int MAIN = +12580;

    private MainActivity context;
    private float dimen[];
    private InitMain initMain;

    public InitDrawer(InitMain initMain, float[] dimen, MainActivity context) {
        this.dimen = dimen;
        this.context = context;
        this.initMain = initMain;
    }

    public View getView() {
        ScrollView scrollView = new ScrollView(context);
        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.setHorizontalScrollBarEnabled(false);
        scrollView.setBackgroundColor(context.getResources().getColor(R.color.base));
        DrawerLayout.LayoutParams p = new DrawerLayout.LayoutParams(ViewUtil.toPx(230, context), -1);
        p.gravity = Gravity.RIGHT;
        scrollView.setLayoutParams(p);

        LinearLayout layout = new LinearLayout(context);
        layout.setLayoutParams(new ScrollView.LayoutParams(-1, -2));
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(header());
        layout.addView(item(MAIN));
        layout.addView(item(PROFILE));
        layout.addView(item(PURCHASE));
        layout.addView(line());
        layout.addView(item(CATEGORY));
        layout.addView(item(HEALTHCENTERS));
        layout.addView(line());
        layout.addView(item(FEED));
        layout.addView(item(CALL));
        layout.addView(item(ABOUT));
        layout.addView(item(LAW));
        layout.addView(item(EXIT));

        scrollView.addView(layout);
        return scrollView;
    }

    private View item(int id) {
        RelativeLayout layout = new RelativeLayout(context);
        layout.setId(id);
        ViewUtil.setBackground(layout, context);
        layout.setLayoutParams(new LinearLayout.LayoutParams(-1, ViewUtil.toPx(50, context)));
        layout.setOnClickListener(this);

        View view = new View(context);
        view.setId(+999998);
        RelativeLayout.LayoutParams pp = new RelativeLayout.LayoutParams(ViewUtil.toPx(20, context), ViewUtil.toPx(20, context));
        pp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        pp.addRule(RelativeLayout.CENTER_VERTICAL);
        pp.rightMargin = ViewUtil.toPx(12, context);
        view.setLayoutParams(pp);

        Text text = new Text(context);
        text.setTextColor(Color.DKGRAY);
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(-1, -2);
        p.setMargins(ViewUtil.toPx(12, context), 0, ViewUtil.toPx(12, context), 0);
        p.addRule(RelativeLayout.LEFT_OF, +999998);
        p.addRule(RelativeLayout.CENTER_VERTICAL);
        text.setLayoutParams(p);
        text.setGravity(Gravity.RIGHT);

        switch (id) {
            case MAIN:
                text.setText("صفحه اصلی");
                view.setBackgroundResource(R.mipmap.home);
                break;
            case PROFILE:
                text.setText("حساب کاربری");
                view.setBackgroundResource(R.mipmap.profile);
                break;
            case PURCHASE:
                text.setText("سبد خرید");
                view.setBackgroundResource(R.mipmap.buy);
                break;
            case CATEGORY:
                text.setText("دسته بندی");
                view.setBackgroundResource(R.mipmap.category);
                break;
            case EXIT:
                text.setText("خروج");
                view.setBackgroundResource(R.mipmap.exit);
                break;
            case ABOUT:
                text.setText("درباره ما");
                view.setBackgroundResource(R.mipmap.about);
                break;
            case LAW:
                text.setText("قوانین و مقررات");
                view.setBackgroundResource(R.mipmap.law);
                break;
            case CALL:
                text.setText("تماس با ما");
                view.setBackgroundResource(R.mipmap.call);
                break;
            case FEED:
                text.setText("اشتراک خبرنامه");
                view.setBackgroundResource(R.mipmap.feed);
                break;
            case HEALTHCENTERS:
                text.setText("مراکز درمانی");
                view.setBackgroundResource(R.mipmap.health_centers);
                break;
        }

        layout.addView(view);
        layout.addView(text);
        return layout;
    }

    private View line() {
        View view = new View(context);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, ViewUtil.toPx(1, context)));
        view.setBackgroundResource(R.color.theme);
        return view;
    }

    private View header() {
        RelativeLayout layout = new RelativeLayout(context);
        layout.setLayoutParams(new LinearLayout.LayoutParams(-1, ViewUtil.toPx(110, context)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            layout.setElevation(2);
        }
        layout.setBackgroundResource(R.color.theme);
        layout.addView(image());
        layout.addView(text());
        return layout;
    }

    private View text() {
        Text text = new Text(context);
        text.setTextSize(1, 12);
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(-1, -2);
        p.addRule(RelativeLayout.BELOW, +8888777);
        p.topMargin = ViewUtil.toPx(10, context);
        text.setLayoutParams(p);
        text.setPadding(ViewUtil.toPx(12, context), 0, ViewUtil.toPx(12, context), 0);
        text.setGravity(Gravity.RIGHT);
        text.setTextColor(Color.WHITE);
        text.setSingleLine();
        text.setEllipsize(TextUtils.TruncateAt.END);
        text.setText("حسین مجیدی نژاد");
        return text;
    }

    private View image() {
        CircleImageView view = new CircleImageView(context);
        view.setId(+8888777);
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewUtil.toPx(50, context), ViewUtil.toPx(50, context));
        p.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        p.rightMargin = ViewUtil.toPx(12, context);
        p.topMargin = ViewUtil.toPx(18, context);
        view.setLayoutParams(p);
        view.setBorderColor(Color.WHITE);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setImageResource(R.mipmap.user_pic);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case MAIN:
                if (!initMain.isFirst) {
                    initMain.showFirst();
                }
                break;
            case PROFILE:
                break;
            case PURCHASE:
                break;
            case CATEGORY:
                if (initMain.isFirst) {
                    initMain.showCategories();
                }
                break;
            case EXIT:
                break;
            case ABOUT:
                break;
            case LAW:
                break;
            case CALL:
                break;
            case FEED:
                break;
            case HEALTHCENTERS:
                break;
        }
        initMain.doDrawer();
    }
}
