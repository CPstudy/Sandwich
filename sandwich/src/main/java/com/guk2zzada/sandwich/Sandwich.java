package com.guk2zzada.sandwich;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.IntDef;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Sandwich {
    public final static int LENGTH_SHORT = 0;
    public final static int LENGTH_LONG = 1;

    @IntDef({LENGTH_SHORT, LENGTH_LONG})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {}

    public final static int SANDWICH_NORMAL = 2;
    public final static int SANDWICH_DONE = 3;
    public final static int SANDWICH_WARNING = 4;
    public final static int SANDWICH_ERROR = 5;

    @IntDef({SANDWICH_NORMAL, SANDWICH_DONE, SANDWICH_WARNING, SANDWICH_ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE {}

    private final static int DURATION_BOX = 250;
    private final static int DURATION_ICON = 350;

    private static Context mContext;
    private static View mLayout;
    private static int mToastDuration = LENGTH_SHORT;
    private static String mMessage;

    private int icon = SandwichType.SandwichNormal.getDrawable();
    private int color = SandwichType.SandwichNormal.getColor();

    private boolean boolSetColor = false;
    private boolean boolSetIcon = false;

    public Sandwich(Context context) {
        mContext = context;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mLayout = inflater.inflate(R.layout.layout_sandwich, null);
    }

    private Sandwich(Context context, View view, int duration) {
        mContext = context;
        mLayout = view;
        mToastDuration = duration;
    }

    public static Sandwich makeText(Context context, String message, @Duration int duration) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.layout_sandwich, null);

        mContext = context;
        mToastDuration = duration;
        mLayout = layout;
        mMessage = message;

        return new Sandwich(context, layout, duration);
    }

    public void show() {
        final FrameLayout layoutRoot = mLayout.findViewById(R.id.layoutRoot);
        final RelativeLayout layoutFront = mLayout.findViewById(R.id.layoutFront);
        final RelativeLayout layoutBack = mLayout.findViewById(R.id.layoutBack);
        //final ImageView imgBack = mLayout.findViewById(R.id.imgBack);
        final ImageView imgIcon = mLayout.findViewById(R.id.imgIcon);
        final TextView txtText = mLayout.findViewById(R.id.txtText);
        final TextView txtText2 = mLayout.findViewById(R.id.txtText2);

        final Drawable drawable = mContext.getResources().getDrawable(R.drawable.bg_sandwich_iconbox);
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);

        imgIcon.setImageResource(icon);
        txtText.setText(mMessage);
        txtText2.setText(mMessage);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            layoutFront.setBackgroundDrawable(drawable);
            layoutBack.setBackgroundDrawable(drawable);
        } else {
            layoutFront.setBackground(drawable);
            layoutBack.setBackground(drawable);
        }

        ViewTreeObserver vto = layoutBack.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int height = layoutBack.getHeight();
                layoutFront.setTranslationY(-height);

                final ObjectAnimator scaleBounceX = ObjectAnimator.ofFloat(layoutRoot, View.SCALE_X, 0.5f, 1.0f);
                scaleBounceX.setInterpolator(new BounceInterpolator());
                scaleBounceX.setDuration(DURATION_ICON);

                final ObjectAnimator scaleBounceY = ObjectAnimator.ofFloat(layoutRoot, View.SCALE_Y, 0.5f, 1.0f);
                scaleBounceY.setInterpolator(new BounceInterpolator());
                scaleBounceY.setDuration(DURATION_ICON);

                final ObjectAnimator slideUp = ObjectAnimator.ofFloat(layoutFront, View.TRANSLATION_Y, -height, 0);
                slideUp.setDuration(DURATION_BOX);
                slideUp.setInterpolator(new DecelerateInterpolator());

                scaleBounceX.addListener(new AnimatorListenerAdapter() {

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        slideUp.start();
                    }
                });

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(
                        scaleBounceX,
                        scaleBounceY
                );
                animatorSet.start();
            }
        });


        /*final ObjectAnimator flipFront = ObjectAnimator.ofFloat(layoutFront, View.ROTATION_X, 0, 90);
        flipFront.setDuration(DURATION_BOX);
        flipFront.setInterpolator(new AccelerateInterpolator());

        final ObjectAnimator flipBack = ObjectAnimator.ofFloat(layoutBack, View.ROTATION_X, -90, 0);
        flipBack.setDuration(DURATION_BOX);
        flipBack.setInterpolator(new DecelerateInterpolator());*/

        Toast toast = new Toast(mContext);
        toast.setDuration(mToastDuration);
        toast.setView(mLayout);
        toast.show();
    }

    public void setText(String text) {
        mMessage = text;
    }

    public void setType(@TYPE int type) {
        SandwichType sandwichType = SandwichType.getType(type);
        if(!boolSetIcon) {
            icon = sandwichType.getDrawable();
        }
        if(!boolSetColor) {
            color = sandwichType.getColor();
        }
    }

    public void setDuration(@Duration int duration) {
        mToastDuration = duration;
    }

    public void setIconBoxColor(int color) {
        boolSetColor = true;
        this.color = color;
    }

    public void setIcon(int resId) {
        boolSetIcon = true;
        icon = resId;
    }
}
