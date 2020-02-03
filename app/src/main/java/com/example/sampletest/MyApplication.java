package com.example.sampletest;

import android.app.Application;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyApplication extends Application {
    int passing_grade=50;
    int number_of_question= 5;
    String selected_course;
    ArrayList<String> temp_answer=new ArrayList<>();
    ArrayList<String> correct_answer= new ArrayList<>();
    int correct_count=0;
    public int getPassing_grade(){
        return  this.passing_grade;
    }
    public int getNumber_of_question(){
        return  this.number_of_question;
    }

    public void setPassing_grade(int passing_grade) {
        this.passing_grade = passing_grade;
    }

    public void setNumber_of_question(int number_of_question) {
        this.number_of_question = number_of_question;
    }

    public void setSelected_course(String selected_course) {
        this.selected_course = selected_course;
    }

    public void init_temp(int i){
        while (i>0){
            temp_answer.add("");
            i--;
        }
    }
    public void set_temp_item(int i, String s){
        temp_answer.set(i,s);
    }
    public String get_temp_item(int i){
         return temp_answer.get(i);
    }
    public int getCorrect_count(){
        return this.correct_count;
    }

    public String getSelected_course() {
        return selected_course;
    }

    public void restart(){
        correct_answer.clear();
        temp_answer.clear();
        selected_course="";
        this.correct_count=0;
    }
    public void init_correct(){
        try{
            JSONObject question_json = new JSONObject(loadJSONFromAsset());
            JSONArray question= question_json.getJSONArray(selected_course);
            for (int i=0;i<number_of_question;i++){
                JSONObject question_body= question.getJSONObject(i);
                correct_answer.add(question_body.getString("answer"));
            }
        }
        catch (JSONException e) {}
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
    public int result(){
        for (int i=0;i<number_of_question;i++){
            System.out.println(correct_answer.get(i)+temp_answer.get(i));
            if (correct_answer.get(i).equals(temp_answer.get(i))){
                this.correct_count++;
            }
        }
        double marks = ((double)this.correct_count)/number_of_question*100;
        return (int)marks;

    }
}
