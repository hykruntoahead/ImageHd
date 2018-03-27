package com.example.administrator.imagehd;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Created by Administrator on 2018/3/26.
 */

public class ImageHelper {
    /**
     * @param bm
     * @param hue        色相
     * @param saturation 饱和度
     * @param lum        亮度
     * @return
     */
    public static Bitmap handleImageEffect(Bitmap bm, float hue, float saturation, float lum) {
        Bitmap bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0, hue);
        hueMatrix.setRotate(1, hue);
        hueMatrix.setRotate(2, hue);

        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum, lum, lum, 1);

        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(lumMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        canvas.drawBitmap(bm, 0, 0, paint);

        return bmp;
    }

    //    底片效果
    public static Bitmap handleImageNegative(Bitmap bm) {

        int width = bm.getWidth();
        int height = bm.getHeight();
        int color;
        int r, g, b, a;

        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int[] oldPx = new int[width * height];
        int[] newPx = new int[width * height];

        bm.getPixels(oldPx, 0, width, 0, 0, width, height);

        for (int i = 0; i < width * height; i++) {
            color = oldPx[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            r = 255 - r;
            g = 255 - g;
            b = 255 - b;

            r = r > 255 ? 255 : r;
            r = r < 0 ? 0 : r;
            g = g > 255 ? 255 : g;
            g = g < 0 ? 0 : g;
            b = b > 255 ? 255 : b;
            b = b < 0 ? 0 : b;


            newPx[i] = Color.argb(a, r, g, b);
        }

        bmp.setPixels(newPx, 0, width, 0, 0, width, height);

        return bmp;
    }

    //    怀旧效果
    public static Bitmap handleImagePixelsOldPhoto(Bitmap bm) {

        int width = bm.getWidth();
        int height = bm.getHeight();
        int color;
        int r, g, b, a, r1, g1, b1;

        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int[] oldPx = new int[width * height];
        int[] newPx = new int[width * height];

        bm.getPixels(oldPx, 0, width, 0, 0, width, height);

        for (int i = 0; i < width * height; i++) {
            color = oldPx[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            r1 = (int) (0.393 * r + 0.769 * g +0.189 * b);
            g1 = (int) (0.349 * r + 0.686 * g +0.168 * b);
            b1 = (int) (0.272 * r + 0.534 * g +0.131 * b);

            r1 = r1 > 255 ? 255 : r1;
            r1 = r1 < 0 ? 0 : r1;
            g1 = g1 > 255 ? 255 : g1;
            g1 = g1 < 0 ? 0 : g1;
            b1 = b1 > 255 ? 255 : b1;
            b1 = b1 < 0 ? 0 : b1;


            newPx[i] = Color.argb(a, r1, g1, b1);
        }

        bmp.setPixels(newPx, 0, width, 0, 0, width, height);

        return bmp;
    }

    //    浮雕效果
    public static Bitmap handleImagePixelsRelief(Bitmap bm) {

        int width = bm.getWidth();
        int height = bm.getHeight();
        int color , colorBefore;
        int r, g, b, a, r1, g1, b1;

        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int[] oldPx = new int[width * height];
        int[] newPx = new int[width * height];

        bm.getPixels(oldPx, 0, width, 0, 0, width, height);

        for (int i = 1; i < width * height; i++) {
            colorBefore = oldPx[i - 1];
            a = Color.alpha(colorBefore);
            r = Color.red(colorBefore);
            g = Color.green(colorBefore);
            b = Color.blue(colorBefore);

            color  = oldPx[i];
            r1 = Color.red(color);
            g1 = Color.green(color);
            b1 = Color.blue(color);

            r = (r - r1 +127);
            g = (g - g1 +127);
            b = (b - b1 +127);

            if (r > 255) {
                r = 255;
            }
            if (g > 255) {
                g = 255;
            }
            if (b > 255) {
                b = 255;
            }


            newPx[i] = Color.argb(a, r, g, b);
        }

        bmp.setPixels(newPx, 0, width, 0, 0, width, height);

        return bmp;
    }
}
