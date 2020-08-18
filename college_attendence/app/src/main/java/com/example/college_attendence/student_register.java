package com.example.college_attendence;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;


public class student_register extends AppCompatActivity {

    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;
    EditText e1,e2,e3;
    Spinner s1,s2;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        e1=(EditText)findViewById(R.id.e1);
        e2=(EditText)findViewById(R.id.e2);
        e3=(EditText)findViewById(R.id.e3);
        s1=(Spinner)findViewById(R.id.s1);
        s2=(Spinner)findViewById(R.id.s2);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);
        ArrayAdapter<String> myadapter=new ArrayAdapter<String>(student_register.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.department));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(myadapter);

        ArrayAdapter<String> myadapter1=new ArrayAdapter<String>(student_register.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.year));
        myadapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(myadapter1);

        register=(Button)findViewById(R.id.b1);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e1.getText().toString().isEmpty() || e2.getText().toString().isEmpty() || e3.getText().toString().isEmpty()){
                    Toast.makeText(student_register.this, "please fill all the details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String fname=e1.getText().toString();
                    String lname=e2.getText().toString();
                    String usn=e3.getText().toString();
                    String dept=s1.getSelectedItem().toString();
                    String year=s2.getSelectedItem().toString();

                    student Student=new student();
                    Student.setFname(fname);
                    Student.setLname(lname);
                    Student.setUsn(usn);
                    Student.setDept(dept);
                    Student.setYear(year);

                    showProgress(true);
                    tvLoad.setText("wait..... student is being registered..");

                    Backendless.Persistence.save(Student, new AsyncCallback<student>() {
                        @Override
                        public void handleResponse(student response) {
                            showProgress(false);
                            Toast.makeText(student_register.this, "Student registered successfully", Toast.LENGTH_SHORT).show();
                            e1.setText("");
                            e2.setText("");
                            e3.setText("");
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(student_register.this, "error!!", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });



    }


    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });

            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}

