package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice6DrawLineView extends View {

    private Paint mPaint;
    private PointF centerPoint;

    public Practice6DrawLineView(Context context) {
        super(context);
        init();
    }

    public Practice6DrawLineView(Context context,@Nullable AttributeSet attrs) {
        super(context,attrs);
        init();
    }

    public Practice6DrawLineView(Context context,@Nullable AttributeSet attrs,int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        centerPoint = new PointF(width / 2,height / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawLine() 方法画直线
        canvas.drawLine(centerPoint.x - 200,centerPoint.y - 150,centerPoint.x + 200,centerPoint.y + 150 ,mPaint);
    }
}
