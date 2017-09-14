package com.magnit.testandroid.model;



public class Values {
    private int mValue;
    private float mRatio;

    public Values(int value, float ratio) {
        this.mValue = value;
        this.mRatio = ratio;
    }

    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
        this.mValue = value;
    }

    public float getRatio() {
        return mRatio;
    }

    public void setRatio(float ratio) {
        this.mRatio = ratio;
    }
}
