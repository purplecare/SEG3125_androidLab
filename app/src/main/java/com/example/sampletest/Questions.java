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
    int number_of_question;
    String selected_course;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);
        number_of_question=((MyApplication)this.getApplication()).getNumber_of_question();
        selected_course=((MyApplication)this.getApplication()).getSelected_course();
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
               next();
            }
        });
        Button prev = findViewById(R.id.prevButton);
        prev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               prev();

            }
        });
    }
    public void update(int i){
        try {
            JSONObject question_json = new JSONObject(loadJSONFromAsset());
            JSONArray question= question_json.getJSONArray(selected_course);
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
            double q_number= (((double)question_doing+1)/number_of_question*100);
            question_number.setText("Question: "+(question_doing+1)+"/"+number_of_question);
            ProgressBar question_progress=findViewById(R.id.question_progress);
            question_progress.setProgress((int)q_number);
            RadioGroup answer=findViewById(R.id.answer);
            answer.clearCheck();
            Button submit=findViewById(R.id.submitButton);
            Button next=findViewById(R.id.nextButton);
            Button prev=findViewById(R.id.prevButton);
            if (question_doing+1<number_of_question && question_doing!=0){
                submit.setVisibility(View.INVISIBLE);
                next.setVisibility(View.VISIBLE);
                prev.setVisibility(View.VISIBLE);

            }
            else if (question_doing==0){
                submit.setVisibility(View.INVISIBLE);
                next.setVisibility(View.VISIBLE);
                prev.setVisibility(View.INVISIBLE);
            }
            else{
               submit.setVisibility(View.VISIBLE);
               next.setVisibility(View.INVISIBLE);
               prev.setVisibility(View.VISIBLE);
            }
        }
        catch (JSONException e) {}
    }
    public void next(){
        RadioGroup answer=findViewById(R.id.answer);
        int answer_button= answer.getCheckedRadioButtonId();
        if (answer_button != -1){
            RadioButton choice=findViewById(answer_button);
            String choice_text=String.valueOf(choice.getText());
            ((MyApplication)this.getApplication()).set_temp_item(question_doing,choice_text);
            question_doing++;
            update(question_doing);
            String temp_ans=((MyApplication)this.getApplication()).get_temp_item(question_doing);
            if (!temp_ans.equals("")) {
                RadioGroup rg = findViewById(R.id.answer);
                RadioButton ansA = findViewById(R.id.choice_a);
                String choice_a = String.valueOf(ansA.getText());
                if (choice_a.equals(temp_ans)) {
                    rg.check(R.id.choice_a);
                }
                RadioButton ansB = findViewById(R.id.choice_b);
                String choice_b = String.valueOf(ansB.getText());
                if (choice_b.equals(temp_ans)) {
                    rg.check(R.id.choice_b);
                }
                RadioButton ansC = findViewById(R.id.choice_c);
                String choice_c = String.valueOf(ansC.getText());
                if (choice_c.equals(temp_ans)) {
                    rg.check(R.id.choice_c);
                }
                RadioButton ansD = findViewById(R.id.choice_d);
                String choice_d = String.valueOf(ansD.getText());
                if (choice_d.equals(temp_ans)) {
                    rg.check(R.id.choice_d);
                }
            }

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
            ((MyApplication)this.getApplication()).set_temp_item(question_doing,choice_text);

            Intent intent = new Intent(getApplicationContext(), Summary.class);
            startActivity(intent);

        }
        else {
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context,"Please Select a answer",Toast.LENGTH_SHORT);
            toast.show();
        }

    }
    public void prev(){
        question_doing--;
        update(question_doing);
        String temp_ans=((MyApplication)this.getApplication()).get_temp_item(question_doing);
        if (!temp_ans.equals("")) {
            RadioGroup rg = findViewById(R.id.answer);
            RadioButton ansA = findViewById(R.id.choice_a);
            String choice_a = String.valueOf(ansA.getText());
            if (choice_a.equals(temp_ans)) {
                rg.check(R.id.choice_a);
            }
            RadioButton ansB = findViewById(R.id.choice_b);
            String choice_b = String.valueOf(ansB.getText());
            if (choice_b.equals(temp_ans)) {
                rg.check(R.id.choice_b);
            }
            RadioButton ansC = findViewById(R.id.choice_c);
            String choice_c = String.valueOf(ansC.getText());
            if (choice_c.equals(temp_ans)) {
                rg.check(R.id.choice_c);
            }
            RadioButton ansD = findViewById(R.id.choice_d);
            String choice_d = String.valueOf(ansD.getText());
            if (choice_d.equals(temp_ans)) {
                rg.check(R.id.choice_d);
            }


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
