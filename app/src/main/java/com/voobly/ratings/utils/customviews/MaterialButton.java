package com.voobly.ratings.utils.customviews;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import com.voobly.ratings.R;
import com.voobly.ratings.VooblyApp;

import java.util.HashMap;


/**
 * Created by jguerras on 16/11/2016.
 */

public class MaterialButton extends AppCompatButton implements View.OnClickListener {

    private View view;
    private boolean isOval;
    private final int version = Build.VERSION.SDK_INT;
    private OnClickListener mClickListener;
    private boolean interceptor;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MaterialButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
        super.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MaterialButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
        super.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init(Context context, AttributeSet attrs) {

        applyCustomFont(context);
        TypedArray configurationParams =
                context.getTheme().obtainStyledAttributes(attrs, R.styleable.MaterialButton, 0, 0);

        try {
            Drawable background = configurationParams.getDrawable(R.styleable.MaterialButton_overBackground);
            if (background != null) {
                setBackground(background);
            } else {
                int normalColor = configurationParams.getColor(R.styleable.MaterialButton_buttonNormalColor,
                        ContextCompat.getColor(getContext(), R.color.colorPrimary));

                TypedValue tp = new TypedValue();
                getContext().getTheme().resolveAttribute(R.attr.colorControlHighlight, tp, true);

                int selectedColor = configurationParams.getColor(R.styleable.MaterialButton_buttonRippleColor2,
                        tp.data);

                int cornerRadius = configurationParams.getDimensionPixelOffset(R.styleable.MaterialButton_cornerRadius,
                        0);

                int buttonStrokeColor = configurationParams.getColor(R.styleable.MaterialButton_buttonStrokeColor,
                        normalColor);

                int shape = configurationParams.getInt(R.styleable.MaterialButton_buttonShape, 0);
                isOval = shape == 1;

                interceptor = configurationParams.getBoolean(R.styleable.MaterialButton_intercepterOnclick, false);


                if (version < Build.VERSION_CODES.LOLLIPOP) {
                    changeRippleColor(normalColor,
                            selectedColor, cornerRadius, buttonStrokeColor, shape);
                } else {
                    changeRippleColorMaterial(normalColor,
                            selectedColor, cornerRadius, buttonStrokeColor, shape);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //configurationParams.recycle();
        }
    }

    private void changeRippleColor(@ColorInt int colorNormal, @ColorInt int colorSelected,
                                   int cornerRadius, @ColorInt int strokeColor, int shape) {

        GradientDrawable normalBackground = new GradientDrawable();
        normalBackground.setShape(/*GradientDrawable.RECTANGLE*/shape);
        normalBackground.setCornerRadius(cornerRadius);
        normalBackground.setColor(colorNormal);
        normalBackground.setStroke((int) convertDpToPixels(2), strokeColor);

        GradientDrawable pressedDrawable = new GradientDrawable();
        pressedDrawable.setShape(shape);
        pressedDrawable.setCornerRadius(cornerRadius);
        pressedDrawable.setColor(colorSelected);
        pressedDrawable.setStroke((int) convertDpToPixels(2), colorSelected);

        StateListDrawable states = new StateListDrawable();
        states.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
        states.addState(new int[]{}, normalBackground);
        setBackground(states);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void changeRippleColorMaterial(@ColorInt int colorNormal, @ColorInt int colorSelected,
                                           int cornerRadius, @ColorInt int strokeColor, int shape) throws Exception {

        //float[] outerRadii = new float[8];
        // 3 is radius of final ripple,
        // instead of 3 you can give required final radius
        //Arrays.fill(outerRadii, (int)Utils.convertDpToPixels(2));


        GradientDrawable pressedDrawable = new GradientDrawable();
        pressedDrawable.setShape(shape);
        pressedDrawable.setCornerRadius(cornerRadius);
        pressedDrawable.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        pressedDrawable.setStroke((int) convertDpToPixels(2), strokeColor);

        GradientDrawable normalBackground = new GradientDrawable();
        normalBackground.setShape(shape);
        normalBackground.setCornerRadius(cornerRadius);
        normalBackground.setColor(colorNormal);
        normalBackground.setStroke((int) convertDpToPixels(2), strokeColor);

        RippleDrawable rippleDrawable = new RippleDrawable(ColorStateList.valueOf(colorSelected),
                normalBackground, pressedDrawable);
        setBackground(rippleDrawable);
    }

    protected void applyCustomFont(Context context) {
        Typeface customFont = getTypeface("fonts/comfortaa/ComfortaaRegular.ttf", context);
        setTypeface(customFont, Typeface.BOLD);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isOval) {
            int widthMode = MeasureSpec.getMode(widthMeasureSpec);
            int widthSize = MeasureSpec.getSize(widthMeasureSpec);
            int heightMode = MeasureSpec.getMode(heightMeasureSpec);
            int heightSize = MeasureSpec.getSize(heightMeasureSpec);
            int size;
            size = Math.min(widthMeasureSpec, heightMeasureSpec);
            //int finalMeasureSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
            super.onMeasure(size, size);
            /*if ( ((ViewGroup)getParent()).getChildCount() == 1){
                ((ViewGroup)getParent()).measure();
            }*/
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

    }

    public void eneableButton() {
        setClickable(false);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setClickable(true);
            }
        }, 1500);
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        this.mClickListener = l;
    }

    @Override
    public void onClick(View view) {
        if (mClickListener != null) {
            mClickListener.onClick(this);
            if (interceptor) eneableButton();
        }
    }

    private static int convertDpToPixels(int dp) {
        DisplayMetrics displayMetrics = VooblyApp.getInstance().getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
    private static Typeface getTypeface(String fontname, Context context) {
        HashMap<String, Typeface> fontCache = new HashMap<>();
        Typeface typeface = fontCache.get(fontname);
        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), fontname);
            }
            catch (Exception e) {
                return null; }
            fontCache.put(fontname, typeface);
        } return typeface;
    }
}