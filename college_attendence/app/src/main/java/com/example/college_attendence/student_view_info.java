package com.example.college_attendence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class student_view_info extends AppCompatActivity {

    EditText e1;
    Button view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_info);
        e1=(EditText)findViewById(R.id.e1);
        view=(Button)findViewById(R.id.b1);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e1.getText().toString().equals("3A")) {
                    Intent i = new Intent(student_view_info.this, view_student.class);
                    startActivity(i);
                }
                else if(e1.getText().toString().equals("3B")) {
                    Intent i1=new Intent(student_view_info.this,view_student1.class);
                    startActivity(i1);
                }
                else if(e1.getText().toString().equals("3C")){
                    Intent i3=new Intent(student_view_info.this,view_student2.class);
                    startActivity(i3);
                }

                else if(e1.getText().toString().equals("2A")){
                    Intent i4=new Intent(student_view_info.this,view_student3.class);
                    startActivity(i4);
                }

                else if(e1.getText().toString().equals("2B")){
                    Intent i5=new Intent(student_view_info.this,view_student4.class);
                    startActivity(i5);
                }

                else if(e1.getText().toString().equals("2C")){
                    Intent i6=new Intent(student_view_info.this,view_student5.class);
                    startActivity(i6);
                }

                else if(e1.getText().toString().equals("4A")){
                    Intent i7=new Intent(student_view_info.this,view_student6.class);
                    startActivity(i7);
                }

                else if(e1.getText().toString().equals("4B")){
                    Intent i8=new Intent(student_view_info.this,view_student7.class);
                    startActivity(i8);
                }
                else if(e1.getText().toString().equals("4C"))
                {
                    Intent i9=new Intent(student_view_info.this,view_student8.class);
                    startActivity(i9);
                }
            }
        });
    }
}
