package net.puzzleco.healthdiscovery.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import net.puzzleco.healthdiscovery.R;

import java.text.NumberFormat;

public class ViewUtil {
    public static void setBackground(View view, Context context) {
        int[] attrs = new int[]{R.attr.selectableItemBackground};
        TypedArray typedArray = context.obtainStyledAttributes(attrs);
        int backgroundResource = typedArray.getResourceId(0, 0);
        view.setBackgroundResource(backgroundResource);
        typedArray.recycle();
    }

    public static void set(TextView tv, Context context) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/font.ttf");
        tv.setTypeface(typeface);
    }

    public static String number(double value) {
        return NumberFormat.getNumberInstance().format(value);
    }

    public static String year(long value) {
        return String.valueOf(value);
    }

    public static int toPx(int i, Context context) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, i, context.getResources().getDisplayMetrics());
        return (int) px;
    }

    public static void setBar(Activity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            context.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            context.getWindow().setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        }
    }
}
