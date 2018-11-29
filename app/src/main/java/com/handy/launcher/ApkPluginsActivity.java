package com.handy.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

/**
 * file name
 *
 * @author LiuJie https://github.com/Handy045
 * @description functional description.
 * @date Created in 2018/11/29 9:14 AM
 * @modified By liujie
 */
public class ApkPluginsActivity extends AppCompatActivity {

    private TextView text;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appplugins);
        text = findViewById(R.id.text);
        button = findViewById(R.id.button);
    }
}
