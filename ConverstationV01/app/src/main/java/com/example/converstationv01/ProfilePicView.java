package com.example.converstationv01;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
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
        bgPaint.setColor(getResources().getColor(R.color.colorPrimary));

        setImage("userpic0");
    }

    Paint bgPaint;
    int size;
    Bitmap image;

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth() / 2, size, size, bgPaint);
        int imageRadius = (int)(size * .9);
        canvas.drawBitmap(image,
                null, new Rect(getWidth() / 2 - imageRadius, size - imageRadius,
                        getWidth() / 2 + imageRadius, size + imageRadius), null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(size * 2, size * 2);
    }

    public void setImage(String imageString)
    {
        if(imageString.equals("userpic0"))
        {
            Bitmap temp = ((BitmapDrawable)getResources().getDrawable(R.drawable.userpic0)).getBitmap();
            image = temp;//copy(temp.getConfig(), true);
        }
        else if(imageString.equals("userpic1"))
        {
            Bitmap temp = ((BitmapDrawable)getResources().getDrawable(R.drawable.userpic1)).getBitmap();
            image = temp;//.copy(temp.getConfig(), true);
        }
        else if(imageString.equals("userpic2"))
        {
            Bitmap temp = ((BitmapDrawable)getResources().getDrawable(R.drawable.userpic2)).getBitmap();
            image = temp;//copy(temp.getConfig(), true);
        }
        else if(imageString.equals("userpic3"))
        {
            Bitmap temp = ((BitmapDrawable)getResources().getDrawable(R.drawable.userpic3)).getBitmap();
            image = temp;//copy(temp.getConfig(), true);
        }
        else
        {
            Bitmap temp = ((BitmapDrawable)getResources().getDrawable(R.drawable.userpic0)).getBitmap();
            image = temp;//copy(temp.getConfig(), true);
        }

        image.setHasAlpha(true);
        //cropCircle(image);
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
