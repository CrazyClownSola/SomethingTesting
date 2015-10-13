package com.sola.testing.solatesting.view.radar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.UiThread;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EView;
import org.androidannotations.api.BackgroundExecutor;

/**
 * Description:
 * <p/>
 * author: Sola
 * 2015/10/12
 */
@EView
public class MyRadarView extends View {

    // ===========================================================
    // Constants
    // ===========================================================

    private final int circleColor = Color.parseColor("#a2a2a2");
    private final int radarColor = Color.parseColor("#99a2a2a2");
    private final int tailColor = Color.parseColor("#50aaaaaa");

    // ===========================================================
    // Fields
    // ===========================================================

    int start = 0;

    int centerX;

    int centerY;

    int radarRadius;

    Paint circlePaint;

    Paint radarPaint;

    Matrix matrix;

//    ProgressBar

    // ===========================================================
    // Constructors
    // ===========================================================
    public MyRadarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, context);
    }

    public MyRadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, context);
    }

    public MyRadarView(Context context) {
        super(context);
        init(null, context);
    }
    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
        radarRadius = Math.min(w, h);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(centerX, centerY, radarRadius / 7, circlePaint);
        canvas.drawCircle(centerX, centerY, radarRadius / 4, circlePaint);
        canvas.drawCircle(centerX, centerY, radarRadius / 3, circlePaint);
        canvas.drawCircle(centerX, centerY, 3 * radarRadius / 7, circlePaint);

        Shader shader = new SweepGradient(centerX, centerY, Color.TRANSPARENT, tailColor);
        radarPaint.setShader(shader);
        canvas.concat(matrix);
        canvas.drawCircle(centerX, centerY, 3 * radarRadius / 7, radarPaint);
    }


    // ===========================================================
    // Methods
    // ===========================================================

    private void init(AttributeSet attrs, Context context) {
        initPaint();
        matrix = new Matrix();

    }

    private void initPaint() {
        circlePaint = new Paint();
        circlePaint.setColor(circleColor);
        circlePaint.setAntiAlias(true);//抗锯齿
        circlePaint.setStyle(Paint.Style.STROKE);//设置实心
        circlePaint.setStrokeWidth(2);//画笔宽度

        radarPaint = new Paint();
        radarPaint.setColor(radarColor);
        radarPaint.setAntiAlias(true);
    }

    @UiThread
    @Background(delay = 10, id = "radar")
    public void startRadar() {
        BackgroundExecutor.cancelAll("radar", true);// 停止旋转
//        postDelayed(new Runnable() {
//            @Override
//            public void run() {
        Log.d("Sola", "RUN!!");
        start += 2;
        matrix = new Matrix();
        matrix.postRotate(start, centerX, centerY);
        postInvalidate();
        startRadar();
//            }
//        }, 10);
    }


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
