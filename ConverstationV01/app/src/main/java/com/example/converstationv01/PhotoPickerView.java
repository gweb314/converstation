package com.example.converstationv01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class PhotoPickerView extends View {
    public PhotoPickerView(Context context, AttributeSet attrset)
    {
        super(context, attrset);

        pics = new String[]{"random_user_alex", "random_user_alicia", "random_user_ben", "random_user_brian",
                "random_user_eric", "random_user_hope", "random_user_jay", "random_user_josh",
                "random_user_julia", "random_user_milen", "random_user_neil", "random_user_nick"};

        highlightPaint = new Paint();
        highlightPaint.setColor(getResources().getColor(R.color.colorAccent));
        highlightPaint.setStyle(Paint.Style.STROKE);
        highlightPaint.setStrokeWidth(16);


        selected = -1;
        button = null;
    }

    String[] pics;

    Paint highlightPaint;

    int selected;

    Button button;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Getting coordinates
        float x = event.getX();
        float y = event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            boolean previouslySelected = selected > 0;
            selected = -1;

            for(int i = 0; i < pics.length; i++)
            {
                int x1 = i % 3;
                x1 *= 400;
                int y1 = i / 3;
                y1 *= 400;
                y1 += 10;
                int size = 300;
                x1 += getWidth() / 2 - 400f * 3 / 2 + 50f;

                int x2 = x1 + 300;
                int y2 = y1 + 300;

                if(x >= x1 && x <= x2 && y >= y1 && y <= y2)
                {
                    selected = i;
                    invalidate();
                    break;
                }
            }
            if(previouslySelected)
            {
                invalidate();
            }
            return false;
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i = 0; i < pics.length; i++)
        {
            int x = i % 3;
            x *= 400;
            int y = i / 3;
            y *= 400;
            y += 10;
            int size = 300;
            x += getWidth() / 2 - 400f * 3 / 2 + 50f;
            canvas.drawBitmap(ResourceManager.getProfilePic(pics[i]), null, new Rect(x, y, x + size, y + size), new Paint());

            if(i == selected)
            {
                canvas.drawRect(x, y, x  + size, y + size, highlightPaint);
            }
        }

        if(selected < 0)
        {
            button.setClickable(false);
            button.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
            button.setTextColor(getResources().getColor(android.R.color.secondary_text_dark));
        }
        else
        {
            button.setClickable(true);
            button.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            button.setTextColor(getResources().getColor(android.R.color.white));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    public void setPickButton(Button button)
    {
        this.button = button;
    }

    public String getSelected()
    {
        if(pics == null || selected < 0 || selected >= pics.length) return null;
        return pics[selected];
    }
}
