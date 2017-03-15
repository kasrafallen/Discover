package net.puzzleco.healthdiscovery.adaptor;

import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import net.puzzleco.healthdiscovery.R;
import net.puzzleco.healthdiscovery.activity.MainActivity;
import net.puzzleco.healthdiscovery.util.ViewUtil;
import net.puzzleco.healthdiscovery.view.Text;

import java.util.ArrayList;

public class MainGridAdaptor extends RecyclerView.Adapter<MainGridAdaptor.ViewHolder> {

    private static final int IMAGE_ID = +28084;
    private static final int TITLE_ID = +99085;

    private MainActivity context;
    private float[] dimen;
    private ArrayList<String> list;

    public MainGridAdaptor(MainActivity context, float[] dimen, ArrayList<String> list) {
        this.context = context;
        this.dimen = dimen;
        this.list = list;
    }

    @Override
    public MainGridAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = createItem(viewType);
        return new MainGridAdaptor.ViewHolder(view);
    }

    private View createItem(int position) {
        CardView cardView = new CardView(context);

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-1, -2);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (position == list.size() - 1) {
                cardView.setElevation(8);
            } else {
                cardView.setElevation(3);
            }
            p.height = ViewUtil.toPx(140, context);
        } else {
            p.height = ViewUtil.toPx(145, context);
        }
        p.topMargin = ViewUtil.toPx(8, context);
        p.bottomMargin = ViewUtil.toPx(8, context);
        p.leftMargin = (int) (dimen[0] / 50);
        p.rightMargin = (int) (dimen[0] / 50);
        cardView.setLayoutParams(p);
        cardView.setRadius(5);

        cardView.addView(box());
        cardView.addView(clickable(position));
        return cardView;
    }

    private View box() {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        CardView.LayoutParams p = new CardView.LayoutParams(-1, -1);
        p.gravity = Gravity.CENTER_HORIZONTAL;
        layout.setLayoutParams(p);
        layout.addView(image());
        layout.addView(line());
        layout.addView(title());
        return layout;
    }

    private View clickable(int position) {
        View view = new View(context);
        ViewUtil.setBackground(view, context);
        view.setLayoutParams(new CardView.LayoutParams(-1, -1));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

    private View line() {
        View view = new View(context);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, ViewUtil.toPx(1, context)));
        view.setBackgroundResource(R.color.theme);
        return view;
    }

    private View title() {
        Text text = new Text(context);
        text.setId(TITLE_ID);
        text.setSingleLine();
        text.setTextColor(context.getResources().getColor(R.color.theme));
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-1, -2);
        text.setLayoutParams(p);
        text.setTextSize(1, 13);
        text.setGravity(Gravity.CENTER_HORIZONTAL);
        text.setTypeface(text.getTypeface(), Typeface.BOLD);
        return text;
    }

    private View image() {
        RelativeLayout layout = new RelativeLayout(context);
        layout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2, 1f));

        ImageView view = new ImageView(context);
        view.setId(IMAGE_ID);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewUtil.toPx(25, context), ViewUtil.toPx(25, context));
        p.addRule(RelativeLayout.CENTER_IN_PARENT);
        view.setLayoutParams(p);

        layout.addView(view);
        return layout;
    }

    @Override
    public void onBindViewHolder(MainGridAdaptor.ViewHolder holder, int position) {
        holder.imageView.setImageResource(R.mipmap.profile);
        holder.title.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        Text title;

        public ViewHolder(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(IMAGE_ID);
            title = (Text) v.findViewById(TITLE_ID);
        }
    }
}
