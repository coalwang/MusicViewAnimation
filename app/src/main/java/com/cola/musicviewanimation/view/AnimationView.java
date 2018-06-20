package com.cola.musicviewanimation.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.print.PrinterId;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cola.musicviewanimation.R;

import java.util.ArrayList;

public class AnimationView extends LinearLayout {

    private ValueAnimator mValueAnimator;

    private  static int WIDTH ;
    private  static int LARGE_HEIGHT ;
    private  static int SMALL_HEIGHT ;

    private ArrayList<View> viewArrayList = new ArrayList<>();

    public AnimationView(Context context) {
        super(context);
        init();
    }

    public AnimationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        WIDTH = getResources().getDimensionPixelSize(R.dimen.width);
        SMALL_HEIGHT = getResources().getDimensionPixelSize(R.dimen.small_height);
        LARGE_HEIGHT = getResources().getDimensionPixelSize(R.dimen.large_height);
        // 设置内容的排列方式，水平
        setOrientation(HORIZONTAL);
        setBackgroundColor(getResources().getColor(R.color.colorAccent));
        setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
//        // 设置当前view的宽高
//        setLayoutParams(new ViewGroup.LayoutParams(getResources().getDimensionPixelSize(R.dimen.height),
//                getResources().getDimensionPixelSize(R.dimen.height)));

        viewArrayList.add(createLineView(SMALL_HEIGHT));
        viewArrayList.add(createLineView(LARGE_HEIGHT));
        viewArrayList.add(createLineView(SMALL_HEIGHT));
        viewArrayList.add(createLineView(LARGE_HEIGHT));

        for (View view:viewArrayList){
            addView(view);
        }
    }

    private View createLineView(int height){
        View view = new View(getContext());
        view.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        LayoutParams params = new LayoutParams(WIDTH, height);
        params.setMargins(getResources().getDimensionPixelSize(R.dimen.margin), 0,getResources().getDimensionPixelSize(R.dimen.margin),
                15);
        // 设置view的宽和高
        view.setLayoutParams(params);
     //   view.setPivotY(height);  // 设置描点
        return view;
    }

    private void initAnimator(){
        mValueAnimator = ValueAnimator.ofFloat(0, 1f);
        mValueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.setDuration(1000);
    }


    /**
     * 补间动画
     * 帧动画
     * 属性动画
     * 使用属性动画
     */
    public void startAnimation(){
        if (mValueAnimator == null){
            initAnimator();
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float value = (float)animation.getAnimatedValue();

                    float short_line = 1 + value - 0.1f;
                    float long_line =  1 - value + 0.1f;

                    // 设置每个view的缩放
                    viewArrayList.get(0).setScaleY(short_line);
                    viewArrayList.get(1).setScaleY(long_line);
                    viewArrayList.get(2).setScaleY(short_line);
                    viewArrayList.get(3).setScaleY(long_line);
                }
            });
            mValueAnimator.start();
        }
    }

    public void stopAnimation(){

        if (mValueAnimator != null && mValueAnimator.isRunning()){
            mValueAnimator.cancel();
        }
        resetLineViews();
    }

    private void resetLineViews(){
        viewArrayList.get(0).setScaleY(1);
        viewArrayList.get(1).setScaleY(1);
        viewArrayList.get(2).setScaleY(1);
        viewArrayList.get(3).setScaleY(1);
    }



}
