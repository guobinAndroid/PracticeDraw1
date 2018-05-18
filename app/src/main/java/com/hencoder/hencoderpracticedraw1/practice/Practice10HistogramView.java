package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.model.HistogramData;

import java.util.ArrayList;
import java.util.List;

public class Practice10HistogramView extends View {

    private Paint mLinePaint; //画坐标Paint
    private Paint mTextPaint; //画直方图三个字
    private Paint mTitlePaint;//画标题
    private Paint mDirectPaint;//画柱子

    private int mDirectSpace; //每个柱子的间距
    private int mDirectWidth;//每个柱子的宽度
    private int mHistogramWidth;//横坐标宽度
    private int mHistogramHeight;//纵坐标
    private int mTitleHeight; //标题高度
    private int mTextHeight;//直方图三个的高度

    private float mScale = 0.25f;

    private List<HistogramData> histogramData = new ArrayList<>();
    //坐标原点
    private PointF mBasePointF;

    public Practice10HistogramView(Context context) {
        super(context);
        init();
    }

    public Practice10HistogramView(Context context,@Nullable AttributeSet attrs) {
        super(context,attrs);
        init();
    }

    public Practice10HistogramView(Context context,@Nullable AttributeSet attrs,int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        init();
    }

    private void init() {
        setPadding(100,50,100,0);
        mDirectPaint = new Paint();
        mDirectPaint.setAntiAlias(true);
        mDirectPaint.setDither(true);
        mDirectPaint.setColor(Color.GREEN);

        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setDither(true);
        mLinePaint.setColor(Color.WHITE);
        mLinePaint.setStrokeWidth(2);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setDither(true);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,50,getContext().getResources().getDisplayMetrics()));

        mTitlePaint = new Paint();
        mTitlePaint.setAntiAlias(true);
        mTitlePaint.setDither(true);
        mTitlePaint.setColor(Color.WHITE);
        mTitlePaint.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,28,getContext().getResources().getDisplayMetrics()));

        histogramData.add(new HistogramData("Froyo",Color.GREEN,10));
        histogramData.add(new HistogramData("GB",Color.GREEN,30));
        histogramData.add(new HistogramData("IC S",Color.GREEN,30));
        histogramData.add(new HistogramData("JB",Color.GREEN,240));
        histogramData.add(new HistogramData("KitKat",Color.GREEN,300));
        histogramData.add(new HistogramData("L",Color.GREEN,400));
        histogramData.add(new HistogramData("M",Color.GREEN,180));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        int height = MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop() - getPaddingBottom();

        mTitleHeight = (int) (height * 0.2f);
        mTextHeight = (int) (height * 0.08f);

        mHistogramWidth = width;
        mHistogramHeight = height - mTitleHeight - mTextHeight;
        mBasePointF = new PointF(getPaddingLeft(),mHistogramHeight);
        mDirectWidth = (int) (mHistogramWidth / histogramData.size() - mHistogramWidth / (histogramData.size() + 1) * mScale);
        mDirectSpace = (int) (mDirectWidth * mScale);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawText("直方图",(mBasePointF.x + mHistogramWidth) / 2,mHistogramHeight + mTextHeight + mTitleHeight,mTextPaint);
        canvas.drawLine(mBasePointF.x,getPaddingTop(),mBasePointF.x,mBasePointF.y,mLinePaint);
        canvas.drawLine(mBasePointF.x,mBasePointF.y,mBasePointF.x + mHistogramWidth,mBasePointF.y,mLinePaint);
        canvas.translate(mBasePointF.x,mBasePointF.y);

        int left = mDirectSpace;
        for (int i = 0; i < histogramData.size(); i++) {
            //drawRect
            int top = histogramData.get(i).getValue();//此处埋了一个坑。因为未知直方图最大值是多少，所以这边的高度有可能会超出纵轴的白线
            canvas.drawRect(new Rect(left,-top,left + mDirectWidth,0),mDirectPaint);
            //drawText
            int textWidth = (int) mTitlePaint.measureText(histogramData.get(i).getTitle());
            int textLeft = (mDirectWidth - textWidth) / 2;
            int textHeight = getTextHeight(mTitlePaint);
            canvas.drawText(histogramData.get(i).getTitle(),left + textLeft,textHeight,mTitlePaint);

            left += (mDirectWidth + mDirectSpace);
        }
    }

    private int getTextHeight(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return (int) (fontMetrics.bottom - fontMetrics.top);
    }
}