package com.magnit.testandroid.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.magnit.testandroid.R;

public class Button extends AppCompatButton {
    private float mRatio;
    private int mColor = Color.GREEN;

    public Button(Context context) {
        super(context);
    }

    public Button(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Button(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
      }

    public void setRatio(float ratio) {
        this.mRatio = ratio;
        invalidate();
    }

    public float getMyRatio() {
        return mRatio;
    }

    public void setMyColor(int myColor) {
        this.mColor = myColor;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable fill = getResources().getDrawable(R.drawable.abc_btn_default_mtrl_shape);
        fill.setColorFilter(mColor, PorterDuff.Mode.MULTIPLY);
        fill.setAlpha(128);
        fill.setBounds(0, 0, (int) (getWidth() * mRatio), getHeight());
        fill.draw(canvas);
        super.onDraw(canvas);
    }
}