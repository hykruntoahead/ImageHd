package com.example.administrator.imagehd;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.SeekBar;

/**
 * Created by Administrator on 2018/3/26.
 * 色调 饱和度 亮度 设置
 */

public class PrimaryColorActivity extends Activity implements SeekBar.OnSeekBarChangeListener {
    private ImageView mImageView;
    private SeekBar mSeekBarHue, mSeekBarSaturation, mSeekBarLum;
    private static int MAX_VALUE = 255;
    private static int MID_VALUE = 127;

    private float mHum, mSaturation, mLum;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_primary_color);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test3);

        mImageView = findViewById(R.id.imageView);
        mSeekBarHue = findViewById(R.id.seekBarHue);
        mSeekBarSaturation = findViewById(R.id.seekBarSaturation);
        mSeekBarLum = findViewById(R.id.seekBarLum);

        mSeekBarHue.setOnSeekBarChangeListener(this);
        mSeekBarSaturation.setOnSeekBarChangeListener(this);
        mSeekBarLum.setOnSeekBarChangeListener(this);

        mSeekBarHue.setMax(MAX_VALUE);
        mSeekBarSaturation.setMax(MAX_VALUE);
        mSeekBarLum.setMax(MAX_VALUE);

        mSeekBarHue.setProgress(MID_VALUE);
        mSeekBarSaturation.setProgress(MID_VALUE);
        mSeekBarLum.setProgress(MID_VALUE);

        mImageView.setImageBitmap(mBitmap);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        switch (seekBar.getId()) {
            case R.id.seekBarHue:
                mHum = (progress - MID_VALUE) * 1.0f / MID_VALUE * 180;
                break;
            case R.id.seekBarSaturation:
                mSaturation = progress * 1.0f / MID_VALUE;
                break;
            case R.id.seekBarLum:
                mLum = progress * 1.0f / MID_VALUE;
                break;
            default:
                break;
        }
        mImageView.setImageBitmap(ImageHelper.handleImageEffect(mBitmap, mHum, mSaturation, mLum));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
