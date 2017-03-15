package net.puzzleco.healthdiscovery.init;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import net.puzzleco.healthdiscovery.R;
import net.puzzleco.healthdiscovery.activity.DetailActivity;
import net.puzzleco.healthdiscovery.util.Util;
import net.puzzleco.healthdiscovery.util.ViewUtil;
import net.puzzleco.healthdiscovery.view.Text;

public class InitDetail {
    private DetailActivity context;
    private float[] dimen;
    private int app_size;
    private int toolbar_size;
    private int padding;

    public InitDetail(DetailActivity detailActivity) {
        this.context = detailActivity;
        this.dimen = new Util(context).getDimen();
        this.app_size = ViewUtil.toPx(250, context);
        this.toolbar_size = ViewUtil.toPx(41, context);
        this.padding = ViewUtil.toPx(18, context);
    }

    public View getView() {
        CoordinatorLayout layout = new CoordinatorLayout(context);
        layout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        layout.setBackgroundResource(R.color.white);
        layout.addView(appBar());
        layout.addView(list());
        layout.addView(buy());
        return layout;
    }

    private View list() {
        NestedScrollView scrollView = new NestedScrollView(context);
        CoordinatorLayout.LayoutParams p = new CoordinatorLayout.LayoutParams(-1, -2);
        p.setBehavior(new AppBarLayout.ScrollingViewBehavior());
        scrollView.setLayoutParams(p);

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(title("آزمایش خون در آزمایشگاه  رسالت  با 20 درصد تخفیف و امکانات مناسب"));
        layout.addView(timer());
        layout.addView(line());
        layout.addView(title("اتوبان رسالت،جنب خیابان کرمان جنوبی،پلاک 59 طبقه پنجم"));
        layout.addView(detail());
        layout.addView(line());
        layout.addView(desc(true));
        layout.addView(desc(false));

        scrollView.addView(layout);
        return scrollView;
    }

    private View desc(boolean isSpecials) {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-1, -2);
        p.topMargin = ViewUtil.toPx(30, context);
        if (!isSpecials) {
            p.bottomMargin = ViewUtil.toPx(60, context);
        }
        layout.setLayoutParams(p);
        layout.setPadding(padding, 0, padding, 0);
        layout.addView(header(isSpecials));
        String[] data = data(isSpecials);
        for (String s : data) {
            layout.addView(item(s));
        }
        return layout;
    }

    private String[] data(boolean isSpecials) {
        if (isSpecials) {
            return new String[]{"امکان استفاده از یک لحظه پس از خرید-",
                    "مالیات بر ارزش افزوده : ندارد-",
                    "جای پارک راحت-"};
        } else {
            return new String[]{"ساعات سرویس دهی : 8 صبح تا 16 بعدازظهر-"
                    , "روزهای سرویس دهی : شنبه تا چهارشنبه-"};
        }
    }

    private View item(String s) {
        Text text = new Text(context);
        text.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        text.setGravity(Gravity.RIGHT);
        text.setPadding(padding, 0, padding, 0);
        text.setTextColor(Color.DKGRAY);
        text.setSingleLine(false);
        text.setTextSize(1, 13);
        text.setText("- ");
        text.append(s.replaceAll("\\-", ""));
        return text;
    }

    private View header(boolean isSpecials) {
        RelativeLayout layout = new RelativeLayout(context);
        layout.setLayoutParams(new LinearLayout.LayoutParams(-1, ViewUtil.toPx(50, context)));

        View view = new View(context);
        RelativeLayout.LayoutParams pp = new RelativeLayout.LayoutParams(-1, ViewUtil.toPx(1, context));
        pp.addRule(RelativeLayout.CENTER_VERTICAL);
        view.setLayoutParams(pp);
        view.setBackgroundColor(context.getResources().getColor(R.color.theme));

        LinearLayout title = new LinearLayout(context);
        title.setOrientation(LinearLayout.HORIZONTAL);
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewUtil.toPx(100, context), ViewUtil.toPx(40, context));
        p.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        title.setLayoutParams(p);
        title.setBackgroundResource(R.mipmap.header_base);

        Text tv = new Text(context);
        tv.setTextSize(1, 14);
        tv.setSingleLine();
        tv.setTextColor(Color.WHITE);
        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setLayoutParams(new LinearLayout.LayoutParams(-2, -1, 1f));

        View icon = new View(context);
        LinearLayout.LayoutParams ppp = new LinearLayout.LayoutParams(ViewUtil.toPx(25, context), ViewUtil.toPx(25, context));
        ppp.gravity = Gravity.CENTER_VERTICAL;
        ppp.setMargins(ViewUtil.toPx(5, context), 0, ViewUtil.toPx(5, context), 0);
        icon.setLayoutParams(ppp);

        if (isSpecials) {
            icon.setBackgroundResource(R.mipmap.specials);
            tv.setText("ویژگی ها");
        } else {
            icon.setBackgroundResource(R.mipmap.depends);
            tv.setText("شرایط");
        }

        title.addView(tv);
        title.addView(icon);

        layout.addView(view);
        layout.addView(title);
        return layout;
    }

    private View buy() {
        RelativeLayout layout = new RelativeLayout(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            layout.setElevation(4);
        }
        layout.setBackgroundResource(R.color.dark);
        CoordinatorLayout.LayoutParams p = new CoordinatorLayout.LayoutParams(-1, ViewUtil.toPx(50, context));
        p.gravity = Gravity.BOTTOM;
        layout.setLayoutParams(p);

        View buyIcon = new View(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            buyIcon.setElevation(20);
        }
        RelativeLayout.LayoutParams pp = new RelativeLayout.LayoutParams(ViewUtil.toPx(20, context), ViewUtil.toPx(20, context));
        pp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        pp.addRule(RelativeLayout.CENTER_VERTICAL);
        pp.leftMargin = ViewUtil.toPx(70, context);
        buyIcon.setLayoutParams(pp);
        buyIcon.setBackgroundResource(R.mipmap.buy_icon);

        layout.addView(price());
        layout.addView(buyButton());
        layout.addView(buyIcon);
        return layout;
    }

    private View buyButton() {
        AppCompatButton button = new AppCompatButton(context);
        ViewUtil.set(button, context);
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(-2, -1);
        p.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        p.leftMargin = padding;
        button.setLayoutParams(p);
        button.setText("        خرید");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            button.setSupportBackgroundTintList(new ColorStateList(new int[][]{
                    new int[]{android.R.attr.state_pressed},
                    new int[]{}
            },
                    new int[]{
                            context.getResources().getColor(R.color.theme),
                            context.getResources().getColor(R.color.dark),
                    }));
        } else {
            button.setSupportBackgroundTintList(new ColorStateList(new int[][]{
                    new int[]{android.R.attr.state_pressed},
                    new int[]{}
            },
                    new int[]{
                            context.getResources().getColor(R.color.lite),
                            context.getResources().getColor(R.color.theme),
                    }));
        }
        button.setTextSize(1, 15);
        button.setTextColor(context.getResources().getColor(R.color.white));
        button.setOnClickListener(context);
        return button;
    }

    private View price() {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(-2, -2);
        p.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        p.rightMargin = padding;
        p.addRule(RelativeLayout.CENTER_VERTICAL);
        layout.setLayoutParams(p);
        layout.addView(price(false));
        layout.addView(price(true));
        return layout;
    }

    private View price(boolean isNew) {
        Text text = new Text(context);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-2, -2);
        p.gravity = Gravity.RIGHT;
        text.setLayoutParams(p);
        text.setSingleLine();
        if (isNew) {
            text.setTextColor(Color.WHITE);
            text.setTextSize(1, 15);
            text.setText(ViewUtil.number(15000) + " تومان");
        } else {
            text.setTextColor(Color.BLACK);
            text.setTextSize(1, 11);
            text.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            text.setText(ViewUtil.number(20000) + " تومان");
        }
        return text;
    }

    private View detail() {
        RelativeLayout layout = new RelativeLayout(context);
        layout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        layout.setPadding(padding, 0, padding, 0);

        View phoneIcon = new View(context);
        phoneIcon.setId(+100001);
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewUtil.toPx(20, context), ViewUtil.toPx(20, context));
        p.addRule(RelativeLayout.CENTER_VERTICAL);
        p.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        p.rightMargin = (int) (2f * padding / 3f);
        phoneIcon.setLayoutParams(p);
        phoneIcon.setBackgroundResource(R.mipmap.call_icon);

        Text phoneNumber = new Text(context);
        phoneNumber.setSingleLine();
        RelativeLayout.LayoutParams pp = new RelativeLayout.LayoutParams(-2, -2);
        pp.addRule(RelativeLayout.CENTER_VERTICAL);
        pp.addRule(RelativeLayout.RIGHT_OF, +100001);
        phoneNumber.setLayoutParams(pp);
        phoneNumber.setText("021-77418752");
        phoneNumber.setTextColor(context.getResources().getColor(R.color.theme));
        phoneNumber.setTypeface(phoneNumber.getTypeface(), Typeface.BOLD);
        phoneNumber.setTextSize(1, 18);

        RelativeLayout l = new RelativeLayout(context);
        ViewUtil.setBackground(l, context);
        RelativeLayout.LayoutParams ppp = new RelativeLayout.LayoutParams(ViewUtil.toPx(35, context), ViewUtil.toPx(35, context));
        ppp.addRule(RelativeLayout.CENTER_VERTICAL);
        ppp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        l.setLayoutParams(ppp);

        View gps = new View(context);
        RelativeLayout.LayoutParams pppp = new RelativeLayout.LayoutParams(ViewUtil.toPx(20, context), ViewUtil.toPx(20, context));
        pppp.addRule(RelativeLayout.CENTER_IN_PARENT);
        gps.setLayoutParams(pppp);
        gps.setBackgroundResource(R.mipmap.gps);

        l.addView(gps);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        layout.addView(phoneIcon);
        layout.addView(phoneNumber);
        layout.addView(l);
        return layout;
    }

    private View line() {
        View view = new View(context);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, ViewUtil.toPx(1, context)));
        view.setBackgroundColor(Color.LTGRAY);
        return view;
    }

    private View title(String txt) {
        int margin = (int) (28f * padding / 40f);
        Text text = new Text(context);
        text.setSingleLine(false);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-1, -2);
        p.setMargins(0, margin, 0, margin);
        text.setLayoutParams(p);
        text.setPadding(padding, 0, padding, 0);
        text.setTextSize(1, 12);
        text.setTextColor(Color.DKGRAY);
        text.setText(txt);
        text.setGravity(Gravity.RIGHT);
        return text;
    }

    private View timer() {
        int size = ViewUtil.toPx(90, context);
        LinearLayout layout = new LinearLayout(context);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-1, size);
        p.bottomMargin = (int) (1f * size / 7f);
        layout.setLayoutParams(p);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.addView(space());
        layout.addView(dateItem(true));
        layout.addView(space());
        layout.addView(progress(size));
        layout.addView(space());
        layout.addView(dateItem(false));
        layout.addView(space());
        return layout;
    }

    private View progress(int size) {
        RelativeLayout layout = new RelativeLayout(context);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(size, size);
        layout.setLayoutParams(p);

        CircularProgressBar bar = new CircularProgressBar(context, null);
        RelativeLayout.LayoutParams pp = new RelativeLayout.LayoutParams(size, size);
        pp.addRule(RelativeLayout.CENTER_IN_PARENT);
        bar.setLayoutParams(pp);
        bar.setProgress(20);
        bar.setColor(context.getResources().getColor(R.color.progress));
        bar.setBackgroundColor(Color.LTGRAY);
        bar.setBackgroundProgressBarWidth(1f * size / 13f);
        bar.setProgressBarWidth(1f * size / 13f);

        Text text = new Text(context);
        RelativeLayout.LayoutParams ppp = new RelativeLayout.LayoutParams(-2, -2);
        ppp.addRule(RelativeLayout.CENTER_IN_PARENT);
        text.setLayoutParams(ppp);
        text.setTextSize(1, 11);
        text.setTypeface(text.getTypeface(), Typeface.BOLD);
        text.setTextColor(Color.DKGRAY);
        text.setGravity(Gravity.CENTER);
        text.setText("20%" + "\nتخفیف");

        layout.addView(bar);
        layout.addView(text);
        return layout;
    }

    private View dateItem(boolean isCounter) {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-2, -2);
        p.gravity = Gravity.CENTER_VERTICAL;
        layout.setLayoutParams(p);
        if (isCounter) {
            layout.addView(text(true, "126"));
            layout.addView(text(false, " فروخته شده "));
        } else {
            layout.addView(text(true, "15"));
            layout.addView(text(false, "روز تا پایان"));
        }
        return layout;
    }

    private View text(boolean isGreen, String txt) {
        Text text = new Text(context);
        text.setSingleLine();
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-2, -2);
        p.gravity = Gravity.CENTER_HORIZONTAL;
        text.setLayoutParams(p);
        if (isGreen) {
            text.setTextSize(1, 18);
            text.setTextColor(context.getResources().getColor(R.color.dark));
            text.setTypeface(text.getTypeface(), Typeface.BOLD);
        } else {
            text.setTextSize(1, 13);
            text.setTextColor(Color.DKGRAY);
        }
        text.setText(txt);
        return text;
    }

    private View space() {
        Space space = new Space(context);
        space.setLayoutParams(new LinearLayout.LayoutParams(-2, -1, 1f));
        return space;
    }

    private View appBar() {
        AppBarLayout layout = new AppBarLayout(context);
        CoordinatorLayout.LayoutParams p = new CoordinatorLayout.LayoutParams(-1, app_size);
        layout.setLayoutParams(p);

        CollapsingToolbarLayout collapse = new CollapsingToolbarLayout(context);
        AppBarLayout.LayoutParams pp = new AppBarLayout.LayoutParams(-1, -1);
        pp.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
        collapse.setLayoutParams(pp);
        collapse.setContentScrimResource(R.color.theme);
        collapse.addView(pager());
        collapse.addView(toolbar());

        layout.addView(collapse);
        return layout;
    }

    private View pager() {
        SliderLayout layout = new SliderLayout(context);
        layout.setPresetTransformer(SliderLayout.Transformer.RotateUp);
        layout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        layout.setCustomAnimation(new DescriptionAnimation());
        layout.setDuration(3000);
        CollapsingToolbarLayout.LayoutParams p = new CollapsingToolbarLayout.LayoutParams(-1, -1);
        p.setCollapseMode(CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PARALLAX);
        layout.setLayoutParams(p);

        layout.addSlider(slide("آزمایشگاه", R.mipmap.pic));
        layout.addSlider(slide("آزمایشگاه", R.mipmap.pic));
        layout.addSlider(slide("آزمایشگاه", R.mipmap.pic));
        return layout;
    }

    private TextSliderView slide(String txt, @DrawableRes int pic) {
        TextSliderView view = new TextSliderView(context);
        view.setScaleType(TextSliderView.ScaleType.CenterCrop);
        view.image(pic);
        view.description(txt);
        return view;
    }

    private View toolbar() {
        CollapsingToolbarLayout.LayoutParams p = new CollapsingToolbarLayout.LayoutParams(-1, toolbar_size);
        p.setCollapseMode(CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN);
        final Toolbar toolbar = new Toolbar(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setElevation(4);
        }
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setLayoutParams(p);
        toolbar.setContentInsetsRelative(0, 0);
        toolbar.setContentInsetsAbsolute(0, 0);
        toolbar.addView(back(toolbar_size));
        return toolbar;
    }

    private View back(int size) {
        RelativeLayout button = new RelativeLayout(context);
        ViewUtil.setBackground(button, context);
        Toolbar.LayoutParams p = new Toolbar.LayoutParams(size, size);
        p.gravity = Gravity.CENTER_VERTICAL | Gravity.LEFT;
        button.setLayoutParams(p);

        View view = new View(context);
        view.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        view.setBackgroundResource(R.drawable.ic_hardware_keyboard_backspace);

        button.addView(view);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.finish();
            }
        });
        return button;
    }
}
