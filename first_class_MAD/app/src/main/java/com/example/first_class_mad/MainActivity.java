package com.example.first_class_mad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button submitBtn;
    EditText formTxt;
    TextView outputTxtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitBtn = (Button) findViewById(R.id.submitBtn);
        formTxt = (EditText)findViewById(R.id.formTxt);
        outputTxtView = (TextView)findViewById(R.id.outputTxtView);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = formTxt.getText().toString();
                Toast.makeText(MainActivity.this,txt, Toast.LENGTH_LONG).show();
                outputTxtView.setText(txt);
            }
        });
    }

}