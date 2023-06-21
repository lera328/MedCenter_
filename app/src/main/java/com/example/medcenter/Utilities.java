package com.example.medcenter;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import androidx.annotation.NonNull;

import java.io.ByteArrayOutputStream;

public class Utilities {

    // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
    public static Bitmap to4BytesPerPixelBitmap(@NonNull final Bitmap input){
        final Bitmap bitmap = Bitmap.createBitmap(input.getWidth(), input.getHeight(), Bitmap.Config.ARGB_8888);
        // Instantiate the canvas to draw on:
        final Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(input, 0, 0, null);
        // Return the new bitmap:
        return bitmap;
    }
}
