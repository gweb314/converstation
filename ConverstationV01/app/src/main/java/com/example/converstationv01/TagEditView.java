package com.example.converstationv01;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
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
public class TagEditView extends EditText {
    public TagEditView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        tags = new ArrayList<String>();
        lastText = "";

        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                System.out.println("Last: \"" + lastText + "\"");
                System.out.println("Curr: \"" + text + "\"");
                if(!text.equals(lastText))
                {
                    format();
                }
            }
        };

        addTextChangedListener(textWatcher);
    }

    ArrayList<String> tags;
    TextWatcher textWatcher;
    String lastText;

    public ArrayList<String> getTags()
    {
        ArrayList<String> retval = new ArrayList<String>();
        for(String s: tags) retval.add(s);
        return retval;
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd)
    {
        super.onSelectionChanged(selStart, selEnd);
        if(selStart != selEnd)
        {
            setSelection(selStart, selStart);
        }
        else if(getText().length() > 0 && selStart > 0 && selStart < getText().length() && getText().charAt(selStart - 1) == ' ')
        {
            setSelection(selStart - 1);
        }
    }

    private void format()
    {
        tags.clear();

        String text = getText().toString();

        if(text.length() == 0)
        {
            lastText = "";
            return;
        }

        text = text.replace('\n', ' ');

        int selection = getSelectionStart();

        String[] textArray = text.split(" ");
        boolean lastDone = text.charAt(text.length() - 1) == ' ';

        SpannableStringBuilder ssb = new SpannableStringBuilder();

        for(int i = 0; i < textArray.length; i++)
        {
            String s = textArray[i];
            if(s.length() == 0) continue;

            tags.add(s);
            int startIndex = ssb.length();
            ssb.append(s);
            int endIndex = ssb.length();
            BitmapDrawable tag = convertViewToDrawable(createTag(s));
            tag.setBounds(0, 0, tag.getIntrinsicWidth(), tag.getIntrinsicHeight());
            ssb.setSpan(new ImageSpan(tag), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            if(i < textArray.length - 1 || lastDone) {
                ssb.append(" ");
            }
        }

        System.out.println("New text = \"" + ssb.toString() + "\"");

        lastText = ssb.toString();
        setText(ssb);

        setSelection(Math.min(selection, ssb.length()));
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
