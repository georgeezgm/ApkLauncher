package com.handy.launcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                startActivity(new Intent(MainActivity.this, AppPluginsResActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(MainActivity.this, AppPluginsActivity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(MainActivity.this, ApkPluginsResActivity.class));
                break;
            case R.id.button4:
                startActivity(new Intent(MainActivity.this, ApkPluginsActivity.class));
                break;
            default:
        }
    }
}
