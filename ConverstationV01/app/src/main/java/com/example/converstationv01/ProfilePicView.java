package com.example.converstationv01;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ProfilePicView extends View
{
    public ProfilePicView(Context context, AttributeSet attrset)
    {
        super(context, attrset);

        size = 300;

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrset,
                R.styleable.ProfilePicView,
                0, 0);

        try {
            size = a.getInt(R.styleable.ProfilePicView_size, 300);
        } finally {
            a.recycle();
        }

        bgPaint = new Paint();
        bgPaint.setColor(getResources().getColor(R.color.colorPrimaryDark));
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setStrokeWidth(25);

        setImage("userpic0");
    }

    Paint bgPaint;
    int size;
    String image;

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        ResourceManager.drawProfilePic(canvas, bgPaint, image, size, getWidth());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(size * 2, size * 2);
    }

    public void setImage(String imageString)
    {
        image = imageString;
        invalidate();
    }

    private void cropCircle(Bitmap bitmap)
    {
        float width = bitmap.getWidth() / 2;
        float height = bitmap.getHeight() / 2;

        for(int x = 0; x < bitmap.getWidth(); x++)
        {
            for(int y = 0; y < bitmap.getHeight(); y++)
            {
                float curX = x - width;
                float curY = y - height;
                if((curX*curX)/(width*width) + (curY*curY)/(height*height) > 1)
                {
                    bitmap.setPixel(x, y, Color.TRANSPARENT);
                }
            }
        }
    }
}
