package com.hencoder.hencoderpracticedraw1.model;

/**
 * <请描述这个类是干什么的>
 *
 * @author guobin
 * @data: 2018/5/18 14:44
 * @Email: 702250823@qq.com
 */
public class HistogramData {

    private String title;

    private int color;

    private int value;

    public HistogramData(String title, int color, int value) {
        this.title = title;
        this.color = color;
        this.value = value;
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}