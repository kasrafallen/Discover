package net.puzzleco.healthdiscovery.init;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.Toast;

import net.puzzleco.healthdiscovery.R;
import net.puzzleco.healthdiscovery.activity.MainActivity;
import net.puzzleco.healthdiscovery.adaptor.MainGridAdaptor;
import net.puzzleco.healthdiscovery.adaptor.MainListAdaptor;
import net.puzzleco.healthdiscovery.util.Util;
import net.puzzleco.healthdiscovery.util.ViewUtil;
import net.puzzleco.healthdiscovery.view.Text;

import java.util.ArrayList;

public class InitMain {
    private MainActivity context;
    private float[] dimen;
    private RecyclerView recycler;

    public boolean isFirst;

    public SwipeRefreshLayout refreshLayout;
    public DrawerLayout layout;

    private Text text;
    private Text subject;
    private LinearLayout header;
    private RelativeLayout button;
    private NumberPicker picker;

    private int toolbar_size;
    private int header_size;

    public InitMain(MainActivity mainActivity) {
        this.context = mainActivity;
        this.dimen = new Util(context).getDimen();
        this.isFirst = true;
        this.header_size = ViewUtil.toPx(27, context);
        this.toolbar_size = ViewUtil.toPx(41, context);
    }

    public View getView() {
        layout = new DrawerLayout(context);
        layout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));

        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(new DrawerLayout.LayoutParams(-1, -1));
        frameLayout.addView(getLayout());

        layout.addView(frameLayout);
        layout.addView(getDrawer());
        return layout;
    }

    private View getDrawer() {
        return new InitDrawer(this, dimen, context).getView();
    }

    private View getLayout() {
        CoordinatorLayout layout = new CoordinatorLayout(context);
        layout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        layout.setBackgroundResource(R.color.base);

        layout.addView(toolbar());
        layout.addView(header());
        layout.addView(list());
        return layout;
    }

    private View header() {
        header = new LinearLayout(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            layout.setElevation(2);
        }
        CoordinatorLayout.LayoutParams p = new CoordinatorLayout.LayoutParams(-1, header_size);
        p.topMargin = toolbar_size;
        header.setLayoutParams(p);
        header.setOrientation(LinearLayout.HORIZONTAL);
        header.setBackgroundResource(R.color.white);
        header.setPadding((int) (dimen[0] / 20), 0, (int) (dimen[0] / 20), 0);
        header.addView(title());
        return header;
    }

    private View title() {
        text = new Text(context);
        text.setSingleLine();
        text.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        text.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        text.setTextColor(Color.DKGRAY);
        text.setTextSize(1, 12);
        text.setText("صفحه اصلی");
        return text;
    }

    private View list() {
        refreshLayout = new SwipeRefreshLayout(context);
        CoordinatorLayout.LayoutParams p = new CoordinatorLayout.LayoutParams(-1, -2);
        p.topMargin = header_size + toolbar_size;
        refreshLayout.setLayoutParams(p);
        refreshLayout.setColorSchemeResources(R.color.dark, R.color.theme, R.color.lite);
        refreshLayout.setProgressBackgroundColorSchemeResource(R.color.white);
        refreshLayout.setProgressViewOffset(false, -ViewUtil.toPx(60, context), (int) (dimen[1] / 6));

        recycler = new RecyclerView(context);
        recycler.setLayoutParams(new SwipeRefreshLayout.LayoutParams(-2, -2));
        recycler.setHasFixedSize(true);

        refreshLayout.addView(recycler);
        refreshLayout.setOnRefreshListener(context);
        return refreshLayout;
    }

    private View toolbar() {
        CoordinatorLayout.LayoutParams p = new CoordinatorLayout.LayoutParams(-1, toolbar_size);
        final Toolbar toolbar = new Toolbar(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setElevation(4);
        }
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setLayoutParams(p);
        toolbar.setContentInsetsRelative(0, 0);
        toolbar.setContentInsetsAbsolute(0, 0);
        toolbar.setBackgroundResource(R.color.theme);
        toolbar.addView(navigation(toolbar_size));
        toolbar.addView(appName(toolbar_size));
        toolbar.addView(sort(toolbar_size));
        return toolbar;
    }

    private View sort(int size) {
        button = new RelativeLayout(context);
        ViewUtil.setBackground(button, context);
        Toolbar.LayoutParams p = new Toolbar.LayoutParams(size, size);
        p.gravity = Gravity.CENTER_VERTICAL | Gravity.LEFT;
        button.setLayoutParams(p);

        View view = new View(context);
        view.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));

        button.addView(view);
        return button;
    }

    private View appName(int height) {
        subject = new Text(context);
        subject.setSingleLine();
        subject.setTextColor(Color.WHITE);
        Toolbar.LayoutParams p = new Toolbar.LayoutParams(-2, height);
        p.rightMargin = ViewUtil.toPx(5, context);
        p.gravity = Gravity.RIGHT;
        subject.setLayoutParams(p);
        subject.setGravity(Gravity.CENTER_VERTICAL);
        subject.setTextSize(1, 18);
        return subject;
    }

    private View navigation(int size) {
        RelativeLayout layout = new RelativeLayout(context);
        ViewUtil.setBackground(layout, context);
        Toolbar.LayoutParams p = new Toolbar.LayoutParams(size, size);
        p.gravity = Gravity.CENTER_VERTICAL | Gravity.RIGHT;
        layout.setLayoutParams(p);

        View view = new View(context);
        view.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        view.setBackgroundResource(R.drawable.ic_navigation_menu);

        layout.addView(view);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doDrawer();
            }
        });
        return layout;
    }

    public void doDrawer() {
        if (layout.isDrawerOpen(Gravity.RIGHT)) {
            layout.closeDrawer(Gravity.RIGHT);
        } else {
            layout.openDrawer(Gravity.RIGHT);
        }
    }

    private void setList() {
        ArrayList<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        recycler.setAdapter(new MainListAdaptor(context, dimen, list));
    }

    public void setText(String txt) {
        text.setText(txt);
    }

    public void showFirst() {
        isFirst = true;

        button.getChildAt(0).setBackgroundResource(R.drawable.ic_action_group_work);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSort();
            }
        });
        header.setVisibility(View.VISIBLE);
        subject.setText("نام");
        recycler.setLayoutManager(new LinearLayoutManager(context));
        setList();
    }

    public void showCategories() {
        isFirst = false;

        button.getChildAt(0).setBackgroundResource(R.drawable.ic_hardware_keyboard_backspace);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFirst();
            }
        });
        header.setVisibility(View.GONE);
        subject.setText("دسته بندی");
        recycler.setLayoutManager(new GridLayoutManager(context, 3));
        setCategories();
    }

    private void setCategories() {
        ArrayList<String> list = new ArrayList<>();
        list.add("دندان پزشکی");
        list.add("داروخانه");
        list.add("مشاوره");
        list.add("بیمارستان ها");
        list.add("رادیولوژی");
        list.add("کلینیک ها");
        list.add("پوست و مو");
        list.add("آزمایشگاه");
        list.add("تمام پیشنهادات");
        recycler.setAdapter(new MainGridAdaptor(context, dimen, list));
    }

    private void showSort() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(DialogView())
                .setPositiveButton("تایید", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (picker != null) {
                            Toast.makeText(context, "Value is : " + picker.getValue(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("لغو", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).setCancelable(true).setTitle("انتخاب منطقه");
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(context.getResources().getColor(R.color.dark));
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.DKGRAY);
    }

    private View DialogView() {
        RelativeLayout layout = new RelativeLayout(context);
        layout.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));

        picker = new NumberPicker(context);
        picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(-2, -2);
        p.addRule(RelativeLayout.CENTER_IN_PARENT);
        picker.setLayoutParams(p);
        picker.setMaxValue(22);
        picker.setMinValue(1);
        picker.setValue(1);

        layout.addView(picker);
        return layout;
    }
}
