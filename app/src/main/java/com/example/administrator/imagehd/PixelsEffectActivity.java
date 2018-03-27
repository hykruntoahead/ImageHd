package com.example.administrator.imagehd;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

/**
 * Created by Administrator on 2018/3/27.
 */

public class PixelsEffectActivity extends Activity {
    private ImageView mImageView1;
    private ImageView mImageView2;
    private ImageView mImageView3;
    private ImageView mImageView4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pixels_effect);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test2);

        mImageView1 = findViewById(R.id.imageView1);
        mImageView2 = findViewById(R.id.imageView2);
        mImageView3 = findViewById(R.id.imageView3);
        mImageView4 = findViewById(R.id.imageView4);

        mImageView1.setImageBitmap(bitmap);
        mImageView2.setImageBitmap(ImageHelper.handleImageNegative(bitmap));
        mImageView3.setImageBitmap(ImageHelper.handleImagePixelsOldPhoto(bitmap));
        mImageView4.setImageBitmap(ImageHelper.handleImagePixelsRelief(bitmap));
    }
}
