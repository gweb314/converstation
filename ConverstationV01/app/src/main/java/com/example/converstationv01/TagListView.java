package com.example.converstationv01;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

@SuppressLint("AppCompatCustomView")
public class TagListView extends TextView {
    public TagListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setTags(ArrayList<String> tags)
    {
        SpannableStringBuilder ssb = new SpannableStringBuilder();


        for(String s: tags)
        {
            ssb.append(":");
            BitmapDrawable tag = convertViewToDrawable(createTag(s));
            tag.setBounds(0, 0, tag.getIntrinsicWidth(), tag.getIntrinsicHeight());
            ssb.setSpan(new ImageSpan(tag), ssb.length() - 1, ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            ssb.append(" ");
        }

        setText(ssb);
    }

    private View createTag(String text)
    {
        LinearLayout tag = new LinearLayout(getContext());
        tag.setOrientation(LinearLayout.HORIZONTAL);
        tag.setBackgroundResource(R.drawable.rounded_rect_bg);

        TextView tagText = new TextView(getContext());
        tagText.setText(text);
        tagText.setTextSize(TypedValue.COMPLEX_UNIT_PT, 36);

        tag.addView(tagText);
        return tag;
    }

    //From here:
    //https://stackoverflow.com/questions/10812316/contact-bubble-edittext/10864568#10864568
    private BitmapDrawable convertViewToDrawable(View view) {
        int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(spec, spec);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap b = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        c.translate(-view.getScrollX(), -view.getScrollY());
        view.draw(c);
        view.setDrawingCacheEnabled(true);
        Bitmap cacheBmp = view.getDrawingCache();
        Bitmap viewBmp = cacheBmp.copy(Bitmap.Config.ARGB_8888, true);
        view.destroyDrawingCache();
        return new BitmapDrawable(viewBmp);

    }
}
