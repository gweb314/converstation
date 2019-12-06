package com.example.converstationv01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;

public class ResourceManager
{
    public static void loadProfilePics(Context context)
    {
        if(profilePics != null) return;
        profilePics = new HashMap<String, Bitmap>();
        profilePics.put("userpic0", loadProfilePic(context, R.drawable.userpic0));
        profilePics.put("userpic1", loadProfilePic(context, R.drawable.userpic1));
        profilePics.put("userpic2", loadProfilePic(context, R.drawable.userpic2));
        profilePics.put("userpic3", loadProfilePic(context, R.drawable.userpic3));

        border = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.border)).getBitmap();
    }

    public static boolean picExists(String image)
    {
        return profilePics != null & image != null && profilePics.get(image) != null;
    }

    public static Bitmap loadProfilePic(Context context, int id)
    {
        System.out.println("Loading " + id);
        Bitmap bitmap = ((BitmapDrawable)context.getResources().getDrawable(id)).getBitmap();
        System.out.println("Loaded!\n");
        return bitmap;
    }

    public static void addNewProfilePic(String name, Bitmap image)
    {
        if(profilePics == null) return;
        profilePics.put(name, image);
    }

    public static Bitmap getProfilePic(String image)
    {
        if(profilePics == null) return null;
        if(!profilePics.containsKey(image)) return null;
        return profilePics.get(image);
    }

    public static void drawProfilePic(Canvas canvas, Paint paint, String image, int size, int width)
    {
        int imageRadius = (int)(size * .9);
        if(profilePics != null && image != null && image.length() > 0 && getProfilePic(image) != null) {
            canvas.drawBitmap(getProfilePic(image),
                    null, new Rect(width / 2 - imageRadius, size - imageRadius,
                            width / 2 + imageRadius, size + imageRadius), null);
        }
        else
        {
            Paint textPaint = new Paint();
            textPaint.setTextSize(64);
            canvas.drawText("Add a Profile Pic", imageRadius / 4, size, textPaint);
        }
        canvas.drawBitmap(border,
                null, new Rect(width / 2 - imageRadius, size - imageRadius,
                        width / 2 + imageRadius, size + imageRadius), null);
        canvas.drawCircle(width / 2, size, imageRadius, paint);
    }

    private static HashMap<String, Bitmap> profilePics;
    private static Bitmap border;
}
