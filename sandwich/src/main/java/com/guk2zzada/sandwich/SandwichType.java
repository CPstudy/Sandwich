package com.guk2zzada.sandwich;

import android.graphics.Color;

public enum SandwichType {
    SandwichNormal(2, R.drawable.icon_sandwich_notification, "#1565C0"),
    SandwichDone(3, R.drawable.icon_sandwich_done, "#43A047"),
    SandwichWarning(4, R.drawable.icon_sandwich_warning, "#FFAB00"),
    SandwichError(5, R.drawable.icon_sandwich_error, "#D50000");

    int type;
    int drawable;
    String color;

    SandwichType(int type, int drawable, String color) {
        this.type = type;
        this.drawable = drawable;
        this.color = color;

    }

    public static SandwichType getType(int type) {
        for(SandwichType v : values()) {
            if (v.getType() == type) {
                return v;
            }
        }
        return SandwichNormal;
    }

    public int getType() {
        return type;
    }

    public int getDrawable() {
        return drawable;
    }

    public int getColor() {
        return Color.parseColor(color);
    }
}
