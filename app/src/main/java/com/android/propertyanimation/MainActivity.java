package com.android.propertyanimation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LayoutAnimationController;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    private LinearLayout linearLayout;

    private Button addBtn;

    private Button secondBtn;

    private Button threeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.iv);

        linearLayout = (LinearLayout) findViewById(R.id.ll);

        addBtn = (Button) findViewById(R.id.button_add);

        secondBtn = (Button) findViewById(R.id.btn_open_second);

        threeBtn = (Button) findViewById(R.id.btn_three);

        setLayoutAnimation();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView();
            }
        });

        secondBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });

        threeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ThreeActivity.class));
            }
        });

    }

    /**
     * 对布局中的子view的添加设置动画
     * 仅限于xml中的子view
     *
     * 若是动态添加子View 在xml的lyaout节点中加android:animateLayoutChanges="true"属性
     *
     * */
    public void setLayoutAnimation(){
        ScaleAnimation sa = new ScaleAnimation(0,1,0,1);
        sa.setDuration(2000);
        LayoutAnimationController lac = new LayoutAnimationController(sa,0.5f);
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        linearLayout.setLayoutAnimation(lac);
    }


    public void anim1(){

        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView,"translationX",200);

        animator.setDuration(500);

        animator.start();

    }

    public void anim2(){

        ValueAnimator animator = ValueAnimator.ofFloat(0,100);

        animator.setTarget(imageView);

        animator.setDuration(1000);

        animator.start();

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float values = (Float) animation.getAnimatedValue();
                imageView.setTranslationX(values);
            }
        });

    }

    public void anim3(){
        Animator a = AnimatorInflater.loadAnimator(this,R.animator.property_animator);
        a.setTarget(imageView);
        a.start();
    }

    public void anim4(){

        imageView.animate().alpha(0).y(300).setDuration(1000).withStartAction(new Runnable() {
            @Override
            public void run() {

            }
        }).withEndAction(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }

    public void anim5(){

        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(3000);
        valueAnimator.setObjectValues(new PointF(0,0));
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setEvaluator(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {

                //fraction = t / duration;
                PointF pointF = new PointF();
                pointF.x = 200 * fraction * 3 ;
                pointF.y = 0.5f * 200 *  (fraction * 3)  * (fraction * 3) ;

                return pointF;
            }
        });

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                PointF value = (PointF) animation.getAnimatedValue();

                imageView.setX(value.x);
                imageView.setY(value.y);

            }
        });

        valueAnimator.start();

    }

    public void addView(){
        TextView tv = new TextView(this);
        tv.setText("哈哈哈哈");
        tv.setTextSize(20);
        tv.setPadding(10,10,10,10);
        tv.setBackgroundColor(Color.CYAN);
        linearLayout.addView(tv);
    }



}
