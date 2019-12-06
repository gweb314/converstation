package edu.stanford.converstationv0;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class CalendarView extends View
{
    public CalendarView(Context context, AttributeSet attrset)
    {
        super(context, attrset);
        backgroundPaint = new Paint();
        backgroundPaint.setColor(getResources().getColor(android.R.color.secondary_text_dark));

        dividerPaint = new Paint();
        dividerPaint.setColor(getResources().getColor(android.R.color.darker_gray));
        dividerPaint.setStrokeWidth(4);
        dividerPaint.setTextSize(32);
        dividerPaint.setTextAlign(Paint.Align.RIGHT);

        textPaint = new Paint();
        textPaint.setColor(getResources().getColor(android.R.color.white));
        textPaint.setTextSize(64);
        textPaint.setTextAlign(Paint.Align.CENTER);

        planPaint = new Paint();
        planPaint.setColor(getResources().getColor(R.color.colorPrimaryDark));

        highlightPaint = new Paint();
        highlightPaint.setColor(Color.parseColor("#ff3232"));
        highlightPaint.setStyle(Paint.Style.STROKE);
        highlightPaint.setStrokeWidth(16);


        plans = new ArrayList<Plan>();
        selected = null;
        button = null;
    }

    Paint backgroundPaint;
    Paint dividerPaint;
    Paint textPaint;
    Paint planPaint;
    Paint highlightPaint;

    String[] days = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

    ArrayList<Plan> plans;
    Plan selected;

    Button button;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Getting coordinates
        float x = event.getX();
        float y = event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN && plans != null)
        {
            boolean previouslySelected = selected!=null;
            selected = null;
            int calendarWidth = getWidth() - 200;
            int calendarHeight = getHeight();
            int barHeight = calendarHeight - 80;
            for(Plan plan: plans)
            {
                int x1 = plan.getDayInt() * calendarWidth / 7 + (calendarWidth/7/20) + 100;
                int x2 = x1 + calendarWidth/7 - calendarWidth/7/10 + 100;
                int y1 = 80 + (int)(((float)plan.getStartInt()) / Plan.MAXTIME * barHeight);
                int y2 = 80 + (int)(((float)plan.getEndInt()) / Plan.MAXTIME * barHeight);

                if(x >= x1 && x <= x2 && y >= y1 && y <= y2)
                {
                    selected = plan;
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
        int calendarWidth = getWidth() - 200;
        int calendarHeight = getHeight();
        canvas.drawRect(100, 0, calendarWidth + 100, calendarHeight, backgroundPaint);
        for(int i = 1; i <= 7; i++)
        {
            int x = calendarWidth/7*i;
            if(i < 7)
            {
                canvas.drawLine(x + 100, 0, x + 100, calendarHeight, dividerPaint);
            }

            int textX = x - calendarWidth/14;
            canvas.drawText(days[i-1], textX + 100, 64, textPaint);
        }

        canvas.drawLine(100, 80, calendarWidth + 100, 80, dividerPaint);

        int barHeight = calendarHeight - 80;

        if(plans != null)
        {
            for (Plan plan : plans) {
                int x1 = plan.getDayInt() * calendarWidth / 7 + (calendarWidth / 7 / 20);
                int x2 = x1 + calendarWidth / 7 - calendarWidth / 7 / 10;
                int y1 = 80 + (int) (((float) plan.getStartInt()) / Plan.MAXTIME * barHeight);
                int y2 = 80 + (int) (((float) plan.getEndInt()) / Plan.MAXTIME * barHeight);

                canvas.drawRect(x1 + 100, y1, x2 + 100, y2, planPaint);
                if (selected == plan) {
                    canvas.drawRect(x1 + 100, y1, x2 + 100, y2, highlightPaint);
                }
            }
        }

        for(int i = 1; i < 24; i++)
        {
            int y = 80 + barHeight / 24 * i;
            canvas.drawText("" + ((i-1) % 12 + 1) + (i < 12 ? "AM" : "PM"), 90, y + 8, dividerPaint);
            canvas.drawLine(100, y, calendarWidth + 100, y, dividerPaint);
        }

        if(selected == null)
        {
            button.setClickable(false);
            button.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
            button.setTextColor(getResources().getColor(android.R.color.secondary_text_dark));
        }
        else
        {
            button.setClickable(true);
            button.setBackgroundColor(Color.parseColor("#ff3232"));
            button.setTextColor(getResources().getColor(android.R.color.white));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec * 4);
    }

    public void setPlans(ArrayList<Plan> plans)
    {
        this.plans = plans;
        selected = null;
        invalidate();
    }

    public void setDeleteButton(Button button)
    {
        this.button = button;
    }

    public Plan getSelected()
    {
        return selected;
    }
}
