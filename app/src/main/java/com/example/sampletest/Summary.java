package com.example.sampletest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Summary extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary);
        int marks= getIntent().getExtras().getInt("result");
        TextView passed= findViewById(R.id.summary_passed);
        TextView percentage=findViewById(R.id.summary_percentage);
        percentage.setText(String.valueOf(marks)+"%");
        if (marks>50){
           passed.setText("Passed");
        }
        else{
            passed.setText("Failed");
            passed.setTextColor(Color.RED);
            percentage.setTextColor(Color.RED);
        }

        Button button=findViewById(R.id.restart_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }

        });
    }
}