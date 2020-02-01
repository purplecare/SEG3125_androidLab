package com.example.sampletest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.*;

import org.json.JSONArray;
import org.json.JSONException;
import android.app.*;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class Questions extends Activity {
    int question_doing=0;
    int correct_count=0;
    String correct_answer=null;
    int number_of_question=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);
        number_of_question=getIntent().getExtras().getInt("x");
        Button submit=findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                submit();
            }
        });
        update(question_doing);
        Button next=findViewById(R.id.nextButton);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               question_doing++;
               next(question_doing);
            }
        });
    }
    public void update(int i){
        try {
            JSONObject question_json = new JSONObject(loadJSONFromAsset());
            JSONArray question= question_json.getJSONArray("CSI1102");
            JSONObject question_body= question.getJSONObject(i);
            TextView content=findViewById(R.id.questionContent);
            content.setText(question_body.getString("body"));
            RadioButton ansA=findViewById(R.id.choice_a);
            ansA.setText(question_body.getString("choice_a"));
            RadioButton ansB=findViewById(R.id.choice_b);
            ansB.setText(question_body.getString("choice_b"));
            RadioButton ansC=findViewById(R.id.choice_c);
            ansC.setText(question_body.getString("choice_c"));
            RadioButton ansD=findViewById(R.id.choice_d);
            ansD.setText(question_body.getString("choice_d"));
            TextView question_number=findViewById(R.id.questionNumber);
            question_number.setText("Question: "+(question_doing+1)+"/10");
            ProgressBar question_progress=findViewById(R.id.question_progress);
            question_progress.setProgress((question_doing+1)*10);
            correct_answer=question_body.getString("answer");
            RadioGroup answer=findViewById(R.id.answer);
            answer.clearCheck();
            Button submit=findViewById(R.id.submitButton);
            Button next=findViewById(R.id.nextButton);

            if (question_doing+1<number_of_question){
                submit.setVisibility(View.INVISIBLE);
            }
            else{
               submit.setVisibility(View.VISIBLE);
               next.setVisibility(View.INVISIBLE);
            }
        }
        catch (JSONException e) {}
    }
    public void next(int i){
        RadioGroup answer=findViewById(R.id.answer);
        int answer_button= answer.getCheckedRadioButtonId();
        if (answer_button != -1){
            RadioButton choice=findViewById(answer_button);
            String choice_text=String.valueOf(choice.getText());
            if (choice_text.equals(correct_answer)) {
                correct_count++;
            }
            update(i);

        }
        else {
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context,"Please Select a answer",Toast.LENGTH_SHORT);
            toast.show();
        }

    }
    public void submit(){
        RadioGroup answer=findViewById(R.id.answer);
        int answer_button= answer.getCheckedRadioButtonId();
        if (answer_button != -1){
            RadioButton choice=findViewById(answer_button);
            String choice_text=String.valueOf(choice.getText());
            if (choice_text.equals(correct_answer)) {
                correct_count++;
            }

            Intent intent = new Intent(getApplicationContext(), Summary.class);
            intent.putExtra("correct_count",correct_count);
            intent.putExtra("number_of_question",number_of_question);
            startActivity(intent);

        }
        else {
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context,"Please Select a answer",Toast.LENGTH_SHORT);
            toast.show();
        }

    }
    public String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getAssets().open("questions.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
