package com.hencoder.hencoderpracticedraw1.model;

/**
 * <请描述这个类是干什么的>
 *
 * @author guobin
 * @data: 2018/5/18 16:01
 * @Email: 702250823@qq.com
 */
public class PieChartData {


    private String title;

    private int color;

    private float value;

    private float paddingValue;

    public PieChartData(String title,int color,float value,float paddingValue) {
        this.title = title;
        this.color = color;
        this.value = value;
        this.paddingValue = paddingValue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getPaddingValue() {
        return paddingValue;
    }

    public void setPaddingValue(float paddingValue) {
        this.paddingValue = paddingValue;
    }
}