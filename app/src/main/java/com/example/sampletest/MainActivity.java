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
        Button button=findViewById(R.id.startTest);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Questions.class);
                intent.putExtra("x", 2);
                startActivity(intent);

            }

        });

    }

}
