package com.example.converstationv01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class DateBar extends View
{
    public DateBar(Context context, AttributeSet attrset)
    {
        super(context, attrset);
        backgroundPaint = new Paint();
        backgroundPaint.setColor(getResources().getColor(R.color.colorAccent));

        dividerPaint = new Paint();
        dividerPaint.setColor(getResources().getColor(android.R.color.darker_gray));
        dividerPaint.setStrokeWidth(4);
        dividerPaint.setTextSize(32);
        dividerPaint.setTextAlign(Paint.Align.RIGHT);

        textPaint = new Paint();
        textPaint.setColor(getResources().getColor(android.R.color.white));
        textPaint.setTextSize(64);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    Paint backgroundPaint;
    Paint dividerPaint;
    Paint textPaint;

    String[] days = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int calendarWidth = getWidth() - 200;
        int calendarHeight = getHeight();
        canvas.drawRect(100, 0, calendarWidth + 100, calendarHeight, backgroundPaint);
        for (int i = 1; i <= 7; i++) {
            int x = calendarWidth / 7 * i;
            if (i < 7) {
                canvas.drawLine(x + 100, 0, x + 100, calendarHeight, dividerPaint);
            }

            int textX = x - calendarWidth / 14;
            canvas.drawText(days[i - 1], textX + 100, 64, textPaint);
        }

        canvas.drawLine(100, 80, calendarWidth + 100, 80, dividerPaint);
    }
}
