package edu.stanford.converstationv0;

import android.content.Context;
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

        bgPaint = new Paint();
        bgPaint.setColor(getResources().getColor(R.color.colorAccent));

        size = 300;

        image = ((BitmapDrawable)getResources().getDrawable(R.drawable.userpic0)).getBitmap();
        image = image.copy(image.getConfig(), true);
        image.setHasAlpha(true);
        cropCircle(image);
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
        setMeasuredDimension(widthMeasureSpec, size * 2);
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
