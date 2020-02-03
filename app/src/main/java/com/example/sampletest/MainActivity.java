package com.example.sampletest;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import android.app.*;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.content.*;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;

import androidx.core.content.res.TypedArrayUtils;

import java.lang.reflect.Array;

public class MainActivity extends Activity {
    private  static final  String[] CourseAndCertification= new String[]{"CSI1102","CSI2222","SEG2105","OCSP","SampleTest"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final AutoCompleteTextView editText = findViewById(R.id.select);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, CourseAndCertification);
        editText.setAdapter(adapter);
        Button start_test=findViewById(R.id.startTest);
        start_test.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = getApplicationContext();
                Toast toast;
                String selected_course;
                selected_course=editText.getText().toString();
                boolean test= Arrays.asList(CourseAndCertification).contains(selected_course);
                if (test){
                    Intent intent = new Intent(getApplicationContext(), Questions.class);
                    intent.putExtra("number_questions", 2);
                    intent.putExtra("selected_course", selected_course);
                    startActivity(intent);
                }
                else if (selected_course.equals("")) {
                    toast = Toast.makeText(context,"Please choose a course or certification",Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if (!test){
                    toast = Toast.makeText(context,"Course or certification invalid. Please try again",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

        });
        final Button advance_setting=findViewById(R.id.advance_setting);
        advance_setting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText passing_grade=findViewById(R.id.passing_grade);
                EditText noq=findViewById(R.id.number_of_question);
                TextView passing_grade_text=findViewById(R.id.passing_grade_text);
                TextView noq_text=findViewById(R.id.noq_text);
                if (passing_grade.getVisibility()==View.GONE){
                    passing_grade.setVisibility(View.VISIBLE);
                    noq.setVisibility(View.VISIBLE);
                    passing_grade_text.setVisibility(View.VISIBLE);
                    noq_text.setVisibility(View.VISIBLE);
                    advance_setting.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_arrow_upward_black_24dp,0);
                }
                else{
                    passing_grade.setVisibility(View.GONE);
                    noq.setVisibility(View.GONE);
                    passing_grade_text.setVisibility(View.GONE);
                    noq_text.setVisibility(View.GONE);
                    advance_setting.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_arrow_downward_black_24dp,0);
                }

            }

        });

    }

}
