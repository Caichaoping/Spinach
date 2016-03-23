package com.cc.spinach.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cc.spinach.R;
import com.cc.spinach.base.BaseActivity;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

/**
 * 注释：
 * 作者：菠菜 on 2016/3/23 11:00
 * 邮箱：971859818@qq.com
 */
public class CustomErrorActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custonerror);

        TextView errorDetailsText = (TextView) findViewById(R.id.error_details);
        errorDetailsText.setText(CustomActivityOnCrash.getStackTraceFromIntent(getIntent()));

        Button restartButton = (Button) findViewById(R.id.restart_button);

        final Class<? extends Activity> restartActivityClass = CustomActivityOnCrash.getRestartActivityClassFromIntent(getIntent());
        final CustomActivityOnCrash.EventListener eventListener = CustomActivityOnCrash.getEventListenerFromIntent(getIntent());

        if (restartActivityClass != null) {
            restartButton.setText(R.string.restart_app);
            restartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CustomErrorActivity.this, restartActivityClass);
                    CustomActivityOnCrash.restartApplicationWithIntent(CustomErrorActivity.this, intent, eventListener);
                }
            });
        } else {
            restartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CustomActivityOnCrash.closeApplication(CustomErrorActivity.this, eventListener);
                }
            });
        }
    }
}
