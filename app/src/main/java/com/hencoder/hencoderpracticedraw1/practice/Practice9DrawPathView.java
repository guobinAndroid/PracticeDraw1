package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice9DrawPathView extends View {

    private Paint mPaint;
    private Path path;

    public Practice9DrawPathView(Context context) {
        super(context);
        init();
    }

    public Practice9DrawPathView(Context context,@Nullable AttributeSet attrs) {
        super(context,attrs);
        init();
    }

    public Practice9DrawPathView(Context context,@Nullable AttributeSet attrs,int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLACK);
        path = new Path();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //爱心坐标需要计算，懒，直接抄凯歌的
//        练习内容：使用 canvas.drawPath() 方法画心形
        path.addArc(new RectF(200,200,400,400),-225,225);
        path.arcTo(new RectF(400,200,600,400),-180,225,false);
        path.lineTo(400,542);
        canvas.drawPath(path,mPaint);
    }
}
