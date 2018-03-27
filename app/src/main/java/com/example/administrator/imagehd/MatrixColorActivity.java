package com.example.administrator.imagehd;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

/**
 * Created by Administrator on 2018/3/26.
 * 矩阵变换
 */

public class MatrixColorActivity extends Activity {
    private ImageView mImageView;
    private GridLayout mGroup;
    private Bitmap mBitmap;
    private int mEtWidth, mEtHeight;
    private EditText[] mEts = new EditText[20];
    private float[] mColorMatrix = new float[20];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_color);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test1);
        mImageView = findViewById(R.id.imageView);
        mGroup = findViewById(R.id.group);
        mImageView.setImageBitmap(mBitmap);

        mGroup.post(new Runnable() {
            @Override
            public void run() {
                mEtWidth = mGroup.getWidth() / 5;
                mEtHeight = mGroup.getHeight() / 4;
                addEts();
                initMatrix();
            }
        });
    }


    private void addEts() {
        for (int i = 0; i < 20; i++) {
            EditText editText = new EditText(this);
            mEts[i] = editText;
            mGroup.addView(editText, mEtWidth, mEtHeight);
        }
    }


    private void initMatrix() {
        for (int i = 0; i < 20; i++) {
            if (i % 6 == 0) {
                mEts[i].setText(String.valueOf(1));
            } else {
                mEts[i].setText(String.valueOf(0));
            }
        }
    }

    private void getMatrix() {
        for (int i = 0; i < 20; i++) {
            String s = TextUtils.isEmpty(mEts[i].getText().toString()) ? "0" : mEts[i].getText().toString();
            mColorMatrix[i] = Float.valueOf(s);
        }
    }

    private void setImageMatrix() {
        Bitmap bmp = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(mColorMatrix);

        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(mBitmap, 0, 0, paint);
        mImageView.setImageBitmap(bmp);
    }

    public void btnChange(View view) {
        getMatrix();
        setImageMatrix();
    }

    public void btnReset(View view) {
        initMatrix();
        getMatrix();
        setImageMatrix();
    }


}
