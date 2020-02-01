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
        int correct_count= getIntent().getExtras().getInt("correct_count");
        int number_of_question=getIntent().getExtras().getInt("number_of_question");
        double marks = ((double)correct_count)/number_of_question*100;
        TextView passed= findViewById(R.id.summary_passed);
        TextView percentage=findViewById(R.id.summary_percentage);
        TextView body=findViewById(R.id.summary_body);

        percentage.setText((int)marks+"%");
        if (marks>=50){
           passed.setText("Passed");
           body.setText("Congratulations, You have "+correct_count+"/"+number_of_question+" correct");
        }
        else{
            passed.setText("Failed");
            passed.setTextColor(Color.RED);
            percentage.setTextColor(Color.RED);
            body.setText("Whoops, You only got "+correct_count+"/"+number_of_question+" correct. Try harder next time.:(");
        }
        ProgressBar pg=findViewById(R.id.progressBar);
        pg.setProgress((int)marks);
        Button button=findViewById(R.id.restart_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }

        });



    }
}