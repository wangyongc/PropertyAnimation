package com.android.propertyanimation;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2016/10/26.
 */

public class SecondActivity extends AppCompatActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener{

    private LinearLayout container;
    private Button addBtn;
    private CheckBox appear;
    private CheckBox disappear;
    private CheckBox changeAppear;
    private CheckBox disChangeAppear;
    private GridLayout gridLayout;
    private LayoutTransition layoutTransition;
    int index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_second);

        init();

    }


    public void init(){

        container = (LinearLayout) findViewById(R.id.container);
        addBtn = (Button) findViewById(R.id.btn_addview);
        appear = (CheckBox) findViewById(R.id.cb_appear);
        disappear = (CheckBox) findViewById(R.id.cb_disappear);
        changeAppear = (CheckBox) findViewById(R.id.cb_change_appear);
        disChangeAppear = (CheckBox) findViewById(R.id.cb_change_disappear);

        addBtn.setOnClickListener(this);
        appear.setOnCheckedChangeListener(this);
        disappear.setOnCheckedChangeListener(this);
        changeAppear.setOnCheckedChangeListener(this);
        disChangeAppear.setOnCheckedChangeListener(this);

        gridLayout = new GridLayout(this);
        gridLayout.setColumnCount(5);
        container.addView(gridLayout);

        LayoutTransition layoutTransition = new LayoutTransition();
        gridLayout.setLayoutTransition(layoutTransition);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_addview:

                addViews();

                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        layoutTransition = new LayoutTransition();
        layoutTransition.setAnimator(
                LayoutTransition.APPEARING,
                (appear.isChecked() ? layoutTransition
                        .getAnimator(LayoutTransition.APPEARING) : null));
        layoutTransition
                .setAnimator(
                        LayoutTransition.CHANGE_APPEARING,
                        (changeAppear.isChecked() ? layoutTransition
                                .getAnimator(LayoutTransition.CHANGE_APPEARING)
                                : null));
        layoutTransition.setAnimator(
                LayoutTransition.DISAPPEARING,
                (disappear.isChecked() ? layoutTransition
                        .getAnimator(LayoutTransition.DISAPPEARING) : null));
        layoutTransition.setAnimator(
                LayoutTransition.CHANGE_DISAPPEARING,
                (disChangeAppear.isChecked() ? layoutTransition
                        .getAnimator(LayoutTransition.CHANGE_DISAPPEARING)
                        : null));
        gridLayout.setLayoutTransition(layoutTransition);
    }


    public void addViews(){

        final Button button = new Button(this);
        button.setText(index++ + "");
        gridLayout.addView(button,Math.min(1,gridLayout.getChildCount()));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridLayout.removeView(button);
            }
        });

    }
}
