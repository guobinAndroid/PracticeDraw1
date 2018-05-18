package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.model.PieChartData;

import java.util.ArrayList;
import java.util.List;

public class Practice11PieChartView extends View {
    private Paint mLinePaint;
    private Paint mSpacePaint;
    private Paint mTitlePaint;
    private Paint mTextPaint;
    private Paint mArcPaint;

    private List<PieChartData> pieChartData = new ArrayList<>();

    private float mStartAngle = 0; //起始角度
    private int mAbsWidth;
    private int mAbsHeight;
    private int mBottomTextHeight;
    private int mCircleRadius;
    private RectF mRectF = new RectF();
    private float mCircleCenterX;
    private float mCircleCenterY;

    private float mTotalCount;
    private float mMaxCount;

    public Practice11PieChartView(Context context) {
        super(context);
        init();
    }

    public Practice11PieChartView(Context context,@Nullable AttributeSet attrs) {
        super(context,attrs);
        init();
    }

    public Practice11PieChartView(Context context,@Nullable AttributeSet attrs,int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        init();
    }

    private void init() {
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setDither(true);

        mSpacePaint = new Paint();
        mSpacePaint.setAntiAlias(true);
        mSpacePaint.setDither(true);
        mSpacePaint.setColor(Color.TRANSPARENT);

        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setDither(true);
        mLinePaint.setColor(Color.WHITE);

        mTitlePaint = new Paint();
        mTitlePaint.setAntiAlias(true);
        mTitlePaint.setDither(true);
        mTitlePaint.setColor(Color.WHITE);
        mTitlePaint.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,28,getContext().getResources().getDisplayMetrics()));

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setDither(true);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,50,getContext().getResources().getDisplayMetrics()));

        pieChartData.add(new PieChartData("Froyo",Color.CYAN,2,0));
        pieChartData.add(new PieChartData("Gingerbread",Color.MAGENTA,6,1));
        pieChartData.add(new PieChartData("IceCreamSandwich",Color.GRAY,5,1));
        pieChartData.add(new PieChartData("Jelly Bean",Color.GREEN,63,2));
        pieChartData.add(new PieChartData("KitKat",Color.BLUE,100,0));
        pieChartData.add(new PieChartData("Lollipop",Color.RED,123,0));
        pieChartData.add(new PieChartData("Marshmallow",Color.YELLOW,60,0));

        for (int i = 0; i < pieChartData.size(); i++) {
            mTotalCount += pieChartData.get(i).getValue() + pieChartData.get(i).getPaddingValue();
        }
        for (int i = 0; i < pieChartData.size(); i++) {
            float value = pieChartData.get(i).getValue();
            mMaxCount = mMaxCount > value ? mMaxCount :value;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        mAbsWidth = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        mAbsHeight = MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop() - getPaddingBottom();
        mBottomTextHeight = (int) (mAbsHeight * 0.1F);

        mCircleRadius = (mAbsHeight - mBottomTextHeight) / 2 - 100;

        float mRectLeft = mAbsWidth * 0.5F - mCircleRadius;
        float mRectTop = (mAbsHeight - mBottomTextHeight) * 0.5F - mCircleRadius;
        float mRectRight = mAbsWidth * 0.5F + mCircleRadius;
        float mRectBottom = (mAbsHeight - mBottomTextHeight) * 0.5F + mCircleRadius;

        mRectF.set(mRectLeft,mRectTop,mRectRight,mRectBottom);
        //获取圆心
        mCircleCenterX = (mRectRight + mRectLeft) / 2;
        mCircleCenterY = (mRectBottom + mRectTop) / 2;
    }

    private static final String TAG = "Practice11PieChartView";

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < pieChartData.size(); i++) {
            PieChartData pieChartData = this.pieChartData.get(i);
            mArcPaint.setColor(pieChartData.getColor());

            float spaceAngle = 360f * pieChartData.getPaddingValue() / mTotalCount;
            float sweepAngle = 360f * pieChartData.getValue() / mTotalCount;
            float mStopAngle = mStartAngle + sweepAngle;
            float mHalfAngle = (mStartAngle + mStopAngle) / 2;

            Log.d(TAG,"mStartAngle: " + mStartAngle + ", sweepAngle: " + sweepAngle + ", mHalfAngle: " + mHalfAngle);

            if (pieChartData.getValue() == mMaxCount) {
                float mSpecialCircleCenterX = (float) (mCircleCenterX + (25) * Math.cos(mHalfAngle * Math.PI / 180));
                float mSpecialCircleCenterY = (float) (mCircleCenterY + (25) * Math.sin(mHalfAngle * Math.PI / 180));

                RectF rectF = new RectF(mSpecialCircleCenterX - mCircleRadius,mSpecialCircleCenterY - mCircleRadius,
                        mSpecialCircleCenterX + mCircleRadius,mSpecialCircleCenterY + mCircleRadius);

                canvas.drawArc(rectF,mStartAngle,sweepAngle,true,mArcPaint);

                float mPointStopX = (float) (mSpecialCircleCenterX + (mCircleRadius + 50) * Math.cos(mHalfAngle * Math.PI / 180));
                float mPointStopY = (float) (mSpecialCircleCenterY + (mCircleRadius + 50) * Math.sin(mHalfAngle * Math.PI / 180));
                float mPointStartX = (float) (mSpecialCircleCenterX + (mCircleRadius) * Math.cos(mHalfAngle * Math.PI / 180));
                float mPointStartY = (float) (mSpecialCircleCenterY + (mCircleRadius) * Math.sin(mHalfAngle * Math.PI / 180));

                canvas.drawLine(mPointStartX,mPointStartY,mPointStopX,mPointStopY,mLinePaint);

                float mStopXLine = 0;
                if (mHalfAngle >= 270 || mHalfAngle < 90) {
                    mStopXLine = mPointStopX + 50;
                    mTitlePaint.setTextAlign(Paint.Align.LEFT);
                    canvas.drawText(pieChartData.getTitle(),mStopXLine + 10,mPointStopY,mTitlePaint);
                    canvas.drawLine(mPointStopX,mPointStopY,mStopXLine,mPointStopY,mLinePaint);
                } else if (mHalfAngle < 270) {
                    mStopXLine = mPointStopX - 50;
                    mTitlePaint.setTextAlign(Paint.Align.RIGHT);
                    canvas.drawText(pieChartData.getTitle(),mStopXLine - 10,mPointStopY,mTitlePaint);
                    canvas.drawLine(mPointStopX,mPointStopY,mStopXLine,mPointStopY,mLinePaint);
                }
            } else {
                canvas.drawArc(mRectF,mStartAngle,sweepAngle,true,mArcPaint);
                canvas.drawArc(mRectF,sweepAngle,spaceAngle,true,mSpacePaint);

                float mPointStopX = (float) (mCircleCenterX + (mCircleRadius + 50) * Math.cos(mHalfAngle * Math.PI / 180));
                float mPointStopY = (float) (mCircleCenterY + (mCircleRadius + 50) * Math.sin(mHalfAngle * Math.PI / 180));
                float mPointStartX = (float) (mCircleCenterX + (mCircleRadius) * Math.cos(mHalfAngle * Math.PI / 180));
                float mPointStartY = (float) (mCircleCenterY + (mCircleRadius) * Math.sin(mHalfAngle * Math.PI / 180));

                canvas.drawLine(mPointStartX,mPointStartY,mPointStopX,mPointStopY,mLinePaint);

                float mStopXLine = 0;
                if (mHalfAngle >= 270 || mHalfAngle < 90) {
                    mStopXLine = mPointStopX + 50;
                    mTitlePaint.setTextAlign(Paint.Align.LEFT);
                    canvas.drawText(pieChartData.getTitle(),mStopXLine + 10,mPointStopY,mTitlePaint);
                    canvas.drawLine(mPointStopX,mPointStopY,mStopXLine,mPointStopY,mLinePaint);
                } else if (mHalfAngle < 270) {
                    mStopXLine = mPointStopX - 50;
                    mTitlePaint.setTextAlign(Paint.Align.RIGHT);
                    canvas.drawText(pieChartData.getTitle(),mStopXLine - 10,mPointStopY,mTitlePaint);
                    canvas.drawLine(mPointStopX,mPointStopY,mStopXLine,mPointStopY,mLinePaint);
                }
            }
            mStartAngle += sweepAngle + spaceAngle;
        }
        canvas.drawText("饼图",mCircleCenterX - 50,mAbsHeight - mBottomTextHeight * 0.5F,mTextPaint);
    }
}