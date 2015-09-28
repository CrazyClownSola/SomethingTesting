package com.sola.testing.solatesting.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Property;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

/**
 * Description: View素材
 * <p/>
 * author: Sola
 * 2015/8/26
 */
public class MaterialView extends FrameLayout {

    // ===========================================================
    // Constants
    // ===========================================================

    private static final int DEFAULT_BACKGROUND = Color.TRANSPARENT;

    // ===========================================================
    // Fields
    // ===========================================================

    //水滴效果的半径，此半径会在draw方法当中进行绘制，然后通过开启动画效果，改变这个值以达到波纹的效果
    float radius;

    Point currentCoords = new Point();//当前点击的点的缓存
    Point previousCoords = new Point();//缓存的前一个触碰点

    final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);//画笔
    final Rect bounds = new Rect();

    Drawable rippleBackground;

    PressEvent pressEvent;//自定义的触发线程，作为动画启动的触发口

    boolean pressed;// 该控件是否已经被按下
    boolean eventCancelled; // 触碰事件取消，触发点在当移除当前界面的边框的时候

    View childView;

    GestureDetector gestureDetector;

    AnimatorSet rippleAnimator;
    ObjectAnimator hoverAnimator;
    // ===========================================================
    // Constructors
    // ===========================================================

    public MaterialView(Context context) {
        this(context, null, 0);
    }

    public MaterialView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaterialView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        gestureDetector = new GestureDetector(context, simpleGesture);
        rippleBackground = new ColorDrawable(DEFAULT_BACKGROUND);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public void setRippleAlpha(int alpha) {
        paint.setAlpha(alpha);
        invalidate();
    }

    public int getRippleAlpha() {
        return paint.getAlpha();
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        invalidate();
    }

    boolean hasPerformedLongPress;

    GestureDetector.SimpleOnGestureListener simpleGesture =
            new GestureDetector.SimpleOnGestureListener() {
                @Override
                public void onLongPress(MotionEvent e) {
                    hasPerformedLongPress = childView.performLongClick();
                    if (hasPerformedLongPress) {
                        startRipple();
                    }
                    cancelPressedEvent();
                }

                @Override
                public boolean onDown(MotionEvent e) {
                    hasPerformedLongPress = false;
                    return super.onDown(e);
                }
            };

    Property<MaterialView, Float> radiusProperty =
            new Property<MaterialView, Float>(Float.class, "radius") {
                @Override
                public Float get(MaterialView object) {
                    return object.getRadius();
                }

                @Override
                public void set(MaterialView object, Float value) {
                    object.setRadius(value);
                }
            };

    Property<MaterialView, Integer> cicleAlphaProperty
            = new Property<MaterialView, Integer>(Integer.class, "cicleAlpha") {
        @Override
        public Integer get(MaterialView object) {
            return object.getRippleAlpha();
        }

        @Override
        public void set(MaterialView object, Integer value) {
            object.setRippleAlpha(value);
        }
    };

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================


    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (getChildCount() > 0)
            throw new IllegalStateException("Material must have a child only ");
        childView = child;
        super.addView(child, index, params);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bounds.set(0, 0, w, h);
        rippleBackground.setBounds(bounds);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean superOnTouchEvent = super.onTouchEvent(event);
        if (!isEnabled()) return superOnTouchEvent;
        boolean isEventInBounds = bounds.contains((int) event.getX(), (int) event.getY());
        if (isEventInBounds) {
            previousCoords.set(currentCoords.x, currentCoords.y);
            currentCoords.set((int) event.getX(), (int) event.getY());
        }
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_UP:
                if (pressed) {
                    childView.setPressed(true);
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            childView.setPressed(false);
                        }
                    }, ViewConfiguration.getPressedStateDuration());
                }
                cancelPressedEvent();
                break;
            case MotionEvent.ACTION_CANCEL:
//                currentCoords.set();
                childView.onTouchEvent(event);
                if (!pressed)
                    startRipple();
                cancelPressedEvent();
                break;
            case MotionEvent.ACTION_DOWN:
                eventCancelled = false;
                pressEvent = new PressEvent(event);
                pressEvent.run();
                /***这里注意当这个控件是在ScrollView内部的时候的问题 ***/
                break;
            case MotionEvent.ACTION_MOVE:
                if (isEventInBounds && !eventCancelled)
                    invalidate();
                else if (!isEventInBounds)
                    startRipple();
                if (!isEventInBounds) {
                    cancelPressedEvent();
                    if (hoverAnimator != null)
                        hoverAnimator.cancel();
                    childView.onTouchEvent(event);
                    eventCancelled = true;
                }
                break;
        }
        return true;
    }


    @Override
    public void draw(Canvas canvas) {
        rippleBackground.draw(canvas);//
        super.draw(canvas);//这里super的位置是关键点，
        canvas.drawCircle(currentCoords.x, currentCoords.y, radius, paint);
    }


    // ===========================================================
    // Methods
    // ===========================================================

    /**
     * 启动悬停
     */
    private void startHover() {
        if (eventCancelled) return;
        if (hoverAnimator != null)
            hoverAnimator.cancel();
        final float radius = (float) (Math.sqrt(Math.pow(getWidth(), 2) + Math.pow(getHeight(), 2)) * 1.2f);
        hoverAnimator = ObjectAnimator.ofFloat(this, radiusProperty, 35, radius)
                .setDuration(2500);
//        ObjectAnimator.ofF
        hoverAnimator.setInterpolator(new LinearInterpolator());
        hoverAnimator.start();
    }

    /**
     * 开启脉动效果
     */
    private void startRipple() {
        if (eventCancelled) return;
        float endRadius = getEndRadius();
        cancelAnimations();

        rippleAnimator = new AnimatorSet();
        ObjectAnimator ripple = ObjectAnimator.ofFloat(
                this, radiusProperty, radius, endRadius);
        ripple.setDuration(350);
        ripple.setInterpolator(new DecelerateInterpolator());//在动画开始的地方快然后慢
        ObjectAnimator fade = ObjectAnimator.ofInt(
                this, cicleAlphaProperty, 125, 0);
        fade.setDuration(75);
        fade.setInterpolator(new AccelerateInterpolator());
        fade.setStartDelay(350 - 75 - 50);//这个延迟启动的意义不是很明确，稍后在考虑

        if (getRadius() > endRadius) {
            fade.setStartDelay(0);
            rippleAnimator.play(fade);
        } else {
            rippleAnimator.playTogether(ripple, fade);
        }
        rippleAnimator.start();
    }


    private void cancelPressedEvent() {
        if (pressEvent != null) {
            removeCallbacks(pressEvent);
            pressed = false;
        }
    }


    private void cancelAnimations() {
        if (rippleAnimator != null) {
            rippleAnimator.cancel();
            rippleAnimator.removeAllListeners();
        }

        if (hoverAnimator != null) {
            hoverAnimator.cancel();
        }
    }

    public float getEndRadius() {
        final int width = getWidth();
        final int height = getHeight();

        final int halfWidth = width / 2;
        final int halfHeight = height / 2;

        final float radiusX = halfWidth > currentCoords.x ? width - currentCoords.x : currentCoords.x;
        final float radiusY = halfHeight > currentCoords.y ? height - currentCoords.y : currentCoords.y;

        return (float) Math.sqrt(Math.pow(radiusX, 2) + Math.pow(radiusY, 2)) * 1.2f;

    }
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    private final class PressEvent implements Runnable {

        final MotionEvent event;

        private PressEvent(MotionEvent event) {
            this.event = event;
        }

        @Override
        public void run() {
            pressed = false;
            childView.setLongClickable(false);
            childView.onTouchEvent(event);
            childView.setPressed(true);
            startHover();
        }
    }


}
