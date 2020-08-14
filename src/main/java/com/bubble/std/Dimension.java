package com.bubble.std;

public class Dimension {
    
    public final float width;
    public final float height;

    public Dimension(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public Dimension scaled(double ratio) {
        return new Dimension(
            (float) (ratio * width), (float) (ratio * height));
    }

    @Override
    public String toString() {
        return "(" + width + "," + height + ")";
    }
}