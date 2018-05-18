package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice2DrawCircleView extends View {

    private Paint mCirclePaint1;
    private Paint mCirclePaint2;
    private Paint mCirclePaint3;
    private Paint mCirclePaint4;
    private int circleRadius;
    private int x1, x2;
    private int y1, y2;

    public Practice2DrawCircleView(Context context) {
        super(context);
        init();
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mCirclePaint1 = createPaint();
        mCirclePaint1.setStyle(Paint.Style.FILL);
        mCirclePaint1.setColor(Color.BLACK);

        mCirclePaint2 = createPaint();
        mCirclePaint2.setStyle(Paint.Style.STROKE);
        mCirclePaint2.setColor(Color.BLACK);
        mCirclePaint2.setStrokeWidth(5);

        mCirclePaint3 = createPaint();
        mCirclePaint3.setStyle(Paint.Style.FILL);
        mCirclePaint3.setColor(Color.BLUE);

        mCirclePaint4 = createPaint();
        mCirclePaint4.setStyle(Paint.Style.STROKE);
        mCirclePaint4.setColor(Color.BLACK);
        mCirclePaint4.setStrokeWidth(50);
    }

    private Paint createPaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        return paint;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        x1 = width / 4;
        x2 = width / 2 + x1;

        y1 = height / 4;
        y2 = height / 2 + y1;
        circleRadius = width/6;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        练习内容：使用 canvas.drawCircle() 方法画圆
//        一共四个圆：1.实心圆 2.空心圆 3.蓝色实心圆 4.线宽为 20 的空心圆

        canvas.drawCircle(x1, y1, circleRadius, mCirclePaint1);
        canvas.drawCircle(x2, y1, circleRadius, mCirclePaint2);
        canvas.drawCircle(x1, y2, circleRadius, mCirclePaint3);
        canvas.drawCircle(x2, y2, circleRadius, mCirclePaint4);
    }
}
