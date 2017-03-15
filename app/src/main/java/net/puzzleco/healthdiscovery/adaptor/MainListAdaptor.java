package net.puzzleco.healthdiscovery.adaptor;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import net.puzzleco.healthdiscovery.R;
import net.puzzleco.healthdiscovery.activity.DetailActivity;
import net.puzzleco.healthdiscovery.activity.MainActivity;
import net.puzzleco.healthdiscovery.util.ViewUtil;
import net.puzzleco.healthdiscovery.view.Text;

import java.util.ArrayList;

public class MainListAdaptor extends RecyclerView.Adapter<MainListAdaptor.ViewHolder> implements View.OnClickListener {

    private static final int IMAGE_ID = +28484;
    private static final int TITLE_ID = +99985;

    private static final int NEW_PRICE = +54224;
    private static final int OLD_PRICE = +68485;
    private static final int ADDRESS_ID = +72872;
    private static final int COUNTER_ID = +89258;

    private MainActivity context;
    private float[] dimen;
    private ArrayList<String> list;
    private int image_size;

    public MainListAdaptor(MainActivity context, float[] dimen, ArrayList<String> list) {
        this.context = context;
        this.dimen = dimen;
        this.list = list;
        this.image_size = ViewUtil.toPx(140, context);
    }

    @Override
    public MainListAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = createItem(viewType);
        return new MainListAdaptor.ViewHolder(view);
    }

    private View createItem(int position) {
        CardView cardView = new CardView(context);

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-1, -2);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cardView.setElevation(3);
            p.height = ViewUtil.toPx(235, context);
        } else {
            p.height = ViewUtil.toPx(240, context);
        }
        p.topMargin = ViewUtil.toPx(8, context);
        p.bottomMargin = ViewUtil.toPx(8, context);
        p.leftMargin = (int) (dimen[0] / 15);
        p.rightMargin = (int) (dimen[0] / 15);
        cardView.setLayoutParams(p);
        cardView.setRadius(5);

        cardView.addView(image());
        cardView.addView(off());
        cardView.addView(textBox());
        cardView.addView(clickable(position));

        return cardView;
    }

    private View textBox() {
        LinearLayout layout = new LinearLayout(context);
        CardView.LayoutParams p = new CardView.LayoutParams(-1, -2);
        p.gravity = Gravity.CENTER_HORIZONTAL;
        layout.setLayoutParams(p);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(title());
        layout.addView(line());
        layout.addView(detail(false));
        layout.addView(line());
        layout.addView(detail(true));
        return layout;
    }

    private View clickable(int position) {
        View view = new View(context);
        ViewUtil.setBackground(view, context);
        view.setLayoutParams(new CardView.LayoutParams(-1, -1));
        view.setOnClickListener(this);
        return view;
    }

    private View detail(boolean isAddress) {
        int padding = ViewUtil.toPx(5, context);

        RelativeLayout layout = new RelativeLayout(context);
        layout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        layout.setPadding(0, padding, 0, padding);

        Text left = new Text(context);
        left.setSingleLine();
        left.setTextColor(Color.BLACK);
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(-2, -2);
        p.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        p.addRule(RelativeLayout.CENTER_VERTICAL);
        left.setLayoutParams(p);
        left.setPadding(padding, 0, padding, 0);

        Text right = new Text(context);
        right.setSingleLine();
        right.setTextColor(Color.BLACK);
        RelativeLayout.LayoutParams pp = new RelativeLayout.LayoutParams(-2, -2);
        pp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        pp.addRule(RelativeLayout.CENTER_VERTICAL);
        right.setLayoutParams(pp);
        right.setPadding(padding, 0, padding, 0);

        if (isAddress) {
            left.setId(COUNTER_ID);
            right.setId(ADDRESS_ID);
            pp.addRule(RelativeLayout.RIGHT_OF, COUNTER_ID);
        } else {
            left.setId(OLD_PRICE);
            right.setId(NEW_PRICE);
            left.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            pp.addRule(RelativeLayout.RIGHT_OF, OLD_PRICE);
        }

        layout.addView(left);
        layout.addView(right);
        return layout;
    }

    private View line() {
        View view = new View(context);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, ViewUtil.toPx(1, context)));
        view.setBackgroundColor(Color.LTGRAY);
        return view;
    }

    private View title() {
        Text text = new Text(context);
        text.setTextSize(1, 12);
        text.setId(TITLE_ID);
        text.setSingleLine();
        text.setTextColor(Color.BLACK);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-1, -2);
        p.topMargin = image_size;
        text.setLayoutParams(p);
        text.setGravity(Gravity.RIGHT);
        int padding = ViewUtil.toPx(5, context);
        text.setPadding(padding, padding, padding, padding);
        text.setTypeface(text.getTypeface(), Typeface.BOLD);
        return text;
    }

    private View off() {
        Text view = new Text(context);
        view.setEllipsize(TextUtils.TruncateAt.END);
        float height_time = 162f / 92f;
        int size = ViewUtil.toPx(35, context);
        CardView.LayoutParams p = new CardView.LayoutParams(size, (int) (height_time * size));
        p.gravity = Gravity.LEFT | Gravity.TOP;
        view.setLayoutParams(p);
        view.setBackgroundResource(R.mipmap.off);
        view.setSingleLine();
        view.setTextSize(1, 15);
        view.setTextColor(Color.WHITE);
        view.setGravity(Gravity.CENTER);
        view.setText("%10");
        return view;
    }

    private View image() {
        ImageView view = new ImageView(context);
        view.setId(IMAGE_ID);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        CardView.LayoutParams p = new CardView.LayoutParams(-1, image_size);
        p.gravity = Gravity.CENTER_HORIZONTAL;
        view.setLayoutParams(p);
        return view;
    }

    @Override
    public void onBindViewHolder(MainListAdaptor.ViewHolder holder, int position) {
        holder.imageView.setImageResource(R.mipmap.pic);
        holder.title.setText("آزمایش خون در آزمایشگاه رسالت");
        holder.oldPrice.setText(ViewUtil.number(75000) + " تومان");
        holder.newPrice.setText(ViewUtil.number(50000) + " تومان");
        holder.address.setText("رسالت, خیابان هنگام, کوچه 2");
        holder.counter.setText("37 خرید");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        Text title;
        Text oldPrice;
        Text newPrice;
        Text address;
        Text counter;

        public ViewHolder(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(IMAGE_ID);
            title = (Text) v.findViewById(TITLE_ID);
            oldPrice = (Text) v.findViewById(OLD_PRICE);
            newPrice = (Text) v.findViewById(NEW_PRICE);
            address = (Text) v.findViewById(ADDRESS_ID);
            counter = (Text) v.findViewById(COUNTER_ID);
        }
    }
}
