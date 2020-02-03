package com.example.sampletest;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import android.app.*;
import android.text.Editable;
import android.text.TextWatcher;
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
        final Button advance_setting=findViewById(R.id.advance_setting);
        final EditText passing_grade=findViewById(R.id.passing_grade);
        final EditText noq=findViewById(R.id.number_of_question);
        final TextView passing_grade_text=findViewById(R.id.passing_grade_text);
        final TextView noq_text=findViewById(R.id.noq_text);
        int pg_int=((MyApplication)this.getApplication()).getPassing_grade();
        int nq_int=((MyApplication)this.getApplication()).getNumber_of_question();
        String pg=String.valueOf(pg_int);
        String nq=String.valueOf(nq_int);
        passing_grade.setText(pg);
        noq.setText(nq);
        Button start_test=findViewById(R.id.startTest);
        start_test.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                start_test_onclick(editText,passing_grade,noq);
            }

        });

        advance_setting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                advance_setting_onclick(advance_setting,passing_grade,passing_grade_text,noq,noq_text);
            }
        });

    }

    public void start_test_onclick(AutoCompleteTextView editText, EditText passing_grade, EditText noq) {
        Context context = getApplicationContext();
        Toast toast;
        String selected_course;
        selected_course = editText.getText().toString();
        String pg_check = passing_grade.getText().toString();
        String nq_check = noq.getText().toString();
        boolean advanceSettingNotEmpty = !pg_check.equals("") && !nq_check.equals("");
        if (advanceSettingNotEmpty) {
            int pg = Integer.valueOf(pg_check);
            int noq_int = Integer.valueOf(nq_check);
            boolean validAdvanceSetting = (pg >= 0 && pg <= 100) && (noq_int >= 0 && noq_int <= 5);
            if (validAdvanceSetting) {
                ((MyApplication) this.getApplication()).setPassing_grade(pg);
                ((MyApplication) this.getApplication()).setNumber_of_question(noq_int);

                boolean test = Arrays.asList(CourseAndCertification).contains(selected_course);
                if (test) {
                    Intent intent = new Intent(getApplicationContext(), Questions.class);
                    ((MyApplication) this.getApplication()).setSelected_course(selected_course);
                    ((MyApplication)this.getApplication()).init_temp(noq_int);
                    ((MyApplication)this.getApplication()).init_correct();
                    startActivity(intent);
                } else if (selected_course.equals("")) {
                    toast = Toast.makeText(context, "Please choose a course or certification", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                } else if (!test) {
                    toast = Toast.makeText(context, "Course or certification invalid. Please try again", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                }
            }
            else {
                toast = Toast.makeText(context, "Please review your advance setting", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();

            }
        }
        else {
            toast = Toast.makeText(context, "Please review your advance setting, It cannot be empty", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();

        }
    }
    public void advance_setting_onclick(Button advance_setting, EditText passing_grade,TextView passing_grade_text,EditText noq ,TextView noq_text){
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

}
