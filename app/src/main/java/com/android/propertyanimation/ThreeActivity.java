package com.android.propertyanimation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/10/26.
 */

public class ThreeActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_three);

        init();

    }

    public void init(){

        imageView = (ImageView) findViewById(R.id.iv_luncher);
        button = (Button) findViewById(R.id.btn_start);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CustomTV customTV = new CustomTV();
//                imageView.startAnimation(customTV);

                CustomAnim customAnim = new CustomAnim();
                customAnim.setRotateY(30);
                imageView.startAnimation(customAnim);
            }
        });


    }
}
