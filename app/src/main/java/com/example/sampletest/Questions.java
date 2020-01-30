package com.example.sampletest;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import java.io.*;
import org.json.JSONException;
import android.app.*;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

public class Questions extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);
        Button button=findViewById(R.id.submitButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Summary.class);
                intent.putExtra("result", 40);
                startActivity(intent);
            }

        });
        try {
            JSONObject question = new JSONObject(loadJSONFromAsset());
            String question_son= question.getString("CSI1102");
            //JSONObject classes = question.getJSONObject(question_Json);
            //String question_1=classes.getString("questions_1");
            //String question_body=question_1.getString("body");

            TextView content=findViewById(R.id.questionContent);
            content.setText(question_son);

        }
        catch (JSONException e) {}

    }
    public String loadJSONFromAsset() {
        String json = null;
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
