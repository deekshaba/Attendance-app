package com.example.college_attendence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    Button logout,addf,viewf,logf,adds,views;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        logout=(Button)findViewById(R.id.b6);
        addf=(Button)findViewById(R.id.b1);
        viewf=(Button)findViewById(R.id.b2);
        adds=(Button)findViewById(R.id.b4);
        views=(Button)findViewById(R.id.b5);
        logf=(Button)findViewById(R.id.b3);

        addf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(Menu.this,faculty_register.class);
                startActivity(i1);
            }
        });


        viewf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(Menu.this,view_faculty.class);
                startActivity(i2);

            }
        });

        logf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(Menu.this,faculty_login.class);
              startActivity(i3);
            }
        });

        adds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i4=new Intent(Menu.this,student_register.class);
                startActivity(i4);
            }
        });

        views.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i5=new Intent(Menu.this,student_view_info.class);
                startActivity(i5);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Menu.this.finish();

            }
        });

    }
}
