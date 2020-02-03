package com.example.sampletest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Summary extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary);
        int marks=((MyApplication)this.getApplication()).result();
        int correct_count= ((MyApplication)this.getApplication()).getCorrect_count();
        int number_of_question=((MyApplication)this.getApplication()).getNumber_of_question();
        System.out.println(String.valueOf(correct_count)+String.valueOf(number_of_question));

        TextView passed= findViewById(R.id.summary_passed);
        TextView percentage=findViewById(R.id.summary_percentage);
        TextView body=findViewById(R.id.summary_body);
        int passing_grade=((MyApplication)this.getApplication()).getPassing_grade();
        percentage.setText(marks+"%");
        if (marks>=passing_grade){
           passed.setText("Passed");
           body.setText("Congratulations, You have "+correct_count+"/"+number_of_question+" correct! You Passed");
        }
        else{
            passed.setText("Failed");
            passed.setTextColor(Color.RED);
            percentage.setTextColor(Color.RED);
            body.setText("Whoops, You only got "+correct_count+"/"+number_of_question+" correct. Try harder next time.:(");
        }
        ProgressBar pg=findViewById(R.id.progressBar);
        pg.setProgress(marks);
        Button button=findViewById(R.id.restart_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               restart();
            }

        });



    }
    public void restart(){
        ((MyApplication)this.getApplication()).restart();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}